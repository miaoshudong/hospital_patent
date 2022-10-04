package com.msd.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author miaoshudong
 * @since 2022/10/3 17:32
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.msd")
public class  ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class,args);
    }
}
