package com.wangbo.starter;

import com.wangbo.beans.BeanFactory;
import com.wangbo.core.ClassScanner;
import com.wangbo.web.handler.HandlerManager;
import com.wangbo.web.server.TomcatServer;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 19:34
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MiniApplication {
    public static void run(Class<?> cls,String[] args) throws Exception {
        System.out.println("Hello mini-spring!");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();

            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());

            BeanFactory.initBean(classList);

            HandlerManager.resolveMappingHandler(classList);

            classList.forEach(it-> System.out.println(it.getName()));
        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}