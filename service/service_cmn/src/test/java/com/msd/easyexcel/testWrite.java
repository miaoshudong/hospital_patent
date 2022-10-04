package com.msd.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoshudong
 * @since 2022/10/3 18:37
 */
public class testWrite {
    public static void main(String[] args) {
        List<UserData> userData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("lucy"+i);
            userData.add(data);
        }
        String fileName="D:\\haha.xlsx";
        EasyExcel.write(fileName, UserData.class).sheet("用户信息").doWrite(userData);
    }
}
