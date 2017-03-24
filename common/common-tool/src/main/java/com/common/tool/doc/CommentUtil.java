//package com.common.tool.doc;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.StringReader;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang.StringUtils;
//
///**
// * @function：打印java文件中的所有注释 com.cn.fanjg.regex
// * @author fanjg
// * @date : 2013-4-7 下午06:57:20
// */
//public class CommentUtil {
//    public static void main(String[] args) {
//        // System.out.println(getClassComment(LoginController.class));
//        // System.out.println(getMethodComment(LoginController.class, "auth"));
//        // System.out.println(getFieldComment(LogDownloadVo.class, "device"));
//    }
//
//    public static String getClassComment(Class class1) {
//        String result = "";
//        String fileName = class1.getResource(".").getPath().replaceAll("target/classes", "src/main/java")
//                + class1.getSimpleName() + ".java";
//        try {
//            // FileReader freader = new FileReader(fileName);
//            InputStream in = new FileInputStream(fileName);
//            InputStreamReader inputStreamReader = new InputStreamReader(in, "utf-8");
//            BufferedReader breader = new BufferedReader(inputStreamReader);
//            StringBuilder sb = new StringBuilder();
//            try {
//                String temp = "";
//                /**
//                 * 读取文件内容，并将读取的每一行后都不加\n 其目的是为了在解析双反斜杠（//）注释时做注释中止符
//                 */
//                while ((temp = breader.readLine()) != null) {
//                    sb.append(temp);
//                    sb.append('\n');
//                }
//                String src = sb.toString();
//                src = src.substring(0, src.indexOf(class1.getSimpleName()));
//                src = src.substring(src.lastIndexOf("/**"));
//                // System.out.println(src);
//
//                /**
//                 * 1、做/* 注释的正则匹配
//                 *
//                 * 
//                 * 通过渐进法做注释的正则匹配，因为/*注释总是成对出现 当匹配到一个/*时总会在接下来的内容中会首先匹配到"*\\/",
//                 * 因此在获取对应的"*\\/"注释时只需要从当前匹配的/*开始即可， 下一次匹配时只需要从上一次匹配的结尾开始即可
//                 * （这样对于大文本可以节省匹配效率）—— 这就是渐进匹配法
//                 *
//                 * 
//                 */
//                Pattern leftpattern = Pattern.compile("/\\*");
//                Matcher leftmatcher = leftpattern.matcher(src);
//                Pattern rightpattern = Pattern.compile("\\*/");
//                Matcher rightmatcher = rightpattern.matcher(src);
//                sb = new StringBuilder();
//                /**
//                 * begin 变量用来做渐进匹配的游标 {@value} 初始值为文件开头
//                 **/
//                int begin = 0;
//                while (leftmatcher.find(begin)) {
//                    rightmatcher.find(leftmatcher.start());
//                    sb.append(src.substring(leftmatcher.start(), rightmatcher.end()));
//                    /** 为输出时格式的美观 **/
//                    sb.append('\n');
//                    begin = rightmatcher.end();
//                }
//                // System.out.println(sb.toString());
//
//                StringReader stringReader = new StringReader(sb.toString());
//                BufferedReader reader = new BufferedReader(stringReader);
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    line = line.replaceAll("/", "").replaceAll("\\*", "").trim();
//                    if (line.indexOf("@") == -1 && StringUtils.isNotEmpty(line)) {
//                        // System.out.println(line + "----");
//                        result += line;
//                    }
//                }
//
//            } catch (IOException e) {
//                System.out.println("文件读取失败");
//            } finally {
//                breader.close();
//                // freader.close();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("文件不存在");
//        } catch (IOException e) {
//            System.out.println("文件读取失败");
//        }
//        return result;
//    }
//
//    public static String getMethodComment(Class class1, String methodName) {
//        String result = "";
//        String fileName = class1.getResource(".").getPath().replaceAll("target/classes", "src/main/java")
//                + class1.getSimpleName() + ".java";
//        try {
//            // FileReader freader = new FileReader(fileName);
//            InputStream in = new FileInputStream(fileName);
//            InputStreamReader inputStreamReader = new InputStreamReader(in, "utf-8");
//            BufferedReader breader = new BufferedReader(inputStreamReader);
//            StringBuilder sb = new StringBuilder();
//            try {
//                String temp = "";
//                /**
//                 * 读取文件内容，并将读取的每一行后都不加\n 其目的是为了在解析双反斜杠（//）注释时做注释中止符
//                 */
//                while ((temp = breader.readLine()) != null) {
//                    sb.append(temp);
//                    sb.append('\n');
//                }
//                String src = sb.toString();
//
//                Pattern p = Pattern.compile("public\\s.*\\s" + methodName + ".*\\(");
//                Matcher m = p.matcher(src);
//
//                int index = src.length() - 1;
//                while (m.find()) {
//                    // System.out.println(m.start());
//                    index = m.start();
//                }
//
//                src = src.substring(0, index);
//                src = src.substring(src.lastIndexOf("/**"));
//                // System.out.println(src);
//
//                /**
//                 * 1、做/* 注释的正则匹配
//                 *
//                 * 
//                 * 通过渐进法做注释的正则匹配，因为/*注释总是成对出现 当匹配到一个/*时总会在接下来的内容中会首先匹配到"*\\/",
//                 * 因此在获取对应的"*\\/"注释时只需要从当前匹配的/*开始即可， 下一次匹配时只需要从上一次匹配的结尾开始即可
//                 * （这样对于大文本可以节省匹配效率）—— 这就是渐进匹配法
//                 *
//                 * 
//                 */
//                Pattern leftpattern = Pattern.compile("/\\*");
//                Matcher leftmatcher = leftpattern.matcher(src);
//                Pattern rightpattern = Pattern.compile("\\*/");
//                Matcher rightmatcher = rightpattern.matcher(src);
//                sb = new StringBuilder();
//                /**
//                 * begin 变量用来做渐进匹配的游标 {@value} 初始值为文件开头
//                 **/
//                int begin = 0;
//                while (leftmatcher.find(begin)) {
//                    rightmatcher.find(leftmatcher.start());
//                    sb.append(src.substring(leftmatcher.start(), rightmatcher.end()));
//                    /** 为输出时格式的美观 **/
//                    sb.append('\n');
//                    begin = rightmatcher.end();
//                }
//                // System.out.println(sb.toString());
//
//                StringReader stringReader = new StringReader(sb.toString());
//                BufferedReader reader = new BufferedReader(stringReader);
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    line = line.replaceAll("/", "").replaceAll("\\*", "").trim();
//                    if (line.indexOf("@") == -1 && StringUtils.isNotEmpty(line)) {
//                        // System.out.println(line + "----");
//                        result += line;
//                    }
//                }
//
//            } catch (IOException e) {
//                System.out.println("文件读取失败");
//            } finally {
//                breader.close();
//                // freader.close();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("文件不存在");
//        } catch (IOException e) {
//            System.out.println("文件读取失败");
//        }
//        return result;
//    }
//
//    public static String getFieldComment(Class class1, String fieldName) {
//        String result = "";
//        String fileName = class1.getResource(".").getPath().replaceAll("target/classes", "src/main/java")
//                + class1.getSimpleName() + ".java";
//        try {
//            // FileReader freader = new FileReader(fileName);
//            InputStream in = new FileInputStream(fileName);
//            InputStreamReader inputStreamReader = new InputStreamReader(in, "utf-8");
//            BufferedReader breader = new BufferedReader(inputStreamReader);
//            StringBuilder sb = new StringBuilder();
//            try {
//                String temp = "";
//                /**
//                 * 读取文件内容，并将读取的每一行后都不加\n 其目的是为了在解析双反斜杠（//）注释时做注释中止符
//                 */
//                while ((temp = breader.readLine()) != null) {
//                    sb.append(temp);
//                    sb.append('\n');
//                }
//                String src = sb.toString();
//
//                Pattern p = Pattern.compile("private\\s.*\\s" + fieldName + ";");
//                Matcher m = p.matcher(src);
//
//                int index = src.length() - 1;
//                while (m.find()) {
//                    // System.out.println(m.start());
//                    index = m.start();
//                }
//
//                src = src.substring(0, index);
//                src = src.substring(src.lastIndexOf("/**"));
//                // System.out.println(src);
//
//                /**
//                 * 1、做/* 注释的正则匹配
//                 *
//                 * 
//                 * 通过渐进法做注释的正则匹配，因为/*注释总是成对出现 当匹配到一个/*时总会在接下来的内容中会首先匹配到"*\\/",
//                 * 因此在获取对应的"*\\/"注释时只需要从当前匹配的/*开始即可， 下一次匹配时只需要从上一次匹配的结尾开始即可
//                 * （这样对于大文本可以节省匹配效率）—— 这就是渐进匹配法
//                 *
//                 * 
//                 */
//                Pattern leftpattern = Pattern.compile("/\\*");
//                Matcher leftmatcher = leftpattern.matcher(src);
//                Pattern rightpattern = Pattern.compile("\\*/");
//                Matcher rightmatcher = rightpattern.matcher(src);
//                sb = new StringBuilder();
//                /**
//                 * begin 变量用来做渐进匹配的游标 {@value} 初始值为文件开头
//                 **/
//                int begin = 0;
//                while (leftmatcher.find(begin)) {
//                    rightmatcher.find(leftmatcher.start());
//                    sb.append(src.substring(leftmatcher.start(), rightmatcher.end()));
//                    /** 为输出时格式的美观 **/
//                    sb.append('\n');
//                    begin = rightmatcher.end();
//                }
//                // System.out.println(sb.toString());
//
//                StringReader stringReader = new StringReader(sb.toString());
//                BufferedReader reader = new BufferedReader(stringReader);
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    line = line.replaceAll("/", "").replaceAll("\\*", "").trim();
//                    if (line.indexOf("@") == -1 && StringUtils.isNotEmpty(line)) {
//                        // System.out.println(line + "----");
//                        result += line;
//                    }
//                }
//
//            } catch (IOException e) {
//                System.out.println("文件读取失败");
//            } finally {
//                breader.close();
//                // freader.close();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("文件不存在");
//        } catch (IOException e) {
//            System.out.println("文件读取失败");
//        }
//        return result;
//    }
//
//    public static String getInterfaceFieldComment(Class class1, String fieldName) {
//        String result = "";
//        String fileName = class1.getResource(".").getPath().replaceAll("target/classes", "src/main/java")
//                + class1.getSimpleName() + ".java";
//        try {
//            // FileReader freader = new FileReader(fileName);
//            InputStream in = new FileInputStream(fileName);
//            InputStreamReader inputStreamReader = new InputStreamReader(in, "utf-8");
//            BufferedReader breader = new BufferedReader(inputStreamReader);
//            StringBuilder sb = new StringBuilder();
//            try {
//                String temp = "";
//                /**
//                 * 读取文件内容，并将读取的每一行后都不加\n 其目的是为了在解析双反斜杠（//）注释时做注释中止符
//                 */
//                while ((temp = breader.readLine()) != null) {
//                    sb.append(temp);
//                    sb.append('\n');
//                }
//                String src = sb.toString();
//
//                Pattern p = Pattern.compile("public.*" + fieldName + ".*;");
//                Matcher m = p.matcher(src);
//
//                int index = src.length() - 1;
//                while (m.find()) {
//                    // System.out.println(m.start());
//                    index = m.start();
//                }
//
//                src = src.substring(0, index);
//                src = src.substring(src.lastIndexOf("//"));
//                // System.out.println(src);
//
//                /**
//                 * 2、对//注释进行匹配（渐进匹配法） 匹配方法是 // 总是与 \n 成对出现
//                 */
//                int begin = 0;
//                Pattern leftpattern1 = Pattern.compile("//");
//                Matcher leftmatcher1 = leftpattern1.matcher(src);
//                Pattern rightpattern1 = Pattern.compile("\n");
//                Matcher rightmatcher1 = rightpattern1.matcher(src);
//                sb = new StringBuilder();
//                while (leftmatcher1.find(begin)) {
//                    rightmatcher1.find(leftmatcher1.start());
//                    sb.append(src.substring(leftmatcher1.start(), rightmatcher1.end()));
//                    begin = rightmatcher1.end();
//                }
//                // System.out.println(sb.toString());
//                result = sb.toString().replaceAll("//", "").trim();
//                result = result.trim();
//            } catch (IOException e) {
//                System.out.println("文件读取失败");
//            } finally {
//                breader.close();
//                // freader.close();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("文件不存在");
//        } catch (IOException e) {
//            System.out.println("文件读取失败");
//        }
//        return result;
//    }
//
//    public void test() {
//        String fileName = BookmarkController.class.getResource(".").getPath().replaceAll("target/classes",
//                "src/main/java") + BookmarkController.class.getSimpleName() + ".java";
//        try {
//            FileReader freader = new FileReader(fileName);
//            BufferedReader breader = new BufferedReader(freader);
//            StringBuilder sb = new StringBuilder();
//            try {
//                String temp = "";
//                /**
//                 * 读取文件内容，并将读取的每一行后都不加\n 其目的是为了在解析双反斜杠（//）注释时做注释中止符
//                 */
//                while ((temp = breader.readLine()) != null) {
//                    sb.append(temp);
//                    sb.append('\n');
//                }
//                String src = sb.toString();
//                /**
//                 * 1、做/* 注释的正则匹配
//                 *
//                 * 
//                 * 通过渐进法做注释的正则匹配，因为/*注释总是成对出现 当匹配到一个/*时总会在接下来的内容中会首先匹配到"*\\/",
//                 * 因此在获取对应的"*\\/"注释时只需要从当前匹配的/*开始即可， 下一次匹配时只需要从上一次匹配的结尾开始即可
//                 * （这样对于大文本可以节省匹配效率）—— 这就是渐进匹配法
//                 *
//                 * 
//                 */
//                Pattern leftpattern = Pattern.compile("/\\*");
//                Matcher leftmatcher = leftpattern.matcher(src);
//                Pattern rightpattern = Pattern.compile("\\*/");
//                Matcher rightmatcher = rightpattern.matcher(src);
//                sb = new StringBuilder();
//                /**
//                 * begin 变量用来做渐进匹配的游标 {@value} 初始值为文件开头
//                 **/
//                int begin = 0;
//                while (leftmatcher.find(begin)) {
//                    rightmatcher.find(leftmatcher.start());
//                    sb.append(src.substring(leftmatcher.start(), rightmatcher.end()));
//                    /** 为输出时格式的美观 **/
//                    sb.append('\n');
//                    begin = rightmatcher.end();
//                }
//                System.out.println(sb.toString());
//                /**
//                 * 2、对//注释进行匹配（渐进匹配法） 匹配方法是 // 总是与 \n 成对出现
//                 */
//                begin = 0;
//                Pattern leftpattern1 = Pattern.compile("//");
//                Matcher leftmatcher1 = leftpattern1.matcher(src);
//                Pattern rightpattern1 = Pattern.compile("\n");
//                Matcher rightmatcher1 = rightpattern1.matcher(src);
//                sb = new StringBuilder();
//                while (leftmatcher1.find(begin)) {
//                    rightmatcher1.find(leftmatcher1.start());
//                    sb.append(src.substring(leftmatcher1.start(), rightmatcher1.end()));
//                    begin = rightmatcher1.end();
//                }
//                System.out.println(sb.toString());
//            } catch (IOException e) {
//                System.out.println("文件读取失败");
//            } finally {
//                breader.close();
//                freader.close();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("文件不存在");
//        } catch (IOException e) {
//            System.out.println("文件读取失败");
//        }
//    }
//
//}
