/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BaseFilterModel
 * Author:   shencangsheng
 * Date:     2020/12/28 6:05 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.query.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.elasticsearch.query.type.QueryBoolEnum;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2020/12/28
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Query<T> {
    @NonNull
    private String key;
    private QueryBoolEnum boolType;
    @NonNull
    private T data;
    private String title;

    public List listData() {
        return (List) data;
    }

    public QueryRange rangeData() {
        return (QueryRange) data;
    }

    public List<Query> groupData() {
        return (List<Query>) data;
    }

    public String StringData() {
        return (String) data;
    }

    public Query(@NonNull String key, @NonNull T data) {
        this.key = key;
        this.data = data;
    }
}