package com.msd.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;

/**
 * @author miaoshudong
 * @since 2022/10/4 8:40
 */
public class TestRead {
    public static void main(String[] args) {
        String fileName="D:\\haha.xlsx";
        EasyExcel.read(fileName, UserData.class,new ExcelListener()).sheet().doRead();
    }
}
