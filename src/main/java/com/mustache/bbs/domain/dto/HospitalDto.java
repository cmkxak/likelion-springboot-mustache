package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class HospitalDto {
    //id, 병원명, 도로명 주소
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;

    public HospitalDto(Integer id, String hospitalName, String roadNameAddress) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.roadNameAddress = roadNameAddress;
    }
}
