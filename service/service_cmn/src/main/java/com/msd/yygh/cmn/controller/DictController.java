package com.msd.yygh.cmn.controller;

import com.msd.yygh.cmn.service.DictService;
import com.msd.yygh.common.result.Result;
import com.msd.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author miaoshudong
 * @since 2022/10/3 17:41
 */
@Api(tags = "数据字典接口")
@CrossOrigin
@Controller
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据id查询子数据列表")
    @GetMapping("findChlidData/{id}")
    @ResponseBody
    public Result findChildData(@PathVariable Long id){
        List<Dict> list =  dictService.findChildData(id);
        return Result.ok(list);
    }
    @ApiOperation(value = "导出数据字典")
    @GetMapping("exportData")
    public Result exportDict(HttpServletResponse response){
        dictService.exportDictData(response);
        return Result.ok();
    }
    @ApiOperation(value = "导入数据字典")
    @PostMapping("importData")
    @ResponseBody
    public Result importDict(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }

}
