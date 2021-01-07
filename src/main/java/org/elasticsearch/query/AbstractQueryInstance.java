/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractQueryInstance
 * Author:   shencangsheng
 * Date:     2020/12/28 1:03 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.query.type.QueryMappingEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * query的抽象类 <br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
@Getter
public abstract class AbstractQueryInstance {
    // json路径
    @Setter
    private String path;
    @Setter
    private boolean nested = false;
    private Map<String, MappingInstance> instance = new HashMap<>();

    public void setNested(String path) {
        this.nested = true;
        this.path = path;
    }


    public String pathKey(String key) {
        if (Objects.nonNull(path))
            return path + "." + key;
        return key;
    }

    public AbstractQueryInstance put(String name, MappingInstance model) {
        instance.put(name, model);
        return this;
    }

    public MappingInstance createMappingInstance(String key, QueryMappingEnum type) {
        return new MappingInstance(pathKey(key), type);
    }

    public AbstractQueryInstance createMappingPutInstance(String name, String key, QueryMappingEnum type) {
        put(name, createMappingInstance(key, type));
        return this;
    }

    public AbstractQueryInstance createMappingPutInstance(String name, QueryMappingEnum type) {
        return createMappingPutInstance(name, name, type);
    }

    public AbstractQueryInstance createMappingPutInstance(String name, QueryMappingEnum type, MappingInstance next) {
        return createMappingPutInstance(name, name, type, next);
    }

    public AbstractQueryInstance createMappingPutInstance(String name, String key, QueryMappingEnum type, MappingInstance next) {
        MappingInstance instance = createMappingInstance(key, type);
        instance.setNext(next);
        put(name, instance);
        return this;
    }
}