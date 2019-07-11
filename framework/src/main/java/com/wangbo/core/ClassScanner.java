package com.wangbo.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: wangbo
 * \* Date: 2019/7/10
 * \* Time: 21:25
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>>  classList = new ArrayList<>();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            if (resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFormJar(jarFilePath, path));
            }else {
                //todo
            }
        }
        return classList;
    }

    private static List<Class<?>> getClassesFormJar(String jarFilePath,String path) throws IOException, ClassNotFoundException {
        List<Class<?>>  classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);

        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()){
            JarEntry jarEntry = jarEntries.nextElement();
            String entryName = jarEntry.getName();

            if(entryName.startsWith(path)&&entryName.endsWith(".class")){
                String classFullName = entryName.replace("/","." ).
                        substring(0, entryName.length() - 6);
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }
}