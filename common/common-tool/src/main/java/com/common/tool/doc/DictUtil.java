//package com.pms.tools.doc;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.collections.CollectionUtils;
//
//import com.common.base.constant.ResultConstant;
//import com.common.tool.doc.vo.DictIndex;
//import com.common.tool.doc.vo.DictTypeVo;
//import com.common.tool.doc.vo.DictVo;
//import com.common.util.Md5Util;
//
//public class DictUtil {
//
//    public static void main(String[] args) throws Exception {
//
//        // getDictTypeList();
//        //getInterfaceFieldComment(SocketConstant.class);
//        // getEnumList(appKeySecretConstant.class);
//        // getDictTypeList();
//    }
//
//    public static List<DictTypeVo> getDictTypeList() throws Exception {
//        List<DictTypeVo> dictTypeVos = new ArrayList<>();
//
//        DictTypeVo dictTypeVo = getDictList(ResultConstant.class);
//        dictTypeVos.add(dictTypeVo);
//
//        List<Class> classes = ApiUtil.getClasssFromPackage("com.idbear.common.constant");
//        for (Class class1 : classes) {
//            if (class1.isInterface()) {
//
//                List<DictTypeVo> dictTypeVos2 = getInterfaceFieldComment(class1);
//                if (CollectionUtils.isNotEmpty(dictTypeVos2)) {
//                    dictTypeVos.addAll(dictTypeVos2);
//                }
//
//            } else if (class1.isEnum()) {
//
//                DictTypeVo dictTypeVo2 = getEnumList(class1);
//                dictTypeVos.add(dictTypeVo2);
//
//            }
//
//        }
//
//        return dictTypeVos;
//    }
//
//    public static DictTypeVo getDictList(Class cls) throws Exception {
//        DictTypeVo dictTypeVo = new DictTypeVo();
//        List<DictVo> dictVos = new ArrayList<>();
//        Field[] field = cls.getDeclaredFields();
//        for (Field field2 : field) {
//            DictVo dictVo = new DictVo();
//            dictVo.setDictName(field2.getName().toLowerCase());
//            dictVo.setDictValue(field2.get(field2.getName()).toString());
//            dictVo.setDictDesc(CommentUtil.getInterfaceFieldComment(cls, field2.getName()));
//            dictVos.add(dictVo);
//        }
//        dictTypeVo.setDictTypeName(CommentUtil.getClassComment(cls));
//        dictTypeVo.setDictVos(dictVos);
//        return dictTypeVo;
//    }
//
//    public static DictTypeVo getEnumList(Class cls) throws Exception {
//        DictTypeVo dictTypeVo = new DictTypeVo();
//        List<DictVo> dictVos = new ArrayList<>();
//        Object[] obj = cls.getEnumConstants();
//
//        Method method = null;
//        Method[] methods = cls.getDeclaredMethods();
//        for (Method method2 : methods) {
//            if (Modifier.isStatic(method2.getModifiers())) {
//                method = method2;
//            }
//        }
//        for (Object object : obj) {
//            // System.out.println(cls.getSimpleName() + "---" + method.getName()
//            // + "---" + object + "--"
//            // + method.invoke(cls, object.toString().toLowerCase()));
//            DictVo dictVo = new DictVo();
//            dictVo.setDictName(object.toString().toLowerCase());
//
//            if (cls.getSimpleName().equals(OptTypeConstant.class.getSimpleName())) {
//                dictVo.setDictValue(OptTypeConstant.valueOf(object.toString()).getClass().getDeclaredMethod("getType")
//                        .invoke(OptTypeConstant.valueOf(object.toString())).toString());
//            } else {
//                dictVo.setDictValue(method.invoke(cls, object.toString().toLowerCase()).toString());
//            }
//
//            if (cls.getSimpleName().equals(appKeySecretConstant.class.getSimpleName())) {
//                dictVo.setDictValue(Md5Util.getInstance().md5Encode(dictVo.getDictValue()));
//            }
//
//            dictVos.add(dictVo);
//        }
//        dictTypeVo.setDictTypeName(CommentUtil.getClassComment(cls));
//        dictTypeVo.setDictVos(dictVos);
//        return dictTypeVo;
//    }
//
//    public static List<DictTypeVo> getInterfaceFieldComment(Class class1) {
//        List<DictTypeVo> dictTypeVos = new ArrayList<>();
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
//                Pattern p = Pattern.compile("//.*：.*，.*");
//                Matcher m = p.matcher(src);
//
//                List<DictIndex> dictIndexs = new ArrayList<>();
//                int i = 0;
//                while (m.find()) {
//
//                    DictIndex dictIndex = new DictIndex();
//                    dictIndex.setStart(m.start());
//                    dictIndex.setEnd(m.end());
//
//                    dictIndexs.add(dictIndex);
//
//                    if (i > 0) {
//                        dictIndexs.get(i - 1).setEnd(m.start());
//                    }
//                    // System.out.println(m.start());
//                   // System.out.println(m.start() + "--" + m.end());
//                    // src = src.substring(src.lastIndexOf("//"));
//                    i++;
//                }
//
//                if (i == 0) {
//                    return null;
//                }
//
//                int index = src.substring(dictIndexs.get(i - 1).getEnd()).indexOf("//");
//                if (index == -1) {
//                    index = src.substring(dictIndexs.get(i - 1).getEnd()).indexOf("}");
//                }
//                dictIndexs.get(i - 1).setEnd(dictIndexs.get(i - 1).getEnd() + index);
//
//                // System.out.println(dictIndexs);
//
//                for (DictIndex dictIndex : dictIndexs) {
//
//                    String str = src.substring(dictIndex.getStart(), dictIndex.getEnd());
//                    // System.out.println(str);
//
//                    /**
//                     * 2、对//注释进行匹配（渐进匹配法） 匹配方法是 // 总是与 \n 成对出现
//                     */
//                    int begin = 0;
//                    Pattern leftpattern1 = Pattern.compile("//");
//                    Matcher leftmatcher1 = leftpattern1.matcher(str);
//                    Pattern rightpattern1 = Pattern.compile("\n");
//                    Matcher rightmatcher1 = rightpattern1.matcher(str);
//                    sb = new StringBuilder();
//                    while (leftmatcher1.find(begin)) {
//                        rightmatcher1.find(leftmatcher1.start());
//                        sb.append(str.substring(leftmatcher1.start(), rightmatcher1.end()));
//                        begin = rightmatcher1.end();
//                    }
//                    // System.out.println(sb.toString());
//                    result = sb.toString().replaceAll("//", "").trim();
//                    result = result.trim();
//                   // System.out.println(result);
//
//                    DictTypeVo dictTypeVo = new DictTypeVo();
//                    dictTypeVo.setDictTypeName(result.split("：")[0]);
//
//                    List<DictVo> dictVos = new ArrayList<>();
//                    String[] dictArr = (result.split("：")[1]).split("，");
//                    for (String string : dictArr) {
//                        DictVo dictVo = new DictVo();
//
//                        begin = 0;
//
//                        if (string.indexOf(":") != -1) {
//                            leftpattern1 = Pattern.compile("String.*\"" + string.split(":")[0] + "\".*;");
//                        } else {
//                            leftpattern1 = Pattern.compile("String.*\"" + string.split("-")[0] + "\".*;");
//                        }
//
//                        leftmatcher1 = leftpattern1.matcher(str);
//                        rightpattern1 = Pattern.compile("\n");
//                        rightmatcher1 = rightpattern1.matcher(str);
//                        sb = new StringBuilder();
//                        while (leftmatcher1.find(begin)) {
//                            rightmatcher1.find(leftmatcher1.start());
//                            sb.append(str.substring(leftmatcher1.start(), rightmatcher1.end()));
//                            begin = rightmatcher1.end();
//                        }
//                        // System.out.println(sb.toString());
//                        result = sb.toString().replaceAll("String", "").trim();
//                        result = result.trim();
//                       // System.out.println(result);
//
//                        dictVo.setDictName(result.split("=")[0].toLowerCase());
//
//                        if (string.indexOf(":") != -1) {
//                            dictVo.setDictValue(string.split(":")[0]);
//                            dictVo.setDictDesc(string.split(":")[1]);
//                        } else {
//                            dictVo.setDictValue(string.split("-")[0]);
//                            dictVo.setDictDesc(string.split("-")[1]);
//                        }
//
//                        dictVos.add(dictVo);
//                    }
//                    dictTypeVo.setDictVos(dictVos);
//                    dictTypeVos.add(dictTypeVo);
//                    // System.out.println(dictTypeVos);
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
//        return dictTypeVos;
//    }
//
//}
