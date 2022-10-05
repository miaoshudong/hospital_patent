package com.msd.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msd.yygh.hosp.mapper.HospitalSetMapper;
import com.msd.yygh.hosp.service.HospitalSetService;
import com.msd.yygh.model.hosp.Hospital;
import com.msd.yygh.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * @author miaoshudong
 * @since 2022/10/1 15:22
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(queryWrapper);
        String signKey = hospitalSet.getSignKey();
        return signKey;
    }


}
