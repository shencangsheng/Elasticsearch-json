/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Test
 * Author:   shencangsheng
 * Date:     2020/12/28 3:41 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import com.google.common.collect.Lists;
import org.elasticsearch.query.AbstractModuleInstance;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.model.CentricModel;
import org.elasticsearch.model.QueryModel;
import org.elasticsearch.query.jobs.QueryBoolJob;
import org.elasticsearch.query.json.Query;
import org.elasticsearch.query.json.QueryRange;
import org.elasticsearch.query.model.QueryDefaultInstance;
import org.elasticsearch.query.model.QueryNestedInstance;

import java.util.HashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
public class Test extends AbstractModuleInstance {

    public Test() {
        this.modules = new HashMap() {{
            put("USER", new QueryDefaultInstance());
            put("ORDER", new QueryNestedInstance());
        }};
    }

    public static void main(String[] args) throws Exception {
        CentricModel model = new CentricModel();
        model.setDsl("{\"bool\": {\"filter\": [{\"terms\": {\"user.phone\": [\"1300000000\"] } } ] } }");
        QueryModel user = new QueryModel("USER", Lists.newArrayList(
                new Query("name", Lists.newArrayList("张*三", "李*")),
                new Query("age", new QueryRange().setGt(20)),
                new Query("gender", Lists.newArrayList("男")),
                new Query("city", Lists.newArrayList(new Query("四川", Lists.newArrayList("成都", "重庆")))),
                new Query("countries", Lists.newArrayList(
                        new Query("中国", Lists.newArrayList(new Query("四川", Lists.newArrayList("成都", "重庆")))),
                        new Query("美国", Lists.newArrayList(new Query("得克萨斯州", Lists.newArrayList("小镇"))))
                ))
        ));

        QueryModel order = new QueryModel("ORDER", Lists.newArrayList(
                new Query("id", Lists.newArrayList("0001", "0002"))
        ));
        model.setQuery(Lists.newArrayList(
                user,
                order
        ));
        BoolQueryBuilder boolQueryBuilder = QueryBoolJob.createQueryStatements(model, new Test());
        System.out.println(boolQueryBuilder.toString());
    }
}