/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: QueryBoolJob
 * Author:   shencangsheng
 * Date:     2020/12/30 5:41 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query.jobs;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.model.CentricModel;
import org.elasticsearch.model.QueryModel;
import org.elasticsearch.query.AbstractModuleInstance;
import org.elasticsearch.query.AbstractQueryInstance;
import org.elasticsearch.query.MappingInstance;
import org.elasticsearch.query.json.Query;
import org.elasticsearch.query.methods.QueryRencapsulation;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.elasticsearch.query.type.QueryBoolEnum.FILTER;
import static org.elasticsearch.query.type.QueryBoolEnum.MUST_NOT;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/30
 * @since 1.0.0
 */
public class QueryBoolJob {

    public static final BoolQueryBuilder createQueryStatements(CentricModel model, AbstractModuleInstance moduleInstance) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (QueryModel element : model.getQuery()) {
            AbstractQueryInstance queryInstance = moduleInstance.getModule(element.getModule());
            if (queryInstance.isNested()) {
                boolQueryBuilder.filter(nested(queryInstance, element));
            } else {
                flowQueryBuilder(queryInstance, boolQueryBuilder, element);
            }
        }
        if (Objects.nonNull(model.getDsl())) {
            boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.wrapperQuery(model.getDsl())).filter(boolQueryBuilder);
        }
        return boolQueryBuilder;
    }

    public static final AbstractQueryBuilder queryProcessingFlow(BoolQueryBuilder boolQueryBuilder, MappingInstance instance, Query query) throws Exception {
        AbstractQueryBuilder abstractQueryBuilder = flow(instance, query);
        switch (Objects.nonNull(query.getBoolType()) ? query.getBoolType() : FILTER) {
            case FILTER:
                boolQueryBuilder.filter(abstractQueryBuilder);
                break;
            case SHOULD:
                boolQueryBuilder.minimumShouldMatch(instance.getMinimumShouldMatch());
                boolQueryBuilder.should(abstractQueryBuilder);
                break;
            case MUST_NOT:
                boolQueryBuilder.mustNot(abstractQueryBuilder);
                break;
            default:
                throw new IllegalArgumentException(String.format("Type %s was not found", query.getBoolType()));
        }
        return boolQueryBuilder;
    }

    public static AbstractQueryBuilder flow(MappingInstance instance, Query query) throws Exception {
        switch (instance.getType()) {
            case KEYWORD:
            case STRING:
                return terms(instance, query);
            case DATE:
            case LONG:
            case DOUBLE:
                return range(instance, query);
            case WILDCARD:
                return wildcard(instance, query);
            case GROUP:
                return group(instance, query);
        }
        throw new IllegalArgumentException(format("Type %s was not found", instance.getType()));
    }


    public static RangeQueryBuilder range(MappingInstance instance, Query query) throws Exception {
        return QueryRencapsulation.range(instance.getKey(), instance.getType(), query.rangeData());
    }

    public static TermsQueryBuilder terms(MappingInstance instance, Query query) {
        return QueryRencapsulation.terms(instance.getKey(), query.listData());
    }

    public static TermQueryBuilder term(MappingInstance instance, Query query) {
        return QueryRencapsulation.term(instance.getKey(), query.getData());
    }

    public static BoolQueryBuilder wildcard(MappingInstance instance, Query query) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().minimumShouldMatch(1);
        query.listData().forEach(element -> boolQueryBuilder.should(QueryRencapsulation.wildcard(instance.getKey(), (String) element)));
        return boolQueryBuilder;
    }


    public static AbstractQueryBuilder nested(AbstractQueryInstance queryInstance, QueryModel query) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder notBoolQuery = null;
        BoolQueryBuilder boolQuery = null;
        for (Query element : query.getQuery()) {
            if (element.getBoolType() == MUST_NOT) {
                element.setBoolType(FILTER);
                if (Objects.isNull(notBoolQuery)) {
                    notBoolQuery = QueryBuilders.boolQuery();
                }
                queryProcessingFlow(notBoolQuery, queryInstance.getInstance().get(element.getKey()), element);
            } else {
                if (Objects.isNull(boolQuery))
                    boolQuery = QueryBuilders.boolQuery();
                queryProcessingFlow(boolQueryBuilder, queryInstance.getInstance().get(element.getKey()), element);
            }
        }
        if (Objects.nonNull(boolQuery)) {
            boolQueryBuilder.filter(QueryBuilders.nestedQuery(queryInstance.getPath(), boolQuery, ScoreMode.None));
        }
        if (Objects.nonNull(notBoolQuery)) {
            boolQueryBuilder.mustNot(QueryBuilders.nestedQuery(queryInstance.getPath(), notBoolQuery, ScoreMode.None));
        }
        return QueryBuilders.nestedQuery(queryInstance.getPath(), boolQueryBuilder, ScoreMode.None);
    }

    public static AbstractQueryBuilder flowQueryBuilder(AbstractQueryInstance queryInstance, BoolQueryBuilder boolQueryBuilder, QueryModel query) throws Exception {
        for (Query element : query.getQuery()) {
            queryProcessingFlow(boolQueryBuilder, queryInstance.getInstance().get(element.getKey()), element);
        }
        return boolQueryBuilder;
    }

    public static AbstractQueryBuilder group(MappingInstance instance, Query query) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<Query> list = query.groupData();
        for (Query element : list) {
            BoolQueryBuilder builder = QueryBuilders.boolQuery().filter(QueryRencapsulation.term(instance.getKey(), element.getKey()));
            queryProcessingFlow(builder, instance.getNext(), element);
            boolQueryBuilder.should(builder);
        }
        boolQueryBuilder.minimumShouldMatch(instance.getMinimumShouldMatch());
        return boolQueryBuilder;
    }
}