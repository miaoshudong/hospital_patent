package com.msd.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msd.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author miaoshudong
 * @since 2022/10/3 17:37
 */
public interface DictService extends IService<Dict> {
    List<Dict> findChildData(Long id);

    void exportDictData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
