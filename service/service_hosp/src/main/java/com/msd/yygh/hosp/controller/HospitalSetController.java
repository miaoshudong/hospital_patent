package com.msd.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msd.yygh.common.exception.YyghException;
import com.msd.yygh.common.result.Result;
import com.msd.yygh.common.utils.MD5;
import com.msd.yygh.hosp.service.HospitalSetService;
import com.msd.yygh.model.hosp.HospitalSet;
import com.msd.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author miaoshudong
 * @since 2022/10/1 15:27
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "根据id删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean b = hospitalSetService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "条件查询带分页")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        String hoscode = hospitalSetQueryVo.getHoscode();// 医院名称
        String hosname = hospitalSetQueryVo.getHosname();//医院编码
        if (!StringUtils.isEmpty(hosname)) {
            queryWrapper.like("hosname", hospitalSetQueryVo.getHosname());

        }
        if (!StringUtils.isEmpty(hoscode)) {
            queryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }

        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);
        return Result.ok(hospitalSetPage);
    }

    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //0 不能使用  1 能使用
        hospitalSet.setStatus(1);
        String hoscode = hospitalSet.getHoscode();
        Random random = new Random();
//        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        hospitalSet.setSignKey(MD5.encrypt(hoscode));
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("getHosptialSet/{id}")
    public Result getHospitalSet(@PathVariable long id){
//        try {
//            int a =1/0;
//        }catch (Exception e){
//            throw new YyghException("失败",201);
//        }
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        boolean flag = hospitalSetService.removeByIds(idList);
        return Result.ok(flag);
    }

    //8 医院设置锁定和解锁
    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        //根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }
    //9 发送签名秘钥
    @ApiOperation(value = "发送签名秘钥")
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }

}
