/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.micode.notes.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import java.util.HashMap;

public class Contact {
    // 用于缓存联系人信息的 HashMap
    private static HashMap<String, String> sContactCache;
    private static final String TAG = "Contact"; // 日志标签

    // 查询联系人时使用的 SQL 选择条件
    private static final String CALLER_ID_SELECTION = "PHONE_NUMBERS_EQUAL(" + Phone.NUMBER
    + ",?) AND " + Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "'"
    + " AND " + Data.RAW_CONTACT_ID + " IN "
            + "(SELECT raw_contact_id "
            + " FROM phone_lookup"
            + " WHERE min_match = '+')";

    /**
     * 根据电话号码获取联系人名称
     *
     * @param context 上下文对象
     * @param phoneNumber 电话号码
     * @return 联系人名称，如果找不到则返回 null
     */
    public static String getContact(Context context, String phoneNumber) {
        // 初始化联系人缓存
        if (sContactCache == null) {
            sContactCache = new HashMap<String, String>();
        }

        // 如果缓存中已存在该电话号码的联系人名称，直接返回
        if (sContactCache.containsKey(phoneNumber)) {
            return sContactCache.get(phoneNumber);
        }

        // 替换选择条件中的 "+" 为电话号码的最小匹配形式
        String selection = CALLER_ID_SELECTION.replace("+",
                PhoneNumberUtils.toCallerIDMinMatch(phoneNumber));
        
        // 查询联系人数据库
        Cursor cursor = context.getContentResolver().query(
                Data.CONTENT_URI,
                new String[] { Phone.DISPLAY_NAME }, // 查询联系人名称
                selection, // 选择条件
                new String[] { phoneNumber }, // 查询参数
                null);

        // 检查查询结果
        if (cursor != null && cursor.moveToFirst()) {
            try {
                // 获取联系人名称并缓存
                String name = cursor.getString(0);
                sContactCache.put(phoneNumber, name);
                return name;
            } catch (IndexOutOfBoundsException e) {
                // 处理索引越界异常
                Log.e(TAG, "Cursor get string error " + e.toString());
                return null;
            } finally {
                // 确保关闭游标
                cursor.close();
            }
        } else {
            // 如果没有找到匹配的联系人，记录日志
            Log.d(TAG, "No contact matched with number:" + phoneNumber);
            return null;
        }
    }
}
