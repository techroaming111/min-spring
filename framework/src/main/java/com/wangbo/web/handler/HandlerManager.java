package com.wangbo.web.handler;

import com.wangbo.web.mvc.Controller;
import com.wangbo.web.mvc.RequestMapping;
import com.wangbo.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 23:09
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class HandlerManager {

    public static List<MappingHandler> mappingHandlers = new ArrayList<>();

    public static void resolveMappingHandler(List<Class<?>> classes){
        for (Class<?> cls : classes){
            if (cls.isAnnotationPresent(Controller.class));
            parseHandlerFromController(cls);
        }
    }

    private static void parseHandlerFromController(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods){
            if (!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            String uri = method.getAnnotation(RequestMapping.class).value();

            List<String> paramNameList = new ArrayList<>();

            for (Parameter parameter : method.getParameters()){
                if(parameter.isAnnotationPresent(RequestParam.class)){
                    paramNameList.add(parameter.getAnnotation(RequestParam.class).value());
                }
            }

            String[] params = paramNameList.toArray(new String[paramNameList.size()]);

            MappingHandler mappingHandler = new MappingHandler(uri, method, cls, params);

            HandlerManager.mappingHandlers.add(mappingHandler);
        }
    }
}