/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: QueryNestedInstance
 * Author:   shencangsheng
 * Date:     2020/12/28 3:34 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query.model;

import org.elasticsearch.query.AbstractQueryInstance;

import static org.elasticsearch.query.type.QueryMappingEnum.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
public class QueryNestedInstance extends AbstractQueryInstance {
    public QueryNestedInstance() {
        setNested("order");
        createMappingPutInstance("id", STRING);
        createMappingPutInstance("created_time", DATE);
        createMappingPutInstance("money", DOUBLE);
    }
}