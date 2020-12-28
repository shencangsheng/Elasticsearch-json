/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractModuleInstance
 * Author:   shencangsheng
 * Date:     2020/12/28 3:44 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch;

import org.elasticsearch.query.AbstractQueryInstance;

import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

/**
 * 模块的抽象类,所有使用query都必须实现这个类 <br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
public abstract class AbstractModuleInstance {
    public Map<String, AbstractQueryInstance> modules;

    public AbstractQueryInstance getModule(String key) {
        return Objects.requireNonNull(modules.get(key), format("Type %s was not found", key));
    }
}