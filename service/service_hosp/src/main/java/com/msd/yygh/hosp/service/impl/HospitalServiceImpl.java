package com.msd.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.msd.yygh.hosp.repositroy.HospitalRepository;
import com.msd.yygh.hosp.service.HospitalService;
import com.msd.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/4 18:42
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Override
    public void save(Map<String, Object> map) {
        String s = JSONObject.toJSONString(map);
        Hospital hospital = JSONObject.parseObject(s, Hospital.class);

        String hoscode = hospital.getHoscode();
        Hospital hospitalExist =  hospitalRepository.getHospitalByHoscode(hoscode);
        if (hospitalExist!=null){
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital =  hospitalRepository.getHospitalByHoscode(hoscode);

        return hospital;
    }
}
