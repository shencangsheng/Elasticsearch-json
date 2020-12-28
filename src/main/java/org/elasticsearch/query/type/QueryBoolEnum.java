package org.elasticsearch.query.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shencangsheng
 */

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum QueryBoolEnum {
    FILTER,
    SHOULD,
    MUST_NOT;
}
