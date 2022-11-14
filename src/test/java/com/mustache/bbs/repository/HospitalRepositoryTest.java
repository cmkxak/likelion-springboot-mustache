package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("BusinessTypeName이 보건소, 보건지소, 보건진료소인 데이터가 잘 나오는지?")
    void findByBusinessTypeNameIn(){
        List<String> hospitalColumnList = new ArrayList<>();
        hospitalColumnList.add("보건소");
        hospitalColumnList.add("보건지소");
        hospitalColumnList.add("보건진료소");

        List<Hospital> hospitalsbyBusinessTypeNameIn = hospitalRepository.findByBusinessTypeNameInAndRoadNameAddressContains(hospitalColumnList, "광진구");

        printQueryResult(hospitalsbyBusinessTypeNameIn);
    }

    @Test
    @DisplayName("BusinessTypeName이 피부과이고, 경기도 수원시에 있는 hospital")
    void findByBusinessTypeNameAndRoadNameAddress(){
        String roadNameAddress = "수원";
        String hospitalName = "피부";

        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContainsAndHospitalNameContains(roadNameAddress, hospitalName);

        printQueryResult(hospitals);
    }
    @Test
    @DisplayName("병상 수가 10개 이상 20개 미만인 병원 데이터 가져오기")
    void findByTotalNumberofBeds(){
        int min = 10;
        int max = 20;
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsBetweenOrderByPatientRoomCountDesc(min, max);

        printQueryResult(hospitals);
    }

    private void printQueryResult(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getHospitalName().concat(" " + hospital.getRoadNameAddress() + " "));
        }
    }
}