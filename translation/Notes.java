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

import android.net.Uri;

public class Notes {
    // Authority for the content provider
    public static final String AUTHORITY = "micode_notes";
    public static final String TAG = "Notes"; // 日志标签

    // 备注的类型常量
    public static final int TYPE_NOTE = 0;     // 普通备注
    public static final int TYPE_FOLDER = 1;   // 文件夹
    public static final int TYPE_SYSTEM = 2;   // 系统备注

    /**
     * 以下 ID 是系统文件夹的标识符
     * {@link Notes#ID_ROOT_FOLDER} 是默认文件夹
     * {@link Notes#ID_TEMPARAY_FOLDER} 是没有文件夹的备注
     * {@link Notes#ID_CALL_RECORD_FOLDER} 用于存储通话记录
     */
    public static final int ID_ROOT_FOLDER = 0; // 根文件夹
    public static final int ID_TEMPARAY_FOLDER = -1; // 临时文件夹
    public static final int ID_CALL_RECORD_FOLDER = -2; // 通话记录文件夹
    public static final int ID_TRASH_FOLER = -3; // 垃圾箱文件夹

    // Intent 中使用的额外数据常量
    public static final String INTENT_EXTRA_ALERT_DATE = "net.micode.notes.alert_date";
    public static final String INTENT_EXTRA_BACKGROUND_ID = "net.micode.notes.background_color_id";
    public static final String INTENT_EXTRA_WIDGET_ID = "net.micode.notes.widget_id";
    public static final String INTENT_EXTRA_WIDGET_TYPE = "net.micode.notes.widget_type";
    public static final String INTENT_EXTRA_FOLDER_ID = "net.micode.notes.folder_id";
    public static final String INTENT_EXTRA_CALL_DATE = "net.micode.notes.call_date";

    // 小部件类型常量
    public static final int TYPE_WIDGET_INVALIDE = -1; // 无效小部件
    public static final int TYPE_WIDGET_2X = 0; // 2x 小部件
    public static final int TYPE_WIDGET_4X = 1; // 4x 小部件

    // 数据常量类
    public static class DataConstants {
        public static final String NOTE = TextNote.CONTENT_ITEM_TYPE; // 文本备注的 MIME 类型
        public static final String CALL_NOTE = CallNote.CONTENT_ITEM_TYPE; // 通话备注的 MIME 类型
    }

    /**
     * 查询所有备注和文件夹的 URI
     */
    public static final Uri CONTENT_NOTE_URI = Uri.parse("content://" + AUTHORITY + "/note");

    /**
     * 查询数据的 URI
     */
    public static final Uri CONTENT_DATA_URI = Uri.parse("content://" + AUTHORITY + "/data");

    // 备注列的接口
    public interface NoteColumns {
        /**
         * 行的唯一 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String ID = "_id";

        /**
         * 备注或文件夹的父 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String PARENT_ID = "parent_id";

        /**
         * 备注或文件夹的创建日期
         * <P> Type: INTEGER (long) </P>
         */
        public static final String CREATED_DATE = "created_date";

        /**
         * 最近修改日期
         * <P> Type: INTEGER (long) </P>
         */
        public static final String MODIFIED_DATE = "modified_date";

        /**
         * 提醒日期
         * <P> Type: INTEGER (long) </P>
         */
        public static final String ALERTED_DATE = "alert_date";

        /**
         * 文件夹名称或备注的文本内容
         * <P> Type: TEXT </P>
         */
        public static final String SNIPPET = "snippet";

        /**
         * 备注的小部件 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String WIDGET_ID = "widget_id";

        /**
         * 备注的小部件类型
         * <P> Type: INTEGER (long) </P>
         */
        public static final String WIDGET_TYPE = "widget_type";

        /**
         * 备注的背景颜色 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String BG_COLOR_ID = "bg_color_id";

        /**
         * 对于文本备注，没有附件；对于多媒体备注，至少有一个附件
         * <P> Type: INTEGER </P>
         */
        public static final String HAS_ATTACHMENT = "has_attachment";

        /**
         * 文件夹中的备注数量
         * <P> Type: INTEGER (long) </P>
         */
        public static final String NOTES_COUNT = "notes_count";

        /**
         * 文件的类型：文件夹或备注
         * <P> Type: INTEGER </P>
         */
        public static final String TYPE = "type";

        /**
         * 最后同步 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String SYNC_ID = "sync_id";

        /**
         * 标记本地是否已修改
         * <P> Type: INTEGER </P>
         */
        public static final String LOCAL_MODIFIED = "local_modified";

        /**
         * 移动到临时文件夹之前的原始父 ID
         * <P> Type: INTEGER </P>
         */
        public static final String ORIGIN_PARENT_ID = "origin_parent_id";

        /**
         * gtask ID
         * <P> Type: TEXT </P>
         */
        public static final String GTASK_ID = "gtask_id";

        /**
         * 版本号
         * <P> Type: INTEGER (long) </P>
         */
        public static final String VERSION = "version";
    }

    // 数据列的接口
    public interface DataColumns {
        /**
         * 行的唯一 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String ID = "_id";

        /**
         * 此行表示的项的 MIME 类型
         * <P> Type: Text </P>
         */
        public static final String MIME_TYPE = "mime_type";

        /**
         * 此数据所属的备注的引用 ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String NOTE_ID = "note_id";

        /**
         * 备注或文件夹的创建日期
         * <P> Type: INTEGER (long) </P>
         */
        public static final String CREATED_DATE = "created_date";

        /**
         * 最近修改日期
         * <P> Type: INTEGER (long) </P>
         */
        public static final String MODIFIED_DATE = "modified_date";

        /**
         * 数据的内容
         * <P> Type: TEXT </P>
         */
        public static final String CONTENT = "content";

        /**
         * 通用数据列，含义取决于 {@link #MIMETYPE}，用于整数数据类型
         * <P> Type: INTEGER </P>
         */
        public static final String DATA1 = "data1";

        /**
         * 通用数据列，含义取决于 {@link #MIMETYPE}，用于整数数据类型
         * <P> Type: INTEGER </P>
         */
        public static final String DATA2 = "data2";

        /**
         * 通用数据列，含义取决于 {@link #MIMETYPE}，用于文本数据类型
         * <P> Type: TEXT </P>
         */
        public static final String DATA3 = "data3";

        /**
         * 通用数据列，含义取决于 {@link #MIMETYPE}，用于文本数据类型
         * <P> Type: TEXT </P>
         */
        public static final String DATA4 = "data4";

        /**
         * 通用数据列，含义取决于 {@link #MIMETYPE}，用于文本数据类型
         * <P> Type: TEXT </P>
         */
        public static final String DATA5 = "data5";
    }

    // 文本备注的常量类
    public static final class TextNote implements DataColumns {
        /**
         * 指示文本是否处于检查列表模式的模式
         * <P> Type: Integer 1:检查列表模式 0:普通模式 </P>
         */
        public static final String MODE = DATA1;

        public static final int MODE_CHECK_LIST = 1; // 检查列表模式常量

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/text_note"; // 文本备注的内容类型

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/text_note"; // 单个文本备注的内容类型

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/text_note"); // 文本备注的 URI
    }

    // 通话备注的常量类
    public static final class CallNote implements DataColumns {
        /**
         * 此记录的通话日期
         * <P> Type: INTEGER (long) </P>
         */
        public static final String CALL_DATE = DATA1;

        /**
         * 此记录的电话号码
         * <P> Type: TEXT </P>
         */
        public static final String PHONE_NUMBER = DATA3;

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/call_note"; // 通话备注的内容类型

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/call_note"; // 单个通话备注的内容类型

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/call_note"); // 通话备注的 URI
    }
}
