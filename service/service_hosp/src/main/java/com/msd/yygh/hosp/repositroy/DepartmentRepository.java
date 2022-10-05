package com.msd.yygh.hosp.repositroy;

import com.msd.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author miaoshudong
 * @since 2022/10/5 10:58
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
