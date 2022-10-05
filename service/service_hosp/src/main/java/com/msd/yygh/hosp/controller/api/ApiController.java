package com.msd.yygh.hosp.controller.api;

import com.msd.yygh.common.exception.YyghException;
import com.msd.yygh.common.helper.HttpRequestHelper;
import com.msd.yygh.common.result.Result;
import com.msd.yygh.common.result.ResultCodeEnum;
import com.msd.yygh.common.utils.MD5;
import com.msd.yygh.hosp.service.DepartmentService;
import com.msd.yygh.hosp.service.HospitalService;
import com.msd.yygh.hosp.service.HospitalSetService;
import com.msd.yygh.hosp.service.ScheduleService;
import com.msd.yygh.model.hosp.Department;
import com.msd.yygh.model.hosp.Hospital;
import com.msd.yygh.model.hosp.Schedule;
import com.msd.yygh.vo.hosp.DepartmentQueryVo;
import com.msd.yygh.vo.hosp.DepartmentVo;
import com.msd.yygh.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/10/4 18:43
 */
@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalSetService hospitalSetService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value = "上传医院接口")
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);

        String sign = (String) map.get("sign");
        String hoscode = (String) map.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);

        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        String logoData = (String) map.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        map.put("logoData", logoData);
        hospitalService.save(map);
        return Result.ok();
    }

    @ApiOperation(value = "查询医院 ")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);

        String sign = (String) map.get("sign");
        String hoscode = (String) map.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    //    ————————————————————————————————————科室——————————————————————————————————————————————
    @ApiOperation(value = "上传科室接口")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        String sign = (String) map.get("sign");
        String hoscode = (String) map.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        departmentService.save(map);
        return Result.ok();
    }
    @ApiOperation(value = "查询科室接口")
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);

        String hoscode = (String) map.get("hoscode");

        int page = StringUtils.isEmpty(map.get("page"))
                ?1:Integer.parseInt((String) map.get("page"));
         int limit = StringUtils.isEmpty(map.get("limit"))
                ?1:Integer.parseInt((String) map.get("limit"));
        String sign = (String) map.get("sign");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> pageModel = departmentService.findPageDepartment(page,limit,departmentQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "删除科室接口")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        String hoscode = (String) map.get("hoscode");
        String depcode = (String) map.get("depcode");

        String sign = (String) map.get("sign");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        departmentService.remove(hoscode,depcode);
        return Result.ok();

    }
//    ---------------------------------------排班接口---------------------------------------------------------
    @ApiOperation(value = "上传排班接口")
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        String hoscode = (String) map.get("hoscode");

        String sign = (String) map.get("sign");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        scheduleService.save(map);
        return Result.ok();
    }

    @ApiOperation(value = "查询排班")
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        String hoscode = (String) map.get("hoscode");
        String depcode = (String) map.get("depcode");

        int page = StringUtils.isEmpty(map.get("page"))
                ?1:Integer.parseInt((String) map.get("page"));
        int limit = StringUtils.isEmpty(map.get("limit"))
                ?1:Integer.parseInt((String) map.get("limit"));
        String sign = (String) map.get("sign");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page,limit,scheduleQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "删除排班")
    @PostMapping("schedule/remove")
    public Result removeSchedule(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        String hoscode = (String) map.get("hoscode");
        String hosScheduleId = (String) map.get("hosScheduleId");

        String sign = (String) map.get("sign");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);
        if (!sign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        scheduleService.remove(hoscode,hosScheduleId);
        return Result.ok();
    }
}
