/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: DateUtil
 * Author:   shencangsheng
 * Date:     2020/12/30 6:05 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.util;

import org.apache.http.client.utils.DateUtils;

import java.text.ParseException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/30
 * @since 1.0.0
 */
public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String[] DATE_FORMATS = new String[]{YYYY_MM_DD_HH_MM_SS, YYYY_MM_DD};


    public static Long getTime(String date) throws ParseException {
        return DateUtils.parseDate(date, DATE_FORMATS).getTime();
    }

}