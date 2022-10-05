package com.msd.yygh.hosp.repositroy;

import com.msd.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author miaoshudong
 * @since 2022/10/4 18:40
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    Hospital getHospitalByHoscode(String hoscode);
}
