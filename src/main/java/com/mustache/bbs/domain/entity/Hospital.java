package com.mustache.bbs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nation_wide_hospitals")

public class Hospital {
    @Id
    private Integer id;
    @Column
    private String openServiceName;
    @Column
    private int openLocalGovernmentCode;
    @Column
    private String managementNumber;
    @Column
    private LocalDateTime licenseDate;
    @Column
    private int businessStatus;
    @Column
    private int businessStatusCode;
    @Column
    private String phone;
    @Column
    private String fullAddress;
    @Column
    private String roadNameAddress;
    @Column
    private String hospitalName;
    @Column
    private String businessTypeName;
    @Column
    private int healthcareProviderCount;
    @Column
    private int patientRoomCount;
    @Column
    private int totalNumberOfBeds;
    @Column
    private float totalAreaSize;

}
