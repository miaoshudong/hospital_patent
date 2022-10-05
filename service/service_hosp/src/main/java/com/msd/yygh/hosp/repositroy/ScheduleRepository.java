package com.msd.yygh.hosp.repositroy;

import com.msd.yygh.model.hosp.Hospital;
import com.msd.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author miaoshudong
 * @since 2022/10/5 12:24
 */
public interface ScheduleRepository  extends MongoRepository<Schedule,String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

}
