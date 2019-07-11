package com.wangbo;


import com.wangbo.starter.MiniApplication;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 17:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello,World");
        MiniApplication.run(Application.class, args);
    }
}