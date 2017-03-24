package com.common.tool.doc;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class WarUtil {

    public void test(){
    }
    
    public static void main(String[] args) {
        getClasssFromJarFile("E://Svn_Admin/idbear-app-manager/target/idbear-app-manager/WEB-INF/lib");
    }

    public static List<Class> getClasssFromJarFile(String filePath) {

        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return null;
        }

        // System.out.println(packageName + "----" + filePath);
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                boolean acceptDir = file.isDirectory();// 接受dir目录
                boolean acceptClass = file.getName().endsWith("jar");// 接受jar文件
                return acceptDir || acceptClass;
            }
        });
        List<Class> clazzs = new ArrayList<Class>();
        for (File file : dirFiles) {

            JarFile jarFile = null;
            try {
                System.out.println(file.getName());
                jarFile = new JarFile(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            List<JarEntry> jarEntryList = new ArrayList<JarEntry>();

            Enumeration<JarEntry> ee = jarFile.entries();
            while (ee.hasMoreElements()) {
                JarEntry entry = (JarEntry) ee.nextElement();
                if (entry.getName().endsWith(".class")) {
                    jarEntryList.add(entry);
                }
            }
            for (JarEntry entry : jarEntryList) {
                String className = entry.getName().replace('/', '.');
                className = className.substring(0, className.length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return clazzs;
    }
}
