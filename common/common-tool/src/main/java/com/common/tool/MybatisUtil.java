package com.common.tool;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.common.base.model.base.BaseModel;
import com.common.sql.factory.DBFactory;
import com.common.sql.model.ColumnVO;
import com.common.sql.model.TableVO;
import com.common.sql.proxy.DataSource;
import com.common.util.AnnotationUtil;

/**
 * Mybatis工具类
 * 
 * @author: HuiJunLuo
 * @date:2016年1月20日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class MybatisUtil {

    private String basePath = "H:\\mornsun\\project\\project_1224\\mornsun\\";

    private String modelPath = basePath + "mornsun-jsw-api\\src\\main\\java\\com\\mornsun\\jsw\\api\\model";

    private String apiPath = basePath + "mornsun-jsw-api\\src\\main\\java\\com\\mornsun\\jsw\\api\\api";

    private String voPath = basePath + "mornsun-jsw-api\\src\\main\\java\\com\\mornsun\\jsw\\api\\vo";

    private String daoPath = basePath + "mornsun-jsw-core\\src\\main\\java\\com\\mornsun\\jsw\\core\\dao";

    private String xmlPath = basePath + "mornsun-jsw-core\\src\\main\\resources\\com\\mornsun\\jsw\\core\\dao";

    private String apiImplPath = basePath + "mornsun-jsw-core\\src\\main\\java\\com\\mornsun\\jsw\\core\\api";

    private String servicePath = basePath + "mornsun-jsw-core\\src\\main\\java\\com\\mornsun\\jsw\\core\\service";

    private String serviceAppPath = basePath + "mornsun-app-core\\src\\main\\java\\com\\mornsun\\app\\core\\service";

    private String controllerAppPath = basePath
            + "mornsun-app-manager\\src\\main\\java\\com\\mornsun\\app\\manager\\controller";

    private static Map<String, String> nameMapping = new HashMap<>();

    public MybatisUtil() {
    }

    static {
        nameMapping.put("insert", "insert");
        nameMapping.put("deleteByPrimaryKey", "deleteById");
        nameMapping.put("selectByPrimaryKey", "getOneById");
        nameMapping.put("updateByPrimaryKeySelective", "update");
    }

    // @Test
    public void deleteGenerator() {
        try {
            FileUtils.deleteDirectory(new File(modelPath));
            FileUtils.deleteDirectory(new File(daoPath));
            FileUtils.deleteDirectory(new File(apiPath));
            FileUtils.deleteDirectory(new File(voPath));
            FileUtils.deleteDirectory(new File(apiImplPath));
            FileUtils.deleteDirectory(new File(servicePath));
            FileUtils.deleteDirectory(new File(xmlPath));
            FileUtils.deleteDirectory(new File(serviceAppPath));
            FileUtils.deleteDirectory(new File(controllerAppPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runProject() {
        // deleteGenerator();

        // modelDeal();
        // voDeal();
        // daoDeal();
        // apiDeal();
        // apiImplDeal();
        // serviceDeal();
        // serviceImplDeal();
        // xmlDeal();
        // serviceAppDeal();
        // controllerAppDeal();
    }

    public void generatorTableXml() {
        String tableList = "t_mornsun_apply_area, t_mornsun_atta, t_mornsun_bookmark, t_mornsun_brand, t_mornsun_catalog, t_mornsun_coupon, t_mornsun_expert_area, t_mornsun_position, t_mornsun_product, t_mornsun_product_apply_area, t_mornsun_product_atta, t_mornsun_product_base, t_mornsun_product_bookmark, t_mornsun_product_param, t_mornsun_product_replace, t_mornsun_search_record, t_mornsun_search_report, t_mornsun_search_suggest, t_mornsun_user, t_mornsun_user_answer, t_mornsun_user_area, t_mornsun_user_attention, t_mornsun_user_coupon, t_mornsun_user_course, t_mornsun_user_error, t_mornsun_user_expert, t_mornsun_user_favorite, t_mornsun_user_footprint, t_mornsun_user_inform, t_mornsun_user_invite, t_mornsun_user_message, t_mornsun_user_pay, t_mornsun_user_praise, t_mornsun_user_profit, t_mornsun_user_question, t_mornsun_user_recharge, t_mornsun_user_share, t_mornsun_user_suggest";
        String[] tableArray = tableList.split(",");
        String templates = "<table schema=\"mornsun\" tableName=\"@1@\" domainObjectName=\"@2@\"/>";
        String prefix = "t_mornsun_";
        String suffix = "Model";
        for (String table : tableArray) {
            String tmpArray[] = table.replace(prefix, "").split("_");
            String modelName = "";
            for (int i = 0; i < tmpArray.length; i++) {
                if (i == 0) {
                    modelName += tmpArray[0];
                } else {
                    modelName += StringUtils.capitalize(tmpArray[i]);
                }
            }
            System.out.println(templates.replaceAll("@1@", table.trim()).replaceAll("@2@", modelName.trim() + suffix));
        }
    }

    // @Test
    public void modelDeal() {
        String importPkg = "import com.common.base.model.base.BaseModel;";
        String datePkg = "import java.util.Date;";
        String validPkg = "import com.common.base.annotation.Validate;";
        Collection<File> modelFileList = FileUtils.listFiles(new File(modelPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return false;
            }

            public boolean accept(File file) {
                return false;
            }
        });

        List<Field> fieldList = AnnotationUtil.getInstance().getAllField(BaseModel.class);
        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();
            String className = file.getName().split("\\.")[0];
            String newClassName = StringUtils.capitalize(className);
            String folderName = file.getName().split("Model")[0].toLowerCase();
            if (folderName.startsWith("user")) {
                String[] arr = folderName.split("user");
                folderName = "user.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            } else if (folderName.startsWith("product")) {
                String[] arr = folderName.split("product");
                folderName = "product.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            } else if (folderName.startsWith("search")) {
                String[] arr = folderName.split("search");
                folderName = "search.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            }
            if (!file.getName().endsWith("ModelCriteria.java")) {
                boolean dateFlag = false;
                try {
                    LineIterator lineIterator = FileUtils.lineIterator(file);
                    String prevLine = null;
                    DataSource dataSource = DBFactory.dataSourceInstance();
                    TableVO tableVO = dataSource.getTableInfo("mornsun", null,
                            getTableName(file.getName().split("Model")[0]));
                    while (lineIterator.hasNext()) {
                        String line = lineIterator.nextLine();
                        boolean flag = false;
                        for (Field field : fieldList) {
                            if (line.contains(" " + field.getName() + ";")
                                    || line.contains(" set" + StringUtils.capitalize(field.getName()) + "(")
                                    || line.contains(" get" + StringUtils.capitalize(field.getName()) + "(")
                                    || line.contains(" " + field.getName() + " ")) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag && !line.contains("serialVersionUID")) {
                            continue;
                        }

                        if (line.matches("import\\s.*\\.Serializable;")) {
                            continue;
                        }

                        if (line.matches("public\\sclass\\s" + className + "\\s.*")) {
                            line = line.replaceAll(className, newClassName).replaceAll("implements Serializable", "");
                        }

                        if (line.startsWith("public class")) {
                            lines.add(validPkg);
                            lines.add(importPkg + "\n");
                            lines.add("/**");
                            lines.add(" * "
                                    + tableVO.getRemarks().trim().substring(0, tableVO.getRemarks().trim().length() - 1)
                                    + "Model");
                            lines.add(" *");
                            lines.add(" * @author: HuiJunLuo");
                            lines.add(" * @date:2015年12月31日");
                            lines.add(" * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有");
                            lines.add(" */");
                        }

                        if (line.matches(".*private\\s.*\\s.*;.*") && !line.contains("serialVersionUID")) {

                            String str = "";
                            List<ColumnVO> columnVOs = tableVO.getColumnVOs();
                            for (ColumnVO columnVO : columnVOs) {
                                if (columnVO.getColumnName().replaceAll("_", "")
                                        .equalsIgnoreCase(line.trim().split(" ")[2].split(";")[0])) {
                                    str = columnVO.getRemarks();
                                    break;
                                }
                            }
                            if (StringUtils.isNotEmpty(str)) {
                                lines.add("    /**");
                                lines.add("     * " + str);
                                lines.add("     */");
                            }
                            lines.add("    @Validate");
                        }

                        if (line.startsWith("package")) {
                            line = line.replaceAll(";", "." + folderName + ";");
                        }

                        if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                            continue;
                        }

                        if (prevLine != null && "".equals(prevLine.trim()) && line.trim().equals("}")
                                && lineIterator.hasNext()) {
                            continue;
                        }

                        if (line.trim().equals(datePkg)) {
                            dateFlag = true;
                        }

                        lines.add(line);
                        prevLine = line;
                    }
                    LineIterator.closeQuietly(lineIterator);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                if (dateFlag) {
                    boolean flag = false;
                    for (String line : lines) {
                        if (line.contains(" Date ")) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        for (String line : lines) {
                            if (line.trim().equals(datePkg)) {
                                lines.remove(line);
                                break;
                            }
                        }
                    }
                }

                // for (String line : lines) {
                // System.out.println(line);
                // }

                try {
                    FileUtils.deleteQuietly(file);
                    file = new File(modelPath + "\\" + newClassName + ".java");
                    FileUtils.writeLines(file, lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    LineIterator lineIterator = FileUtils.lineIterator(file);
                    while (lineIterator.hasNext()) {
                        String line = lineIterator.nextLine();
                        if (line.startsWith("package")) {
                            line = line.replaceAll(";", "." + folderName + ";");
                        }
                        if (line.contains(" class " + className + " ")) {
                            line = line.replaceAll(className, newClassName);
                        }
                        if (line.matches(".*\\spublic\\s" + className + "().*")) {
                            line = line.replaceAll(className, newClassName);
                        }
                        lines.add(line);
                    }
                    LineIterator.closeQuietly(lineIterator);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                // for (String line : lines) {
                // System.out.println(line);
                // }

                try {
                    FileUtils.deleteQuietly(file);
                    file = new File(modelPath + "\\" + newClassName + ".java");
                    FileUtils.writeLines(file, lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            File newFolder = new File(modelPath + "\\" + folderName.replaceAll("\\.", "\\\\\\\\"));
            try {
                FileUtils.moveFileToDirectory(file, newFolder, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getName() + "----" + folderName);
        }
    }

    // @Test
    public void voDeal() {
        try {
            FileUtils.deleteDirectory(new File(voPath));
            FileUtils.copyDirectory(new File(modelPath), new File(voPath), new IOFileFilter() {
                public boolean accept(File file) {
                    if (file.getName().matches(".*Criteria.*")) {
                        return false;
                    }
                    return true;
                }

                public boolean accept(File file, String s) {
                    return true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<File> modelFileList = FileUtils.listFiles(new File(voPath), new IOFileFilter() {

            public boolean accept(File file) {
                if (file.getName().matches(".*Vo.*")) {
                    return false;
                }
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String voName = className.replaceAll("Model", "Vo");
            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String modelPkg = null;
                boolean flag = false;
                String prevLine = null;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches("package\\scom\\..*\\.model\\..*")) {
                        modelPkg = line.split(" ")[1].split(";")[0];
                        line = line.replaceAll("\\.model\\.", ".vo.");
                    }
                    if (line.matches("import\\scom\\..*\\.BaseModel;")) {
                        line = line.replaceAll("com\\..*\\.BaseModel", modelPkg + "." + className);
                    }
                    if (line.matches("\\s\\*\\s.*Model.*")) {
                        line = line.replaceAll("Model", "Vo");
                    }
                    if (line.matches("import\\s.*\\.Serializable;") || line.matches("import\\s.*\\.Date;")
                            || line.matches("import\\s.*\\.Validate;") || line.matches("import\\s.*\\.BigDecimal;")) {
                        continue;
                    }
                    if (line.matches("public\\sclass\\s" + className + "\\s.*\\sBaseModel\\s.*")) {
                        line = line.replaceAll(className, voName).replaceAll("BaseModel", className);
                        flag = true;
                    } else {
                        if (flag) {
                            if (line.matches(".*serialVersionUID.*")) {
                                line = "\n" + line + "\n\n}";
                            } else {
                                continue;
                            }
                        }
                    }
                    if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                        continue;
                    }
                    lines.add(line);
                    prevLine = line;
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                String newFilePath = file.getPath().replaceAll(className, voName);
                FileUtils.deleteQuietly(file);
                file = new File(newFilePath);
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // @Test
    public void apiDeal() {
        try {
            FileUtils.deleteDirectory(new File(apiPath));
            FileUtils.copyDirectory(new File(voPath), new File(apiPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<File> modelFileList = FileUtils.listFiles(new File(apiPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                if (file.getName().matches(".*Api.*")) {
                    return false;
                }
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String apiName = className.replaceAll("Vo", "Api");
            String daoName = "I" + className.replaceAll("Vo", "Dao");
            String baseApi = "import com.common.api.page.IBasePageHelperReferenceApi;";
            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String modelPkg = null;
                String prevLine = null;
                boolean flag = false;
                boolean isMethod = false;
                boolean isImport = false;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches("package\\scom\\..*\\.vo\\..*")) {
                        modelPkg = line.split(" ")[1].split(";")[0];
                        line = line.replaceAll("\\.vo\\.", ".api.");
                    }
                    if (line.matches("import\\scom\\..*\\." + className.replaceAll("Vo", "Model") + ";")) {
                        lines.add(baseApi);
                        line = line.replaceAll("com\\..*\\." + className.replaceAll("Vo", "Model"),
                                modelPkg + "." + className);

                        flag = true;
                        isImport = true;
                    }
                    if (line.matches("\\s\\*\\s.*Vo.*")) {
                        line = line.replaceAll("Vo", "Api");
                    }
                    if (line.matches("public\\sclass\\s" + className + "\\s.*\\s" + className.replaceAll("Vo", "Model")
                            + "\\s.*")) {
                        line = line.replaceAll("class", "interface").replaceAll(className, "I" + apiName).replaceAll(
                                className.replaceAll("Vo", "Model"), "IBasePageHelperReferenceApi<" + className + ">");
                        flag = true;
                        isMethod = true;
                    }
                    if (line.matches(".*serialVersionUID.*")) {
                        continue;
                    }
                    if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                        continue;
                    }
                    lines.add(line);
                    prevLine = line;

                    if (flag) {

                        // 插入方法实现
                        String path = daoPath + "\\" + (file.getPath().substring(file.getPath().lastIndexOf("api")))
                                .replaceAll("api\\\\", "");
                        path = path.substring(0, path.lastIndexOf("\\")) + "\\" + daoName + ".java";
                        List<String> tmpList = FileUtils.readLines(new File(path), "UTF-8");
                        if (isMethod) {
                            List<String> methodList = new ArrayList<>();
                            boolean methodFlag = false;
                            for (String str : tmpList) {
                                if (str.matches("public\\sinterface\\s.*")) {
                                    methodFlag = true;
                                    continue;
                                }
                                if (methodFlag && !str.trim().equals("}")) {
                                    if (!str.equals("")) {
                                        str = "    " + str.trim().replaceAll("@Param\\(\"[a-z]+\"\\)\\s", "");
                                    }
                                    methodList.add(str);
                                }
                            }
                            lines.add("");
                            lines.addAll(methodList);
                            isMethod = false;
                        }
                        if (isImport) {
                            List<String> importList = new ArrayList<>();
                            for (String str : tmpList) {
                                if (str.matches("import\\s.*")
                                        && !str.matches("import\\scom\\..*\\." + className + ";.*")
                                        && !str.matches("import\\scom\\..*\\.IBasePageHelperDao;.*")
                                        && !str.matches("import\\sorg\\..*\\.Param;.*")) {
                                    importList.add(str);
                                }
                            }
                            lines.add("");
                            lines.addAll(importList);
                            isImport = false;
                        }
                        flag = false;
                    }
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                String newFilePath = file.getPath().replaceAll(className, "I" + apiName);
                FileUtils.deleteQuietly(file);
                file = new File(newFilePath);
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // @Test
    public void apiImplDeal() {
        try {
            FileUtils.deleteDirectory(new File(apiImplPath));
            FileUtils.copyDirectory(new File(apiPath), new File(apiImplPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<File> modelFileList = FileUtils.listFiles(new File(apiImplPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                if (file.getName().matches(".*Impl.*")) {
                    return false;
                }
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String apiName = className.substring(1, className.length()).replaceAll("Api", "ApiImpl");
            String baseName = className.substring(1, className.length()).replaceAll("Api", "");
            String pkgService = "import org.springframework.stereotype.Service;";
            String pkgBaseApi = "import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;";
            String apiPkg = null;
            String newApiPkg = null;
            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String prevLine = null;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches("package\\scom\\..*\\.api\\..*")) {
                        apiPkg = line.split(" ")[1].split(";")[0];
                        line = line.replaceAll("\\.api\\.api\\.", ".core.api.");
                        newApiPkg = line.replaceAll("\\.core\\.api\\.", ".core.service.").split(" ")[1].split(";")[0];
                        line = line.replaceAll(";", ".impl;");
                        line += "\n\n" + pkgService + "\n";
                        line += "import org.springframework.beans.factory.annotation.Autowired;\n";
                        line += "import com.common.util.ExceptionUtil;";
                    }
                    if (line.matches("public\\sinterface\\s" + className + "\\s.*IBasePageHelperReferenceApi.*")) {
                        lines.add("@Service");
                        line = line.replaceAll("interface", "class").replaceAll(className, apiName).replaceAll("\\{",
                                "implements " + className + " {");
                        line = line.replaceAll("IBasePageHelperReferenceApi", "BasePageHelperProviderApiImpl")
                                .replaceAll("<" + baseName + "Vo>", "<" + baseName + "Vo,I" + baseName + "Service>");
                        line += "  @Autowired\n";
                        line += "  private I" + baseName + "Service " + baseName.toLowerCase() + "Service;";
                    }
                    if (line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")) {
                        lines.add("import " + apiPkg + "." + className + ";");
                    }
                    if (line.matches("import\\scom\\..*\\.IBasePageHelperReferenceApi;.*")) {
                        line = pkgBaseApi;
                        line += "\nimport " + newApiPkg + ".I" + baseName + "Service;";
                    }
                    if (line.matches(".*public\\s.*\\s.*\\(.*\\)\\sthrows Exception;.*")) {
                        String param = "";
                        if (line.indexOf(",") != -1) {
                            param = line.trim().replaceAll(".*" + baseName + "Vo\\s", "")
                                    .replaceAll("\\s.*" + baseName + "ModelCriteria\\s", "");
                            param = param.replaceAll("\\)\\sthrows Exception;", "");
                        } else {
                            param = line.trim().split(" ")[3].split("\\)")[0];
                        }

                        String ctx = "";
                        ctx += "    try {\n";
                        ctx += "        return " + baseName.toLowerCase() + "Service."
                                + (line.trim().split("\\(")[0].trim()).split(" ")[2] + "(" + param + ");\n";
                        ctx += "    } catch (Exception e) {\n";
                        ctx += "        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);\n";
                        ctx += "        logger.error(msg, e);\n";
                        ctx += "        throw new Exception(msg, e);\n";
                        ctx += "    }\n";
                        line = "  " + line.trim().replaceAll(";", "{\n" + ctx + "  }");
                    }
                    if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                        continue;
                    }

                    lines.add(line);
                    prevLine = line;
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                String newFilePath = file.getPath().replaceAll(className, apiName);
                newFilePath = newFilePath.substring(0, newFilePath.lastIndexOf("\\")) + "\\impl"
                        + newFilePath.substring(newFilePath.lastIndexOf("\\"));
                FileUtils.deleteQuietly(file);
                file = new File(newFilePath);
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // @Test
    public void serviceDeal() {
        try {
            FileUtils.deleteDirectory(new File(servicePath));
            FileUtils.copyDirectory(new File(daoPath), new File(servicePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<File> modelFileList = FileUtils.listFiles(new File(servicePath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                if (file.getName().matches(".*Service.*")) {
                    return false;
                }
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String serviceName = className.replaceAll("Dao", "Service");
            String baseName = className.substring(1, className.length()).replaceAll("Dao", "");
            String pkgService = "import com.common.orm.mybatis.service.page.IBasePageHelperService;";
            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String prevLine = null;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches("package\\scom\\..*\\.dao\\..*")) {
                        line = line.replaceAll("\\.dao\\.", ".service.");
                    }
                    if (line.matches("public\\sinterface\\s" + className + "\\s.*IBasePageHelperDao.*")) {
                        line = line.replaceAll(className, serviceName)
                                .replaceAll("IBasePageHelperDao", "IBasePageHelperService").replaceAll("Model", "Vo");
                    }
                    if (line.matches("import\\scom\\..*\\.IBasePageHelperDao;.*")) {
                        line = pkgService;
                    }
                    if (line.matches("\\s\\*\\s.*Dao.*")) {
                        line = line.replaceAll("Dao", "Service");
                    }
                    if (line.matches("import\\scom\\..*\\.model\\..*\\." + baseName + "Model;.*")) {
                        line = line.replaceAll(".model.", ".vo.").replaceAll("Model", "Vo");
                    }
                    if (line.matches(".*\\(" + baseName + "Model\\s.*")
                            || line.matches(".*\\s" + baseName + "Model\\s.*")) {
                        line = line.replaceAll(baseName + "Model\\s", baseName + "Vo ");
                    }
                    if (line.matches(".*<" + baseName + "Model>.*")) {
                        line = line.replaceAll("<" + baseName + "Model>", "<" + baseName + "Vo>");
                    }
                    if (line.matches("import\\sorg\\..*\\.Param;.*")) {
                        continue;
                    }
                    if (line.matches(".*public\\s.*@Param\\(\"[a-z]+\"\\)\\s.*")) {
                        line = line.replaceAll("@Param\\(\"[a-z]+\"\\)\\s", "");
                    }
                    if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                        continue;
                    }
                    lines.add(line);
                    prevLine = line;
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                String newFilePath = file.getPath().replaceAll(className, serviceName);
                FileUtils.deleteQuietly(file);
                file = new File(newFilePath);
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // @Test
    public void serviceImplDeal() {
        try {
            FileUtils.copyDirectory(new File(apiImplPath), new File(servicePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<File> modelFileList = FileUtils.listFiles(new File(servicePath), new IOFileFilter() {
            public boolean accept(File file) {
                if (file.getName().matches(".*Service\\..*")) {
                    return false;
                }
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String implBaseName = className.replaceAll("ApiImpl", "");
            String pkgServiceImpl = "import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;";
            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String prevLine = null;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();

                    if (line.matches("package\\scom\\..*\\.api\\..*")) {
                        line = line.replaceAll("\\.api\\.", ".service.");
                    }

                    if (line.matches("public\\sclass\\s" + className + "\\s.*I" + implBaseName + "Api.*")) {
                        line = line.replaceAll(className, implBaseName + "ServiceImpl")
                                .replaceAll("BasePageHelperProviderApiImpl", "BasePageHelperServiceImpl");
                        line = line.replaceAll("I" + implBaseName + "Service", "I" + implBaseName + "Dao");
                        line = line.replaceAll("I" + implBaseName + "Api", "I" + implBaseName + "Service");
                    }

                    if (line.matches("import\\scom\\..*\\.BasePageHelperProviderApiImpl;.*")) {
                        line = pkgServiceImpl;
                    }

                    if (line.matches("import\\scom\\..*\\.I" + implBaseName + "Api;.*")) {
                        line = line.replaceAll(".api.api.", ".api.dao.").replaceAll("Api", "Dao");
                    }
                    if (line.matches("\\s\\*\\s.*Api.*")) {
                        line = line.replaceAll("Api", "Service");
                    }
                    if (line.matches("import\\scom\\..*\\.I" + implBaseName + "Dao;.*")) {
                        line = line.replaceAll(".api.", ".core.");
                    }
                    if (line.matches("import\\scom\\..*\\.I" + implBaseName + "Api;.*")) {
                        line = line.replaceAll(".api.", ".core.");
                    }
                    if (line.matches(".*private\\s.*\\(" + implBaseName + "ApiImpl\\.class\\);.*")) {
                        line = line.replaceAll(implBaseName + "ApiImpl", implBaseName + "ServiceImpl");
                    }
                    if (line.matches(".*private\\sI" + implBaseName + "Service\\s" + implBaseName.toLowerCase()
                            + "Service;.*")) {
                        line = line.replaceAll("Service", "Dao");
                    }
                    if (line.matches(".*\\s" + implBaseName.toLowerCase() + "Service\\..*;")) {
                        line = line.replaceAll("Service.", "Dao.");
                    }
                    if (prevLine != null && !line.trim().equals("}") && line.trim().equals(prevLine.trim())
                            && lineIterator.hasNext()) {
                        continue;
                    }
                    lines.add(line);
                    prevLine = line;

                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                String newFilePath = file.getPath().replaceAll(className, implBaseName + "ServiceImpl");
                FileUtils.deleteQuietly(file);
                file = new File(newFilePath);
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // @Test
    public void daoDeal() {
        String importPkg = "import com.common.orm.mybatis.dao.page.IBasePageHelperDao;";
        Collection<File> modelFileList = FileUtils.listFiles(new File(daoPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return false;
            }

            public boolean accept(File file) {
                return false;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String modelName = className.replaceAll("Mapper", "");
            String voName = modelName.replaceAll("Model", "Vo");
            String newClassName = "I" + StringUtils.capitalize(className).replaceAll("ModelMapper", "Dao");

            String folderName = file.getName().split("Model")[0].toLowerCase();
            if (folderName.startsWith("user")) {
                String[] arr = folderName.split("user");
                folderName = "user.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            } else if (folderName.startsWith("product")) {
                String[] arr = folderName.split("product");
                folderName = "product.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            } else if (folderName.startsWith("search")) {
                String[] arr = folderName.split("search");
                folderName = "search.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            }

            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String prevLine = null;
                DataSource dataSource = DBFactory.dataSourceInstance();
                TableVO tableVO = dataSource.getTableInfo("mornsun", null,
                        getTableName(file.getName().split("Model")[0]));
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();

                    if (line.startsWith("public interface")) {
                        lines.add(importPkg + "\n");
                        lines.add("/**");
                        lines.add(" * "
                                + tableVO.getRemarks().trim().substring(0, tableVO.getRemarks().trim().length() - 1)
                                + "Dao");
                        lines.add(" *");
                        lines.add(" * @author: HuiJunLuo");
                        lines.add(" * @date:2015年12月31日");
                        lines.add(" * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有");
                        lines.add(" */");
                    }

                    if (line.startsWith("package")) {
                        line = line.replaceAll(";", "." + folderName + ";");
                    }

                    if (line.contains(" interface " + className + " ")) {
                        line = line.replaceAll(className, newClassName);
                    }

                    if (line.contains(" IBaseDao")) {
                        line = line.replaceAll("IBaseDao",
                                "IBasePageHelperDao<" + StringUtils.capitalize(voName) + ">");
                    }

                    if (line.matches(".*\\(" + modelName + "\\s.*")) {
                        line = line.replaceAll(modelName, StringUtils.capitalize(voName));
                    }

                    if (line.matches("import\\s.*\\." + modelName + ";")) {
                        line = line.replaceAll(modelName, folderName + "." + StringUtils.capitalize(voName))
                                .replaceAll(".model.", ".vo.");
                    }

                    if (line.matches("import\\s.*\\." + modelName + "Criteria;")) {
                        line = line.replaceAll(modelName, folderName + "." + StringUtils.capitalize(modelName));
                    }

                    if (line.matches(".*(" + modelName + "Criteria\\s.*);")) {
                        line = line.replaceAll(modelName, StringUtils.capitalize(modelName));
                    }

                    if (line.matches(".*(.*\\s" + StringUtils.capitalize(modelName) + "\\s.*);")) {
                        line = line.replaceAll(StringUtils.capitalize(modelName) + "\\s",
                                StringUtils.capitalize(voName) + " ");
                    }

                    if (line.matches(".*<" + StringUtils.capitalize(modelName) + ">.*")) {
                        line = line.replaceAll("<" + StringUtils.capitalize(modelName) + ">",
                                "<" + StringUtils.capitalize(voName) + ">");
                    }

                    if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                        continue;
                    }

                    boolean flag = false;
                    for (String methodName : nameMapping.keySet()) {
                        if (line.matches(".*" + methodName + "\\(.*")) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;
                    }

                    if (line.matches(".*\\s.*\\(.*\\);.*")) {

                        String str = "";
                        String param = "";
                        if (line.matches(".*\\scount.*")) {
                            str = "根据条件查询总数";
                            param = "example";
                        } else if (line.matches(".*\\sdelete.*")) {
                            str = "根据条件删除数据";
                            param = "example";
                        } else if (line.matches(".*\\sinsert.*")) {
                            str = "根据条件插入数据";
                            param = "record";
                        } else if (line.matches(".*\\sselect.*")) {
                            str = "根据条件查询数据";
                            param = "example";
                        } else if (line.matches(".*\\supdateByExampleSelective.*")) {
                            str = "根据条件更新数据";
                            param = "record\n   * @param example";
                        } else if (line.matches(".*\\supdateByExample.*")) {
                            str = "根据条件更新数据";
                            param = "record\n   * @param example";
                        } else if (line.matches(".*\\supdateByPrimaryKey.*")) {
                            str = "根据主键更新数据";
                            param = "record";
                        }

                        lines.add("  /**");
                        lines.add("   * " + str);
                        lines.add("   * ");
                        lines.add("   * @param " + param);
                        lines.add("   * @return");
                        lines.add("   * @throws Exception");
                        lines.add("   */");
                        line = "  public " + line.trim().replaceAll(";", " throws Exception;");
                    }

                    lines.add(line);
                    prevLine = line;
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                FileUtils.deleteQuietly(file);
                file = new File(daoPath + "\\" + newClassName + ".java");
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }

            File newFolder = new File(daoPath + "\\" + folderName.replaceAll("\\.", "\\\\\\\\"));
            try {
                FileUtils.moveFileToDirectory(file, newFolder, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getName() + "----" + folderName);
        }
    }

    // @Test
    public void xmlDeal() {
        Collection<File> modelFileList = FileUtils.listFiles(new File(xmlPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return false;
            }

            public boolean accept(File file) {
                return false;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String modelName = className.replaceAll("Mapper", "");
            String voName = modelName.replaceAll("Model", "Vo");
            String newClassName = "I" + StringUtils.capitalize(className).replaceAll("ModelMapper", "Dao");
            String pkgName = file.getPath().split("resources")[1].replaceAll(file.getName(), "");
            pkgName = pkgName.substring(1, pkgName.length() - 1).replaceAll("\\\\", ".");

            String folderName = file.getName().split("Model")[0].toLowerCase();
            if (folderName.startsWith("user")) {
                String[] arr = folderName.split("user");
                folderName = "user.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            } else if (folderName.startsWith("product")) {
                String[] arr = folderName.split("product");
                folderName = "product.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            } else if (folderName.startsWith("search")) {
                String[] arr = folderName.split("search");
                folderName = "search.";
                if (arr.length == 0) {
                    folderName += "base";
                } else {
                    folderName += arr[1];
                }
            }

            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                boolean isInsert = false;
                boolean isInsertSelective = false;
                boolean flag = false;
                boolean isUpdate = false;
                int i = 0;

                List<String> whereList = new ArrayList<>();
                List<String> deleteList = new ArrayList<>();
                List<String> oneList = new ArrayList<>();
                List<String> allList = new ArrayList<>();
                List<String> countList = new ArrayList<>();
                List<String> pageList = new ArrayList<>();
                boolean isTmp = false;
                boolean isDelete = false;
                boolean isOne = false;
                boolean isCount = false;
                int j = 0;
                whereList.add("\t<where>");
                whereList.add("\t1=1");
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches(".*\\sid=\"deleteByPrimaryKey\".*")) {
                        deleteList.add(line.replaceAll("id=\"deleteByPrimaryKey\"", "id=\"delete\"")
                                .replaceAll("parameterType=\".*\"",
                                        "parameterType=\"" + StringUtils.capitalize(voName) + "\"")
                                .trim());
                        isDelete = true;
                        continue;
                    }
                    if (isDelete) {
                        deleteList.add(line);
                        isDelete = false;
                        continue;
                    }
                    if (line.matches(".*\\sid=\"countByExample\".*")) {
                        countList.add(line.replaceAll("id=\"countByExample\"", "id=\"getCount\"")
                                .replaceAll("parameterType=\".*\"\\s",
                                        "parameterType=\"" + StringUtils.capitalize(voName) + "\" ")
                                .trim());
                        isCount = true;
                        continue;
                    }
                    if (isCount) {
                        countList.add(line);
                        isCount = false;
                        continue;
                    }
                    if (line.matches(".*\\sid=\"selectByPrimaryKey\".*")) {
                        oneList.add(line.replaceAll("id=\"selectByPrimaryKey\"", "id=\"getOne\"")
                                .replaceAll("parameterType=\".*\"\\s",
                                        "parameterType=\"" + StringUtils.capitalize(voName) + "\" ")
                                .trim());
                        isOne = true;
                        j = 1;
                        continue;
                    }
                    if (isOne) {
                        if (j <= 3) {
                            j++;
                            oneList.add(line);
                            continue;
                        }
                        isOne = false;
                        continue;
                    }
                    if (line.matches(".*\\sid=\"updateByPrimaryKeySelective\".*")) {
                        isTmp = true;
                        j = 0;
                        continue;
                    }
                    if (isTmp) {
                        if (line.matches(".*<set\\s?>.*")) {
                            j = 1;
                            continue;
                        }
                        if (line.matches(".*</set>.*")) {
                            isTmp = false;
                            continue;
                        }
                        if (j >= 1) {
                            j++;
                            if (line.matches(".*\\s=\\s#\\{.*\\}.*")) {
                                line = "\tand " + line.trim();
                            }
                            whereList.add(line.replaceAll("\\},", "\\}"));
                        }
                    }
                }
                whereList.add("\t</where>");

                // for (String line : whereList) {
                // System.out.println(line);
                // }

                deleteList.addAll(whereList);
                deleteList.add(" </delete>");
                deleteList.add("");
                oneList.addAll(whereList);
                oneList.add(" </select>");
                oneList.add("");
                allList.addAll(oneList);
                countList.addAll(whereList);
                countList.add(" </select>");
                countList.add("");
                pageList.addAll(allList);
                for (String line : allList) {
                    line = line.replaceAll("id=\"getOne\"", "id=\"getAll\"");
                    allList.remove(0);
                    allList.add(0, line);
                    break;
                }
                for (String line : pageList) {
                    line = line.replaceAll("id=\"getOne\"", "id=\"getPage\"");
                    pageList.remove(0);
                    pageList.add(0, line);
                    break;
                }
                LineIterator.closeQuietly(lineIterator);

                lineIterator = FileUtils.lineIterator(file);
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches(".*\\sid=\".*\".*")) {
                        lines.add("");
                    }
                    if (line.matches("<mapper\\snamespace.*.*")) {
                        line = line.replaceAll(className, folderName + "." + newClassName);
                    }
                    if (line.matches(".*[Tt]ype=\"com\\..*\\." + modelName + ".*")) {
                        line = line.replaceAll("com\\..*\\." + modelName, StringUtils.capitalize(modelName));
                        line = line.replaceAll(StringUtils.capitalize(modelName) + "\"",
                                StringUtils.capitalize(voName) + "\"");
                    }

                    for (String methodName : nameMapping.keySet()) {
                        if (line.matches(".*\\sid=\"" + methodName + "\".*")) {
                            line = line.replaceAll("id=\"" + methodName + "\"",
                                    "id=\"" + nameMapping.get(methodName) + "\"");
                            break;
                        }
                    }

                    if (line.matches(".*\\sid=\"insert\".*")) {
                        isInsert = true;
                        isInsertSelective = false;
                        isUpdate = false;
                    }
                    if (line.matches(".*\\sid=\"insertSelective\".*")) {
                        isInsert = false;
                        isInsertSelective = true;
                        isUpdate = false;
                    }
                    if (line.matches(".*\\sid=\"update.*\".*")) {
                        isInsert = false;
                        isInsertSelective = false;
                        isUpdate = true;
                    }
                    if (isInsert) {
                        if (line.matches(".*\\sisavailable,.*")) {
                            line = line.replaceAll("isavailable,", "");
                        }
                        if (line.matches(".*\\supdate_by,.*")) {
                            line = line.replaceAll("update_by,", "");
                        }
                        if (line.matches(".*\\supdate_time,.*")) {
                            line = line.replaceAll("update_time,", "");
                        }
                        if (line.matches(".*\\s#\\{isavailable,.*\\},.*")) {
                            line = line.replaceAll("#\\{isavailable,.*\\},", "");
                        }
                        if (line.matches(".*\\s#\\{updateBy,.*\\},.*")) {
                            line = line.replaceAll("#\\{updateBy,.*\\},", "");
                        }
                        if (line.matches(".*\\s#\\{updateTime,.*\\},.*")) {
                            line = line.replaceAll("#\\{updateTime,.*\\},", "");
                        }
                    }
                    if (isInsertSelective) {
                        if (line.matches(".*\\stest=\"isavailable\\s.*")) {
                            flag = true;
                            i = 1;
                            continue;
                        }
                        if (line.matches(".*\\stest=\"updateBy\\s.*")) {
                            flag = true;
                            i = 1;
                            continue;
                        }
                        if (line.matches(".*\\stest=\"updateTime\\s.*")) {
                            flag = true;
                            i = 1;
                            continue;
                        }
                        if (flag && i < 3) {
                            i++;
                            continue;
                        }
                    }
                    if (isUpdate) {
                        if (line.matches(".*\\sid=\"updateByPrimaryKey\".*")
                                || line.matches(".*\\sid=\"updateByExample\".*")) {
                            flag = true;
                        }
                        if (line.matches(".*\\sid=\"update\".*")
                                || line.matches(".*\\sid=\"updateByExampleSelective\".*")) {
                            flag = false;
                        }
                        if (flag) {
                            if (line.matches(".*create_by\\s=\\s#\\{.*\\.?createBy,.*\\}.*")
                                    || line.matches(".*create_time\\s=\\s#\\{.*\\.?createTime,.*\\}.*")) {
                                continue;
                            }
                        } else {
                            if (line.matches(".*\\stest=\".*\\.?createBy\\s.*")
                                    || line.matches(".*\\stest=\".*\\.?createTime\\s.*")) {
                                i = 1;
                                continue;
                            }
                            if (!flag && i < 3) {
                                i++;
                                continue;
                            }
                        }
                    }
                    if (line.matches(".*#\\{.*\\.?createTime,.*\\}.*")) {
                        line = line.replaceAll("#\\{createTime,.*\\}", "sysdate()");
                    }
                    if (line.matches(".*#\\{.*\\.?updateTime,.*\\}.*")) {
                        line = line.replaceAll("#\\{.*\\.?updateTime,.*\\}", "sysdate()");
                    }

                    // List<String> deleteList=new ArrayList<>();
                    // List<String> oneList=new ArrayList<>();
                    // List<String> allList=new ArrayList<>();
                    // List<String> countList=new ArrayList<>();
                    // List<String> pageList=new ArrayList<>();
                    if (line.matches(".*\\sid=\"insert\".*")) {
                        lines.addAll(deleteList);
                    }
                    if (line.matches(".*\\sid=\"deleteById\".*")) {
                        lines.addAll(oneList);
                    }
                    if (line.matches(".*\\sid=\"getOneById\".*")) {
                        lines.addAll(allList);
                    }
                    if (line.matches(".*\\sid=\"updateByExampleSelective\".*")) {
                        lines.addAll(countList);
                    }
                    if (line.matches(".*\\sid=\"getOneById\".*")) {
                        lines.addAll(pageList);
                    }
                    lines.add(line);
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                FileUtils.deleteQuietly(file);
                file = new File(xmlPath + "\\" + newClassName + ".xml");
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }

            File newFolder = new File(xmlPath + "\\" + folderName.replaceAll("\\.", "\\\\\\\\"));
            try {
                FileUtils.moveFileToDirectory(file, newFolder, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getName() + "----" + folderName);
        }
    }

    // @Test
    public void serviceAppDeal() {
        try {
            FileUtils.deleteDirectory(new File(serviceAppPath));
            FileUtils.copyDirectory(new File(servicePath), new File(serviceAppPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<File> modelFileList = FileUtils.listFiles(new File(serviceAppPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                if (file.getName().matches(".*Service.*")) {
                    return false;
                }
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            if (file.getName().matches("I.*Service\\..*")) {
                List<String> lines = new ArrayList<>();
                String className = file.getName().split("\\.")[0];
                String baseName = className.substring(1, className.length()).replaceAll("Service", "");
                String servicePkg = "import com.common.orm.mybatis.service.page.IBasePageHelperApiService;";
                try {
                    LineIterator lineIterator = FileUtils.lineIterator(file);
                    String prevLine = null;
                    boolean flag = false;
                    while (lineIterator.hasNext()) {
                        String line = lineIterator.nextLine();
                        if (!lineIterator.hasNext()) {
                            lines.add("");
                        }
                        if (flag && lineIterator.hasNext()) {
                            continue;
                        }
                        if (line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")) {
                            lines.add(servicePkg);
                        }
                        if (line.matches("import\\s.*") && !line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")) {
                            continue;
                        }
                        if (line.matches("package\\scom\\..*")) {
                            line = line.replaceAll(".jsw.", ".app.");
                        }
                        if (line.matches("public\\sinterface\\s" + className + ".*")) {
                            line = line.replaceAll("IBasePageHelperService", "IBasePageHelperApiService");
                            flag = true;
                        }
                        if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                            continue;
                        }
                        lines.add(line);
                        prevLine = line;
                    }
                    LineIterator.closeQuietly(lineIterator);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                // for (String line : lines) {
                // System.out.println(line);
                // }

                try {
                    String newFilePath = file.getPath();
                    FileUtils.deleteQuietly(file);
                    file = new File(newFilePath);
                    FileUtils.writeLines(file, lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                List<String> lines = new ArrayList<>();
                String className = file.getName().split("\\.")[0];
                String baseName = className.substring(0, className.length()).replaceAll("ServiceImpl", "");
                String servicePkg = "import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;";
                try {
                    LineIterator lineIterator = FileUtils.lineIterator(file);
                    String prevLine = null;
                    boolean flag = false;
                    String pkg = null;
                    while (lineIterator.hasNext()) {
                        String line = lineIterator.nextLine();
                        if (line.matches("package\\scom\\..*")) {
                            line = line.replaceAll(".jsw.", ".app.");
                            pkg = line.split(" ")[1].split(";")[0];
                        }
                        if (line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")) {
                            lines.add(servicePkg);
                            lines.add("");
                            lines.add(line.replaceAll("\\.vo\\.", ".api.").replaceAll("\\." + baseName + "Vo",
                                    ".I" + baseName + "Api"));
                            lines.add("import " + pkg.replaceAll(".impl", "") + ".I" + baseName + "Service;");
                        }
                        if (line.matches("import\\s.*") && !line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")
                                && !line.matches("import\\sorg\\..*\\.Service;.*")
                                && !line.matches("import\\scom\\..*\\.I" + baseName + "Api;.*")) {
                            continue;
                        }
                        if (line.matches("public\\sclass\\s" + className + ".*")) {
                            line = line.replaceAll("\\sBasePageHelperServiceImpl", " BasePageHelperApiServiceImpl");
                            line = line.replaceAll("I" + baseName + "Dao", "I" + baseName + "Api");
                            flag = true;
                            lines.add(line);
                            lines.add("");
                            lines.add("}");
                        }
                        if (flag) {
                            continue;
                        }
                        if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                            continue;
                        }
                        lines.add(line);
                        prevLine = line;
                    }
                    LineIterator.closeQuietly(lineIterator);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                // for (String line : lines) {
                // System.out.println(line);
                // }

                try {
                    String newFilePath = file.getPath();
                    FileUtils.deleteQuietly(file);
                    file = new File(newFilePath);
                    FileUtils.writeLines(file, lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // @Test
    public void controllerAppDeal() {
        try {
            FileUtils.deleteDirectory(new File(controllerAppPath));
            FileUtils.copyDirectory(new File(serviceAppPath), new File(controllerAppPath), new IOFileFilter() {
                public boolean accept(File file) {
                    if (file.getName().matches(".*Service\\..*")) {
                        return false;
                    }
                    return true;
                }

                public boolean accept(File file, String s) {
                    return true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collection<File> modelFileList = FileUtils.listFiles(new File(controllerAppPath), new IOFileFilter() {

            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();
            String className = file.getName().split("\\.")[0];
            String baseName = className.substring(0, className.length()).replaceAll("ServiceImpl", "");
            String servicePkg = "import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;";
            String controllerPkg = "import org.springframework.stereotype.Controller;";
            String mappPkg = "import org.springframework.web.bind.annotation.RequestMapping;";
            try {
                LineIterator lineIterator = FileUtils.lineIterator(file);
                String prevLine = null;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.nextLine();
                    if (line.matches("public\\sclass\\s" + className + "\\s.*")) {
                        line = line.replaceAll("\\simplements.*\\s", "")
                                .replaceAll("I" + baseName + "Api", "I" + baseName + "Service")
                                .replaceAll("BasePageHelperApiServiceImpl", "BasePageHelperApiServiceController");
                        line = line.replaceAll("\\s" + baseName + "ServiceImpl\\s", " " + baseName + "Controller ");
                    }
                    if (line.matches(".*@Service.*")) {

                        String folderName = baseName.toLowerCase().trim();
                        if (folderName.startsWith("user")) {
                            String[] arr = folderName.split("user");
                            folderName = "/user";
                            if (arr.length != 0) {
                                folderName += "/" + arr[1];
                            }
                        } else if (folderName.startsWith("product")) {
                            String[] arr = folderName.split("product");
                            folderName = "/product";
                            if (arr.length != 0) {
                                folderName += "/" + arr[1];
                            }
                        } else if (folderName.startsWith("search")) {
                            String[] arr = folderName.split("search");
                            folderName = "/search";
                            if (arr.length != 0) {
                                folderName += "/" + arr[1];
                            }
                        } else {
                            folderName = "/" + folderName;
                        }

                        line = "@Controller\n";
                        line += "@RequestMapping(\"" + folderName + "\")";
                    }
                    if (line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")) {
                        lines.add(servicePkg);
                        lines.add("");
                        lines.add(controllerPkg);
                        lines.add(mappPkg);
                    }
                    if (line.matches("package\\scom\\..*\\.impl.*")) {
                        line = line.replaceAll("\\.impl", "").replaceAll("\\.core.service\\.", ".manager.controller.");
                    }
                    if (line.matches("\\s\\*\\s.*Service.*")) {
                        line = line.replaceAll("Service", "Controller");
                    }
                    if (line.matches("import\\s.*") && !line.matches("import\\scom\\..*\\." + baseName + "Vo;.*")
                            && !line.matches("import\\scom\\..*\\.I" + baseName + "Service;.*")) {
                        continue;
                    }
                    if (prevLine != null && line.trim().equals(prevLine.trim()) && lineIterator.hasNext()) {
                        continue;
                    }
                    lines.add(line);
                    prevLine = line;
                }
                LineIterator.closeQuietly(lineIterator);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for (String line : lines) {
            // System.out.println(line);
            // }

            try {
                String newFilePath = file.getPath().replaceAll("\\\\impl\\\\", "\\\\");
                FileUtils.deleteQuietly(file);
                file = new File(newFilePath.replaceAll("ServiceImpl", "Controller"));
                FileUtils.writeLines(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        modelFileList = FileUtils.listFiles(new File(controllerAppPath), new IOFileFilter() {

            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {
            FileUtils.deleteQuietly(new File(file.getPath().substring(0, file.getPath().lastIndexOf("\\")) + "\\impl"));
        }
    }

    // @Test
    public void apiDubboXmlDeal() {
        Collection<File> modelFileList = FileUtils.listFiles(new File(apiPath), new IOFileFilter() {
            public boolean accept(File file) {
                return true;
            }

            public boolean accept(File file, String s) {
                return true;
            }
        }, new IOFileFilter() {
            public boolean accept(File file, String s) {
                return true;
            }

            public boolean accept(File file) {
                return true;
            }
        });

        for (File file : modelFileList) {

            if (file.isDirectory()) {
                continue;
            }

            List<String> lines = new ArrayList<>();

            String className = file.getName().split("\\.")[0];
            String xml = "<dubbo:reference id=\"@id@\" interface=\"@pkg@\" version=\"${dubbo.svr.version}\"/>";
            xml = xml.replaceAll("@id@", StringUtils.uncapitalize(className.substring(1, className.length())));
            try {
                xml = xml.replaceAll("@pkg@",
                        FileUtils.readLines(file, "UTF-8").get(0).split(" ")[1].split(";")[0] + "." + className);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // System.out.println(xml);

            String xmlImpl = "<dubbo:service id=\"@id@\" interface=\"@pkg@\" ref=\"@impl@\" version=\"${dubbo.svr.version}\" owner=\"@user@\"/>";
            xmlImpl = xmlImpl.replaceAll("@id@", StringUtils.uncapitalize(className.substring(1, className.length())));
            try {
                xmlImpl = xmlImpl.replaceAll("@pkg@",
                        FileUtils.readLines(file, "UTF-8").get(0).split(" ")[1].split(";")[0] + "." + className);
            } catch (IOException e) {
                e.printStackTrace();
            }
            xmlImpl = xmlImpl.replaceAll("@impl@",
                    StringUtils.uncapitalize(className.substring(1, className.length())) + "Impl");
            xmlImpl = xmlImpl.replaceAll("@user@", "luohj");
            System.out.println(xmlImpl);

        }
    }

    public static String getTableName(String str) {
        String s = "t_mornsun_";
        String tmp = "";
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                flag = true;
                break;
            }
            tmp += c;
        }
        if (flag) {

            String tmp2 = "";
            flag = false;
            str = StringUtils.uncapitalize(str.split(tmp)[1]);
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    flag = true;
                    break;
                }
                tmp2 += c;
            }

            if (flag) {
                s = s + tmp + "_" + tmp2 + "_" + str.split(tmp2)[1];
            } else {
                s = s + tmp + "_" + tmp2;
            }

        } else {
            s = s + tmp;
        }
        return s.toLowerCase();
    }

}
