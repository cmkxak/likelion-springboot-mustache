package com.mustache.bbs.domain.dto.visit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VisitResponse {
    private String hospitalName;
    private String userName;
    private String disease;
    private float amount;
}
