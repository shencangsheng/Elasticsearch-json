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
import org.elasticsearch.AbstractModuleInstance;
import org.elasticsearch.query.json.BaseFilterModel;
import org.elasticsearch.query.model.QueryNestedInstance;
import org.elasticsearch.query.model.QueryOrdinaryInstance;

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
            put("PATIENT", new QueryOrdinaryInstance());
            put("ORDER", new QueryNestedInstance());
        }};
    }

    public static void main(String[] args) {
        BaseFilterModel baseFilterModel = new BaseFilterModel<>();
        baseFilterModel.setData(Lists.newArrayList("张三", "李四"));
    }
}