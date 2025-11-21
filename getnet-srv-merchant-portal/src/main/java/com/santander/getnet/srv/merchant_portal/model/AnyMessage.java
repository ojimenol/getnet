package com.santander.getnet.srv.merchant_portal.model;

import lombok.*;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnyMessage<T> {
    private String id;
    private String typeName;
    private Map<String, Object> properties;
    private T anyData;
}
