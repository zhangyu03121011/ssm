package com.common.tool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class ResumeDoc {
    public static void main(String[] args) throws Exception {
        // String str =
        // "/home/huijunluo/Desktop/zl/智联招聘_姚丽冷_招聘经理_中文_20151014_75344088.doc";
        //
        String str = "/home/huijunluo/Desktop/zl/51job_黄光涛(75218409).mht";

         str = "/home/huijunluo/Desktop/zl/b";
         
         str="/home/huijunluo/Desktop/简历/a";
         
         str="/home/huijunluo/Desktop/简历/test.mht";

        InputStream in = new FileInputStream(str);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str),"GB2312"));
        String line = null;
        StringBuffer buff = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            buff.append(line);
            //System.out.println(Base64Util.decodeStr(line.toString()));
        }
        System.out.println(Base64Util.decodeStr(buff.toString()));

    }

    // public static void main(String[] args) throws Exception {
    // String str =
    // "/home/huijunluo/Desktop/zl/智联招聘_姚丽冷_招聘经理_中文_20151014_75344088.doc";
    //
    // BufferedReader bufferedReader = new BufferedReader(new
    // InputStreamReader(new FileInputStream(str), "UTF-8"));
    // String line = null;
    // StringBuffer buff=new StringBuffer();
    // while ((line = bufferedReader.readLine()) != null) {
    // System.out.println(line);
    // }
    //
    // }
}
