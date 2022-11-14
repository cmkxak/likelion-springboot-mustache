package com.mustache.bbs.repository;

import com.mustache.bbs.domain.dto.HospitalDto;
import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    @Query("select new com.mustache.bbs.domain.dto.HospitalDto(h.id, h.hospitalName, h.roadNameAddress) from Hospital h")
    List<HospitalDto> findAllByDTO(Pageable pageable);

    //광진구에 위치한 보건소라는 문자열을 포함하는 Hospital 반환
    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressContains(List<String> businessType, String roadNameAddress);

    //경기도 수원시에 있는 피부과 라는 문자열을 포함하는 Hospital 반환
    List<Hospital> findByRoadNameAddressContainsAndHospitalNameContains(String roadNameAddress, String businessTypeName);

    //병상 수가 10개 이상 20개 미만인 Hospital 데이터를 PatientRoomCount를 기준으로 내림차순 반환
    List<Hospital> findByTotalNumberOfBedsBetweenOrderByPatientRoomCountDesc(int min, int max);

}
