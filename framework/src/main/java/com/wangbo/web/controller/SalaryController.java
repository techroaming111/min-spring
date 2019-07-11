package com.wangbo.web.controller;

import com.wangbo.beans.AutoWired;
import com.wangbo.web.controller.service.SalaryService;
import com.wangbo.web.mvc.Controller;
import com.wangbo.web.mvc.RequestMapping;
import com.wangbo.web.mvc.RequestParam;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 20:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class SalaryController {

    @AutoWired
    SalaryService salaryService;

    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("experience") String experience){
        return salaryService.calSalary(Integer.parseInt(experience));
    }


}