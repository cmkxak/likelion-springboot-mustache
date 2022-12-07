package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nation_wide_hospitals")

public class Hospital {
    @Id
    private Integer id;
    private String openServiceName;
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;

    @Transient
    private String businessStatusName;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(), hospital.getTotalAreaSize());
    }
}
