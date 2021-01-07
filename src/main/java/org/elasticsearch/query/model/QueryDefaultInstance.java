/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: QueryTestModel
 * Author:   shencangsheng
 * Date:     2020/12/28 2:37 下午
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
public class QueryDefaultInstance extends AbstractQueryInstance {

    public QueryDefaultInstance() {
        createMappingPutInstance("name", WILDCARD);
        createMappingPutInstance("age", LONG);
        createMappingPutInstance("gender", KEYWORD);
        createMappingPutInstance("provinces", KEYWORD);
        createMappingPutInstance("city", GROUP, getInstance().get("provinces"));
        createMappingPutInstance("countries", GROUP, getInstance().get("city"));
    }
}