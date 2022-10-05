package com.msd.yygh.hosp.service;

import com.msd.yygh.model.hosp.Schedule;
import com.msd.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/5 12:25
 */
public interface ScheduleService {
    void save(Map<String, Object> map);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);
}
