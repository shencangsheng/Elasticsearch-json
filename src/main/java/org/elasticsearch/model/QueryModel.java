/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: QueryModel
 * Author:   shencangsheng
 * Date:     2021/1/4 1:59 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.elasticsearch.query.json.Query;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author shencangsheng
 * @create 2021/1/4
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class QueryModel {
    private String module;
    private List<Query> query;
}