/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: FilterRangeModel
 * Author:   shencangsheng
 * Date:     2020/12/28 6:10 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query.json;

import lombok.Data;

/**
 * 针对于range类型的检索,可接受的值类型为 double,long,date <br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
@Data
public class QueryRangeModel<T> {
    private T gt;
    private T gte;
    private T lt;
    private T lte;
}