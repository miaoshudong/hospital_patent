package com.msd.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msd.yygh.model.hosp.Hospital;
import com.msd.yygh.model.hosp.HospitalSet;

/**
 * @author miaoshudong
 * @since 2022/10/1 15:20
 */
public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hoscode);

}
