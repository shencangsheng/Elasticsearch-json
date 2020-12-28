package org.elasticsearch.query.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum QueryMappingEnum {
    LONG,
    DOUBLE,
    STRING,
    MULTI_MATCH,
    KEYWORD,
    DATE,
    GROUP,
    NESTED,
    WILDCARD;
}
