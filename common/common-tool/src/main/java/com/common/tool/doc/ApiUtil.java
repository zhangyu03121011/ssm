//package com.common.tool.doc;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.lang.reflect.AnnotatedType;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.lang.reflect.TypeVariable;
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
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.common.base.annotation.Validate;
//import com.common.base.model.base.BaseModel;
//import com.common.base.vo.base.PageListVo;
//import com.common.base.vo.base.ResultVo;
//import com.common.tool.doc.vo.FunctionVo;
//import com.common.tool.doc.vo.ModuleVo;
//import com.common.tool.doc.vo.ParamVo;
//import com.common.tool.doc.vo.ProjectVo;
//import com.common.util.ReflectUtil;
//
//public class ApiUtil {
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
//
//                List<ParamVo> reqParamVos = new ArrayList<>();
//                List<ParamVo> resParamVos = new ArrayList<>();
//
//                // 请求参数
//                Class[] classArray = method.getParameterTypes();
//                List<Integer> strList = new ArrayList<>();
//                int i = 0;
//                for (Class class2 : classArray) {
//                    if (!class2.getSimpleName().equalsIgnoreCase(HttpServletRequest.class.getSimpleName())) {
//
//                        if (class2.getSimpleName().equalsIgnoreCase(String.class.getSimpleName())) {
//
//                            strList.add(i);
//
//                        } else if (class2.getSimpleName().equalsIgnoreCase(PageListVo.class.getSimpleName())) {
//
//                            ParamVo paramVo = new ParamVo();
//                            paramVo.setParamName("currPage");
//                            paramVo.setParamDesc("第几页");
//                            paramVo.setParamType("int");
//                            reqParamVos.add(paramVo);
//
//                        } else if (class2.getSimpleName().equalsIgnoreCase(List.class.getSimpleName())) {
//
//                            // System.out.println(method.getDeclaringClass() +
//                            // "--" + method.getName());
//                            TypeVariable<Method>[] typeVariable = method.getTypeParameters();
//                            for (TypeVariable<Method> typeVariable2 : typeVariable) {
//                                // System.out.println(typeVariable2.getName());
//                            }
//
//                            Type[] type = method.getGenericParameterTypes();
//                            for (Type type2 : type) {
//                                // System.out.println(type2.getClass().getSimpleName());
//                                if (type2 instanceof ParameterizedType) {
//                                    ParameterizedType aType = (ParameterizedType) type2;
//                                    // System.out.println(aType);
//                                    Type[] fieldArgTypes = aType.getActualTypeArguments();
//                                    for (Type fieldArgType : fieldArgTypes) {
//                                        // System.out.println(fieldArgType);
//                                        Class fieldArgClass = null;
//                                        if (fieldArgType.getTypeName().equals("T")) {
//                                            fieldArgClass = ReflectUtil.getInstance().getClassGenricType(class1);
//                                            // System.out.println(fieldArgClass);
//                                        } else {
//                                            fieldArgClass = (Class) fieldArgType;
//                                        }
//
//                                        if (fieldArgClass.getSimpleName()
//                                                .equalsIgnoreCase(String.class.getSimpleName())) {
//
//                                            strList.add(i);
//
//                                        } else {
//                                            List<Field> fields = com.idbear.common.util.AnnotationUtil.getInstance()
//                                                    .getAllField(fieldArgClass);
//                                            for (Field field : fields) {
//
//                                                if (commExcludeFieldList.contains(field.getName())) {
//                                                    continue;
//                                                }
//
//                                                if (!class1.getSimpleName().equals(UserController.class.getSimpleName())
//                                                        && !class1.getSimpleName()
//                                                                .equals(CompanyController.class.getSimpleName())
//                                                        && !class1.getSimpleName()
//                                                                .equals(LoginController.class.getSimpleName())
//                                                        && commExcludeUserFieldList.contains(field.getName())) {
//                                                    continue;
//                                                }
//
//                                                if (method.getName().equals("save")
//                                                        && commExcludeSaveMethodList.contains(field.getName())) {
//                                                    continue;
//                                                }
//
//                                                if (field.getType().getSimpleName().equals(List.class.getSimpleName())
//                                                        || (!field.getType().getSimpleName()
//                                                                .equals(Integer.class.getSimpleName())
//                                                                && !field.getType().getSimpleName()
//                                                                        .equals(Long.class.getSimpleName())
//                                                                && !field.getType().getSimpleName()
//                                                                        .equalsIgnoreCase(Boolean.class.getSimpleName())
//                                                                && !field.getType().getSimpleName()
//                                                                        .equalsIgnoreCase("int")
//                                                                && field.getType()
//                                                                        .newInstance() instanceof BaseModel)) {
//                                                    continue;
//                                                }
//                                                // System.out.println(field);
//
//                                                // System.out.println(fieldArgClass);
//                                                ParamVo paramVo = new ParamVo();
//                                                paramVo.setParamName("list." + field.getName());
//                                                paramVo.setParamDesc(CommentUtil
//                                                        .getFieldComment(field.getDeclaringClass(), field.getName()));
//                                                paramVo.setParamType(field.getType().getSimpleName());
//
//                                                if (field.getDeclaredAnnotation(Validate.class) != null) {
//                                                    paramVo.setIsRequire("1");
//                                                }
//
//                                                reqParamVos.add(paramVo);
//                                            }
//                                            // System.out.println("fieldArgClass
//                                            // = "
//                                            // + fieldArgClass.getSimpleName());
//                                        }
//
//                                    }
//                                }
//
//                            }
//
//                            AnnotatedType[] annotatedTypes = method.getAnnotatedParameterTypes();
//                            for (AnnotatedType annotatedType : annotatedTypes) {
//                                // System.out.println(annotatedType.getType());
//                            }
//
//                            Parameter[] parameters = method.getParameters();
//                            for (Parameter parameter : parameters) {
//                                // System.out.println(parameter.getParameterizedType());
//                            }
//
//                        } else {
//
//                            // System.out.println(method.getName() + "---" +
//                            // method.getDeclaringClass().getSimpleName()
//                            // + "---" + (class1.getSimpleName()));
//                            if (!method.getDeclaringClass().getSimpleName().equals(class1.getSimpleName())) {
//                                // fieldArgClass =
//                                // ReflectUtil.getInstance().getClassGenricType(class1);
//                                // System.out.println(class2);
//
//                                Type[] type = method.getGenericParameterTypes();
//                                for (Type type2 : type) {
//                                    // System.out.println(method.getName() +
//                                    // "---"
//                                    // +
//                                    // method.getDeclaringClass().getSimpleName()
//                                    // + "---" + type2.getTypeName());
//                                    if (type2.getTypeName().equals("T")) {
//                                        class2 = ReflectUtil.getInstance().getClassGenricType(class1);
//                                        // System.out.println(method.getName()+"----"+class2);
//                                    }
//                                }
//                                if (type[i] instanceof ParameterizedType) {
//                                    // System.out.println(
//                                    // method.getName() + "---" +
//                                    // method.getDeclaringClass().getSimpleName()
//                                    // + "---" );
//                                    ParameterizedType aType = (ParameterizedType) type[i];
//                                    Type[] fieldArgTypes = aType.getActualTypeArguments();
//                                    for (Type fieldArgType : fieldArgTypes) {
//                                        if (fieldArgType.getTypeName().equals("T")) {
//                                            class2 = ReflectUtil.getInstance().getClassGenricType(class1);
//                                            // System.out.println(method.getName()+"----"+class2);
//                                        }
//
//                                    }
//
//                                }
//
//                            }
//
//                            List<Field> fields = com.idbear.common.util.AnnotationUtil.getInstance()
//                                    .getAllField(class2);
//                            for (Field field : fields) {
//                                // System.out.println(field);
//
//                                if (commExcludeFieldList.contains(field.getName())) {
//                                    continue;
//                                }
//
//                                if (!class1.getSimpleName().equals(UserController.class.getSimpleName())
//                                        && !class1.getSimpleName().equals(CompanyController.class.getSimpleName())
//                                        && !class1.getSimpleName().equals(LoginController.class.getSimpleName())
//                                        && commExcludeUserFieldList.contains(field.getName())) {
//                                    continue;
//                                }
//
//                                if (method.getName().equals("save")
//                                        && commExcludeSaveMethodList.contains(field.getName())) {
//                                    continue;
//                                }
//
//                                if (field.getType().getSimpleName().equals(List.class.getSimpleName())
//                                        || (!field.getType().getSimpleName().equals(Integer.class.getSimpleName())
//                                                && !field.getType().getSimpleName().equals(Long.class.getSimpleName())
//                                                && !field.getType().getSimpleName()
//                                                        .equalsIgnoreCase(Boolean.class.getSimpleName())
//                                        && !field.getType().getSimpleName().equalsIgnoreCase("int")
//                                        && field.getType().newInstance() instanceof BaseModel)) {
//                                    continue;
//                                }
//
//                                ParamVo paramVo = new ParamVo();
//                                paramVo.setParamName(field.getName());
//
//                                if (!method.getDeclaringClass().getSimpleName().equals(class1.getSimpleName())) {
//                                    // System.out.println(class2.getSimpleName()
//                                    // + "---" + field.getDeclaringClass()
//                                    // + "---" + field.getName() + "---"
//                                    // + CommentUtil.getFieldComment(class2,
//                                    // field.getName()));
//                                }
//
//                                if (field.getDeclaredAnnotation(Validate.class) != null) {
//                                    paramVo.setIsRequire("1");
//                                }
//
//                                paramVo.setParamDesc(
//                                        CommentUtil.getFieldComment(field.getDeclaringClass(), field.getName()));
//                                paramVo.setParamType(field.getType().getSimpleName());
//                                reqParamVos.add(paramVo);
//                            }
//
//                        }
//
//                        // System.out.println(class2.getName());
//                    }
//                    i++;
//                }
//
//                if (CollectionUtils.isNotEmpty(strList)) {
//                    String[] paramNames = MethodUtil.getMethodParamNames(method.getDeclaringClass(), method.getName(),
//                            method.getParameterTypes());
//                    for (Integer integer : strList) {
//                        ParamVo paramVo = new ParamVo();
//                        paramVo.setParamName(paramNames[integer]);
//                        // System.out.println(paramNames[integer]);
//                        // paramVo.setParamDesc(paramDesc);
//                        paramVo.setParamType(String.class.getSimpleName());
//                        reqParamVos.add(paramVo);
//                    }
//                }
//
//                functionVo.setReqParams(reqParamVos);
//
//                // 获取返回值
//                Class returnClass = method.getReturnType();
//                Class returnObjClass = null;
//                if (!returnClass.getSimpleName().equals(ResultVo.class.getSimpleName())) {
//                    // System.out.println(method.getName() + "--" +
//                    // returnClass);
//                }
//                // System.out.println(method.getGenericReturnType().getTypeName());
//                if (method.getGenericReturnType() instanceof ParameterizedType) {
//                    // System.out.println(
//                    // method.getName() + "---" +
//                    // method.getDeclaringClass().getSimpleName()
//                    // + "---" );
//                    ParameterizedType aType = (ParameterizedType) method.getGenericReturnType();
//                    Type[] fieldArgTypes = aType.getActualTypeArguments();
//                    for (Type fieldArgType : fieldArgTypes) {
//                        // System.out.println(fieldArgType);
//                        if (fieldArgType.getTypeName().equals("T")) {
//                            returnObjClass = ReflectUtil.getInstance().getClassGenricType(class1);
//                            // System.out.println(method.getName() + "----" +
//                            // class2);
//                        } else {
//                            // System.out.println(fieldArgType);
//                            returnObjClass = (Class) fieldArgType;
//                        }
//
//                    }
//
//                }
//                // System.out.println(method.getGenericReturnType().getTypeName()
//                // + "--" + returnObjClass);
//
//                Field[] fields = returnClass.getDeclaredFields();
//                for (Field field : fields) {
//                    if (commExcludeResultFieldList.contains(field.getName())) {
//                        continue;
//                    }
//
//                    ParamVo paramVo = new ParamVo();
//                    paramVo.setParamName(field.getName());
//                    paramVo.setParamDesc(CommentUtil.getFieldComment(field.getDeclaringClass(), field.getName()));
//                    paramVo.setParamType(field.getType().getSimpleName());
//                    resParamVos.add(paramVo);
//                }
//
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
