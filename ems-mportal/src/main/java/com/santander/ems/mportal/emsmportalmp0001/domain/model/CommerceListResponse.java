package com.santander.ems.mportal.emsmportalmp0001.domain.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommerceListResponse {
    private List<Commerce> commerceList;
}
