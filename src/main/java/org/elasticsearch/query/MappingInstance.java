/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MappingInstance
 * Author:   shencangsheng
 * Date:     2020/12/28 2:20 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query;

import lombok.Getter;
import org.elasticsearch.query.type.QueryBoolEnum;
import org.elasticsearch.query.type.QueryMappingEnum;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
@Getter
public class MappingInstance {
    private final String key;
    private final QueryMappingEnum type;
    private QueryBoolEnum bool = QueryBoolEnum.FILTER;
    private Integer minimumShouldMatch = 1;
    private MappingInstance next;

    public MappingInstance(String key, QueryMappingEnum type) {
        this.key = key;
        this.type = type;
    }

    public MappingInstance(String key, QueryMappingEnum type, MappingInstance next) {
        this(key, type);
        this.next = next;
    }
}