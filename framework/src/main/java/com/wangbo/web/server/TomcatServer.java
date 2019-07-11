package com.wangbo.web.server;

import com.wangbo.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 19:45
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TomcatServer {

    private Tomcat tomcat;

    private String[] args;

    public TomcatServer(String[] args) {
        this.args = args;
    }

    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(6699);
        tomcat.start();

        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());
        DispatcherServlet servlet = new DispatcherServlet();

        Tomcat.addServlet(context, "dispatcherServlet", servlet).setAsyncSupported(true);

        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.getHost().addChild(context);

        Thread awaitThread = new Thread("tomcat_await_thread"){

            @Override
            public void run(){
                TomcatServer.this.tomcat.getServer().await();;
            }
        };
        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}