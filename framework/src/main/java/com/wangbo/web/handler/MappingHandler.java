package com.wangbo.web.handler;

import com.wangbo.beans.BeanFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 23:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MappingHandler {
    private String uri;
    private Method method;
    private Class<?> controller;
    private String[] args;

    public boolean handle(ServletRequest req, ServletResponse res) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        if (!uri.equals(requestUri)) {
            return false;
        }

        Object[] parameters = new Object[args.length];
        for (int i=0;i<args.length;i++) {
            parameters[i] = req.getParameter(args[i]);
        }

        Object ctl = BeanFactory.getBean(controller);
        Object response = method.invoke(ctl, parameters);
        res.getWriter().println(response.toString());
        return true;
    }
    public MappingHandler(String uri, Method method, Class<?> cls, String[] args) {
        this.uri = uri;
        this.method = method;
        this.controller = cls;
        this.args = args;
    }


}