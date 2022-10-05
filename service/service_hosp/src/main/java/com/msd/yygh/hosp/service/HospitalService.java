package com.msd.yygh.hosp.service;

import com.msd.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/4 18:42
 */
public interface HospitalService {
    void save(Map<String, Object> map);

    Hospital getByHoscode(String hoscode);
}
