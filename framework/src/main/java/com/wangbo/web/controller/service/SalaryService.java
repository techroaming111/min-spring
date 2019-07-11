package com.wangbo.web.controller.service;

import com.wangbo.beans.Bean;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/11
 * \* Time: 09:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience * 100;
    }
}