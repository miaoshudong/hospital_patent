package com.msd.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.msd.yygh.hosp.repositroy.ScheduleRepository;
import com.msd.yygh.hosp.service.ScheduleService;
import com.msd.yygh.model.hosp.Department;
import com.msd.yygh.model.hosp.Schedule;
import com.msd.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/5 12:25
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public void save(Map<String, Object> map) {
        String s = JSONObject.toJSONString(map);
        Schedule schedule = JSONObject.parseObject(s, Schedule.class);

        Schedule scheduleExist = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(),schedule.getHosScheduleId());
        if (scheduleExist!=null){
            scheduleExist.setUpdateTime(new Date());
            scheduleExist.setIsDeleted(0);
            scheduleExist.setStatus(1);
            scheduleRepository.save(scheduleExist);
        }else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);
        }
    }

    @Override
    public Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
        Pageable pageable = PageRequest.of(page-1,limit);

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo,schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Example<Schedule> example = Example.of(schedule,matcher);

        Page<Schedule> all = scheduleRepository.findAll(example,pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Schedule schedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (schedule!=null){
            scheduleRepository.deleteById(schedule.getId());
        }
    }
}
