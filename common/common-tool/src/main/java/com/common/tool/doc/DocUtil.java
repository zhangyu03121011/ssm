//package com.common.tool.doc;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.common.sql.model.TableVO;
//import com.common.tool.doc.vo.DictTypeVo;
//import com.common.tool.doc.vo.ProjectVo;
//
//import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapper;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
//
//public class DocUtil {
//    private Configuration configure = null;
//
//    public DocUtil() {
//        configure = new Configuration();
//        configure.setDefaultEncoding("utf-8");
//    }
//
//    /**
//     * 根据Doc模板生成word文件
//     * 
//     * @param dataMap
//     *            Map 需要填入模板的数据
//     * @param fileName
//     *            文件名称
//     * @param savePath
//     *            保存路径
//     */
//    public void createDoc(Map<String, Object> dataMap, String downloadType, String savePath) {
//        try {
//            // 加载需要装填的模板
//            Template template = null;
//            // 加载模板文件
//            configure.setClassForTemplateLoading(this.getClass(), "/com/pms/tools/doc");
//            // 设置对象包装器
//            configure.setObjectWrapper(new DefaultObjectWrapper());
//            // 设置异常处理器
//            configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
//            // 定义Template对象,注意模板类型名字与downloadType要一致
//            template = configure.getTemplate(downloadType);
//            // 输出文档
//            File outFile = new File(savePath);
//            Writer out = null;
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
//            template.process(dataMap, out);
//            outFile.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void createDict() {
//        String dbName = "idbear";
//
//        // local表
//        List<TableVO> tables = DbTools.getTables("root", "idbear_2016_mysql_!QAZ2wsx",
//                "jdbc:mysql://192.168.1.160:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF8");
//
//        DocUtil docUtil = new DocUtil();
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("companyName", "xxxx有限公司");
//        dataMap.put("companyNameEn", "ShenZhen idbear Co.,Ltd.");
//        dataMap.put("companyUrl", "http://www.id-bear.com");
//        dataMap.put("docName", "谷熊浏览器数据字典");
//        dataMap.put("tables", tables);
//        docUtil.createDoc(dataMap, "template-V2.0.xml", "D://谷熊浏览器数据字典-" + dbName + ".doc");
//
//        // local表
//        dbName = "code";
//        tables = DbTools.getTables("root", "idbear_2016_mysql_!QAZ2wsx",
//                "jdbc:mysql://192.168.1.160:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF8");
//
//        dataMap.put("tables", tables);
//        docUtil.createDoc(dataMap, "template-V2.0.xml", "D://谷熊浏览器数据字典-" + dbName + ".doc");
//
//        // local表
//        dbName = "admin";
//        tables = DbTools.getTables("root", "idbear_2016_mysql_!QAZ2wsx",
//                "jdbc:mysql://192.168.1.160:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF8");
//
//        dataMap.put("tables", tables);
//        docUtil.createDoc(dataMap, "template-V2.0.xml", "D://谷熊浏览器数据字典-" + dbName + ".doc");
//
//        // local表
//        dbName = "report";
//        tables = DbTools.getTables("root", "idbear_2016_mysql_!QAZ2wsx",
//                "jdbc:mysql://192.168.1.160:3306/" + dbName + "?useUnicode=true&characterEncoding=UTF8");
//
//        dataMap.put("tables", tables);
//        docUtil.createDoc(dataMap, "template-V2.0.xml", "D://谷熊浏览器数据字典-" + dbName + ".doc");
//    }
//
//    public static void createApi() throws Exception {
//
//        List<ProjectVo> projectVos = ApiUtil.getApiList();
//        List<DictTypeVo> dictTypeVos = DictUtil.getDictTypeList();
//        // for (ProjectVo projectVo : projectVos) {
//        // List<ModuleVo> moduleVos = projectVo.getModuleVos();
//        // for (ModuleVo moduleVo : moduleVos) {
//        // System.out.println(moduleVo);
//        // }
//        // }
//
//        DocUtil docUtil = new DocUtil();
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("companyName", "xxxx有限公司");
//        dataMap.put("companyNameEn", "ShenZhen idbear Co.,Ltd.");
//        dataMap.put("companyUrl", "http://www.id-bear.com");
//        dataMap.put("docName", "谷熊浏览器");
//        dataMap.put("projectList", projectVos);
//        dataMap.put("dictTypeVos", dictTypeVos);
//        docUtil.createDoc(dataMap, "template-api-V1.0.xml", "D://谷熊浏览器Api.doc");
//    }
//
//    public static void createDevApi() throws Exception {
//
//        List<ProjectVo> projectVos = ApiDevUtil.getApiList();
//        List<DictTypeVo> dictTypeVos = DictUtil.getDictTypeList();
//
//        DocUtil docUtil = new DocUtil();
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("companyName", "xxxx有限公司");
//        dataMap.put("companyNameEn", "ShenZhen idbear Co.,Ltd.");
//        dataMap.put("companyUrl", "http://www.id-bear.com");
//        dataMap.put("docName", "谷熊浏览器");
//        dataMap.put("projectList", projectVos);
//        dataMap.put("dictTypeVos", dictTypeVos);
//        docUtil.createDoc(dataMap, "template-api-dev-V1.0.xml", "D://谷熊浏览器Api（开发）.doc");
//    }
//
//    public static void main(String[] args) throws Exception {
//        // createDict();
//
//        // List<Class> list = ClassUtil.getClasssFromJarFile(
//        // "E://Svn_Admin/idbear-app-manager/target/idbear-app-manager.jar",
//        // "com/idbear/app/manager/controller");
//        // for (Class class1 : list) {
//        // System.out.println(class1);
//        // }
//
//        // File file=new
//        // File("E://Svn/idbear/Trunk/Project/idbear/idbear-app-manager/target/classes/com/idbear/app/manager/inteceptor/TokenInterceptor.class");
//        //
//        // URLClassLoader classLoader=new URLClassLoader(new
//        // URL[]{file.toURL()});
//        // System.out.println(classLoader.loadClass("com.idbear.app.manager.inteceptor.TokenInterceptor"));
//
//        // List<Class> list = new ArrayList<>();
//        // ClassUtil.findClassInPackageByFileNew("com.idbear.app.manager.controller",
//        // "E://Svn/idbear/Trunk/Project/idbear/idbear-app-manager/target/classes",
//        // true, list);
//        // for (Class class1 : list) {
//        // System.out.println(class1);
//        // }
//
//        // createApi();
//
//        createDevApi();
//    }
//
//}