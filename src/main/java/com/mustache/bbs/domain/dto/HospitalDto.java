package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Hospital;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HospitalDto {
    //id, 병원명, 도로명 주소
    private Long id;
    private String hospitalName;
    private String roadNameAddress;

    public Hospital toEntity(){
        return new Hospital(this.id, this.hospitalName, this.roadNameAddress);
    }
}
