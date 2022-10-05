package com.msd.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.msd.yygh.hosp.repositroy.DepartmentRepository;
import com.msd.yygh.hosp.service.DepartmentService;
import com.msd.yygh.model.hosp.Department;
import com.msd.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/5 11:00
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> map) {
        String s = JSONObject.toJSONString(map);
        Department department = JSONObject.parseObject(s, Department.class);

        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());
        if (departmentExist!=null){
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        Pageable pageable = PageRequest.of(page-1,limit);

        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Example<Department> example = Example.of(department,matcher);

        Page<Department> all = departmentRepository.findAll(example,pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department!=null){
            departmentRepository.deleteById(department.getId());
        }
    }
}
