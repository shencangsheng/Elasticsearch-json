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
public class BaseFilterModel<T> {
    @NonNull
    private String key;
    private QueryBoolEnum boolType;
    @NonNull
    private T data;
    private String title;

    public List dataList() {
        return (List) data;
    }

    public FilterRangeModel dataRange() {
        return (FilterRangeModel) data;
    }

    public List<BaseFilterModel> dataGroup() {
        return (List<BaseFilterModel>) data;
    }
}