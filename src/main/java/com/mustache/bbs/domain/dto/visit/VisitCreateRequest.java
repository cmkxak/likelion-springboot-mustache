package com.mustache.bbs.domain.dto.visit;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VisitCreateRequest {
    private Integer hospitalId;
    private String disease;
    private float amount;

    public Visit toEntity(Hospital hospital) {
        return Visit.builder()
                .hospital(hospital)
                .disease(disease)
                .amount(amount)
                .build();
    }
}
