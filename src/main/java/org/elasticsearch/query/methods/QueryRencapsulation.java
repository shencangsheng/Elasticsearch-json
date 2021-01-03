/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: QueyRencapsulation
 * Author:   shencangsheng
 * Date:     2020/12/30 5:59 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query.methods;

import org.elasticsearch.index.query.*;
import org.elasticsearch.query.json.QueryRangeModel;
import org.elasticsearch.query.type.QueryMappingEnum;
import org.util.DateUtil;

import java.util.List;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/30
 * @since 1.0.0
 */
public class QueryRencapsulation {
    public static TermsQueryBuilder terms(String name, List value) {
        return QueryBuilders.termsQuery(name, value);
    }

    public static TermQueryBuilder term(String name, Object value) {
        return QueryBuilders.termQuery(name, value);
    }

    public static MultiMatchQueryBuilder multi_match(Object value, String... name) {
        return QueryBuilders.multiMatchQuery(value, name);
    }

    public static WildcardQueryBuilder wildcard(String name, String value) {
        return QueryBuilders.wildcardQuery(name, value);
    }

    public static RangeQueryBuilder range(String name, QueryMappingEnum type, QueryRangeModel range) throws Exception {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(name);
        if (type == QueryMappingEnum.DATE) {
            if (Objects.nonNull(range.getGt())) {
                range.setGt(DateUtil.getTime(range.getGt().toString()));
            }
            if (Objects.nonNull(range.getGte())) {
                range.setGte(DateUtil.getTime(range.getGte().toString()));
            }
            if (Objects.nonNull(range.getLt())) {
                range.setLt(DateUtil.getTime(range.getLt().toString()));
            }
            if (Objects.nonNull(range.getLte())) {
                range.setLte(DateUtil.getTime(range.getLte().toString()));
            }
        }
        if (Objects.nonNull(range.getGt())) {
            rangeQueryBuilder.gt(range.getGt());
        }
        if (Objects.nonNull(range.getGte())) {
            rangeQueryBuilder.gte(range.getGte());
        }
        if (Objects.nonNull(range.getLt())) {
            rangeQueryBuilder.lt(range.getLt());
        }
        if (Objects.nonNull(range.getLte())) {
            rangeQueryBuilder.lte(range.getLte());
        }
        return rangeQueryBuilder;
    }

}