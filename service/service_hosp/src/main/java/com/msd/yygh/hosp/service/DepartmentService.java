package com.msd.yygh.hosp.service;

import com.msd.yygh.model.hosp.Department;
import com.msd.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/5 11:00
 */
public interface DepartmentService {
    void save(Map<String, Object> map);

    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);
}
