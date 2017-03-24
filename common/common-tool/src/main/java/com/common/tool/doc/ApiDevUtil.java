//package com.common.tool.doc;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.common.base.model.base.BaseModel;
//import com.common.base.vo.base.PageListVo;
//import com.common.tool.doc.vo.FunctionVo;
//import com.common.tool.doc.vo.ModuleVo;
//import com.common.tool.doc.vo.ParamVo;
//import com.common.tool.doc.vo.ProjectVo;
//import com.common.util.ReflectUtil;
//
//public class ApiDevUtil {
//
//    private static List<String> commExcludeFieldList = new ArrayList<>();
//
//    private static List<String> commExcludeResultFieldList = new ArrayList<>();
//
//    private static List<String> commExcludeUserFieldList = new ArrayList<>();
//
//    private static List<String> commExcludeMethodList = new ArrayList<>();
//
//    private static List<String> commExcludeSaveMethodList = new ArrayList<>();
//
//    private static Map<String, List<String>> commExcludeMethodMap = new HashMap<>();
//
//    static {
//        commExcludeFieldList.add("serialVersionUID");
//        commExcludeFieldList.add("createTime");
//        commExcludeFieldList.add("createBy");
//        commExcludeFieldList.add("updateTime");
//        commExcludeFieldList.add("updateBy");
//        commExcludeFieldList.add("descr");
//        commExcludeFieldList.add("flag");
//        commExcludeFieldList.add("appId");
//        commExcludeFieldList.add("validFlag");
//        commExcludeFieldList.add("sessionId");
//
//        commExcludeMethodList.add("pagination");
//        commExcludeMethodList.add("socketList");
//
//        commExcludeResultFieldList.add("serialVersionUID");
//        commExcludeResultFieldList.add("priKey");
//        commExcludeResultFieldList.add("code");
//        commExcludeResultFieldList.add("token");
//
//        commExcludeUserFieldList.add("userId");
//        commExcludeUserFieldList.add("userType");
//
//        commExcludeSaveMethodList.add("state");
//        commExcludeSaveMethodList.add("isavailable");
//    }
//
//    public static void main(String[] args) throws Exception {
//        getApiList();
//    }
//
//    public static List<ProjectVo> getApiList() throws Exception {
//        List<ProjectVo> projectVos = new ArrayList<>();
//        ProjectVo projectAppVo = getApiVo("App系统", "com.idbear.app.manager.controller");
//        projectVos.add(projectAppVo);
//        ProjectVo projectSocketVo = getApiVo("Socket系统", "com.idbear.socket.manager.controller");
//        projectVos.add(projectSocketVo);
//        return projectVos;
//    }
//
//    public static ProjectVo getApiVo(String projectName, String packageName) throws Exception {
//        ProjectVo projectVo = new ProjectVo();
//        projectVo.setProjectName(projectName);
//        List<Class> clazzs = getClasssFromPackage(packageName);
//
//        List<ModuleVo> moduleVos = new ArrayList<>();
//        for (Class class1 : clazzs) {
//            ModuleVo moduleVo = new ModuleVo();
//            moduleVo.setModuleName(CommentUtil.getClassComment(class1));
//            moduleVo.setModuleMapping(AnnotationUtil.getAnnotationClassVal(class1, RequestMapping.class, "value"));
//
//            List<Method> methodsList = new ArrayList<>();
//            // 获取本身方法
//            Method[] methods = class1.getDeclaredMethods();
//            for (Method method : methods) {
//                methodsList.add(method);
//            }
//
//            // 获取公共方法
//            if (!class1.getSuperclass().getSimpleName().equals(Object.class.getSimpleName())) {
//                Method[] commMethods = class1.getSuperclass().getDeclaredMethods();
//                for (Method method : commMethods) {
//                    methodsList.add(method);
//                }
//            }
//
//            List<FunctionVo> functionVos = new ArrayList<>();
//            for (Method method : methodsList) {
//
//                if (commExcludeMethodList.contains(method.getName())) {
//                    continue;
//                }
//
//                if (method.getName().equals("batch")) {
//                    continue;
//                }
//
//                FunctionVo functionVo = new FunctionVo();
//                functionVo.setFunctionName(method.getName());
//                functionVo.setFunctionMapping(AnnotationUtil.getAnnotationMethodVal(method.getDeclaringClass(),
//                        RequestMapping.class, method.getName(), "value"));
//                functionVo.setFunctionDesc(CommentUtil.getMethodComment(method.getDeclaringClass(), method.getName()));
//                functionVo.setFunctionPkg(method.getDeclaringClass().getName());
//                
//                Class[] c=method.getExceptionTypes();
//                for (Class class2 : c) {
//                    functionVo.setFunctionException(class2.getSimpleName());
//                }
//
//                String methodStr = "";
//                int mod = method.getModifiers();
//                if (Modifier.isPublic(mod)) {
//                    methodStr += "public";
//                } else if (Modifier.isProtected(mod)) {
//                    methodStr += "protected";
//                } else {
//                    methodStr += "private";
//                }
//
//                List<ParamVo> reqParamVos = new ArrayList<>();
//                List<ParamVo> resParamVos = new ArrayList<>();
//
//                // 返回参数
//                String resParamName = method.getReturnType().getSimpleName();
//                if (method.getGenericReturnType() instanceof ParameterizedType) {
//                    ParameterizedType aType = (ParameterizedType) method.getGenericReturnType();
//                    Type[] fieldArgTypes = aType.getActualTypeArguments();
//                    for (Type fieldArgType : fieldArgTypes) {
//                        if (fieldArgType.getTypeName().equals("T")) {
//                            resParamName += "&lt;"
//                                    + ReflectUtil.getInstance().getClassGenricType(class1).getSimpleName() + "&gt;";
//                        } else {
//                            resParamName += "&lt;" + ((Class) fieldArgType).getSimpleName() + "&gt;";
//                        }
//
//                    }
//
//                    ParamVo paramVo = new ParamVo();
//                    paramVo.setParamName(resParamName);
//                    paramVo.setParamDesc(CommentUtil.getClassComment(method.getReturnType()));
//                    paramVo.setParamType(method.getReturnType().getSimpleName());
//                    resParamVos.add(paramVo);
//                }
//                methodStr += " " + resParamName + " " + method.getName() + "(";
//
//                // 请求参数
//                Class[] classArray = method.getParameterTypes();
//                int i = 0;
//                String[] paramNames = MethodUtil.getMethodParamNames(method.getDeclaringClass(), method.getName(),
//                        method.getParameterTypes());
//                for (Class class2 : classArray) {
//
//                    if (class2.getSimpleName().equals(BaseModel.class.getSimpleName())) {
//                        Type[] type = method.getGenericParameterTypes();
//                        for (Type type2 : type) {
//                            if (type2.getTypeName().equals("T")) {
//                                class2 = ReflectUtil.getInstance().getClassGenricType(class1);
//                            }
//                        }
//                        if (type[i] instanceof ParameterizedType) {
//                            ParameterizedType aType = (ParameterizedType) type[i];
//                            Type[] fieldArgTypes = aType.getActualTypeArguments();
//                            for (Type fieldArgType : fieldArgTypes) {
//                                if (fieldArgType.getTypeName().equals("T")) {
//                                    class2 = ReflectUtil.getInstance().getClassGenricType(class1);
//                                }
//                            }
//                        }
//                    }
//
//                    String listStr = "";
//                    if (class2.getSimpleName().equalsIgnoreCase(List.class.getSimpleName())
//                            || class2.getSimpleName().equalsIgnoreCase(PageListVo.class.getSimpleName())) {
//
//                        Type[] type = method.getGenericParameterTypes();
//                        for (Type type2 : type) {
//                            if (type2 instanceof ParameterizedType) {
//                                ParameterizedType aType = (ParameterizedType) type2;
//                                Type[] fieldArgTypes = aType.getActualTypeArguments();
//                                for (Type fieldArgType : fieldArgTypes) {
//                                    Class fieldArgClass = null;
//                                    if (fieldArgType.getTypeName().equals("T")) {
//                                        fieldArgClass = ReflectUtil.getInstance().getClassGenricType(class1);
//                                    } else {
//                                        fieldArgClass = (Class) fieldArgType;
//                                    }
//
//                                    listStr = "&lt;" + fieldArgClass.getSimpleName() + "&gt;";
//                                }
//                            }
//                        }
//                    }
//
//                    methodStr += class2.getSimpleName() + listStr + " " + paramNames[i];
//                    if (i != classArray.length - 1) {
//                        methodStr += ",";
//                    }
//
//                    ParamVo paramVo = new ParamVo();
//                    paramVo.setParamName(class2.getSimpleName() + listStr);
//                    if (!class2.getSimpleName().equals(HttpServletRequest.class.getSimpleName())
//                            && !class2.getSimpleName().equals(List.class.getSimpleName())
//                            && !class2.getSimpleName().equals(String.class.getSimpleName())) {
//                        paramVo.setParamDesc(CommentUtil.getClassComment(class2));
//                    }
//                    paramVo.setParamType(class2.getSimpleName());
//                    reqParamVos.add(paramVo);
//
//                    i++;
//                }
//                methodStr += ")";
//                functionVo.setFunctionMethod(methodStr);
//                // System.out.println(methodStr);
//
//                functionVo.setReqParams(reqParamVos);
//                functionVo.setResParams(resParamVos);
//                functionVos.add(functionVo);
//                // System.out.println(method.getName());
//            }
//            moduleVo.setFunctionVos(functionVos);
//            moduleVos.add(moduleVo);
//        }
//        projectVo.setModuleVos(moduleVos);
//        return projectVo;
//    }
//
//    public static List<Class> getClasssFromPackage(String pack) {
//        List<Class> clazzs = new ArrayList<Class>();
//
//        // 是否循环搜索子包
//        boolean recursive = true;
//
//        // 包名字
//        String packageName = pack;
//        // 包名对应的路径名称
//        String packageDirName = packageName.replace('.', '/');
//
//        Enumeration<URL> dirs;
//
//        try {
//            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
//            while (dirs.hasMoreElements()) {
//                URL url = dirs.nextElement();
//
//                String protocol = url.getProtocol();
//
//                if ("file".equals(protocol)) {
//                    System.out.println("file类型的扫描");
//                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
//                    findClassInPackageByFile(packageName, filePath, recursive, clazzs);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return clazzs;
//    }
//
//    public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive,
//            List<Class> clazzs) {
//        File dir = new File(filePath);
//        if (!dir.exists() || !dir.isDirectory()) {
//            return;
//        }
//        // 在给定的目录下找到所有的文件，并且进行条件过滤
//        File[] dirFiles = dir.listFiles(new FileFilter() {
//
//            @Override
//            public boolean accept(File file) {
//                boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
//                boolean acceptClass = file.getName().endsWith("class");// 接受class文件
//                return acceptDir || acceptClass;
//            }
//        });
//
//        for (File file : dirFiles) {
//            if (file.isDirectory()) {
//                findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
//            } else {
//                String className = file.getName().substring(0, file.getName().length() - 6);
//                try {
//                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
