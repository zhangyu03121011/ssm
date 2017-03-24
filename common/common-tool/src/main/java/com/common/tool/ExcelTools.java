package com.common.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.common.sql.util.CommUtil;

public class ExcelTools {

    private static Map<String, Map<String, String>> cnMap = new HashMap<String, Map<String, String>>();

    private static Map<String, Map<String, String>> enMap = new HashMap<String, Map<String, String>>();

    private static Map<String, Map<String, String>> sapMap = new HashMap<String, Map<String, String>>();

    public static void main(String[] args) {
        cnMap = getVendorOther(readExcel("D:\\cn.xlsx"));
        enMap = getVendorOther(readExcel("D:\\en.xlsx"));
        sapMap = getVendorOther(readExcel("D:\\sap.xlsx"));
        createVendor(readExcel("D:\\a.xlsx"));
    }

    public static String getVendorContactList(Row row, Row titleRow, String vendorNo, int vendorId) {

        Map<String, String> vendorContactMap = new HashMap<String, String>();
        vendorContactMap.put("供应商简称", "vendor_name");
        vendorContactMap.put("手机号", "mobile_number");
        vendorContactMap.put("联系人", "contact_person");
        vendorContactMap.put("邮箱", "email_address");
        vendorContactMap.put("电话", "tel");

        StringBuffer sqlColumn = new StringBuffer("insert into t_pms_vendor_contact (");
        StringBuffer sqlValue = new StringBuffer(" values (");

        String strColumn = "vendor_id,vendor_no,";
        String strValue = vendorId + ",'" + vendorNo + "',";

        for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell == null) {
                continue;
            }

            String titleName = titleRow.getCell(cellNum).getStringCellValue();
            String columnName = vendorContactMap.get(titleName);
            if (CommUtil.isNotEmpty(columnName)) {
                strColumn += columnName + ",";

                String value = cell.getStringCellValue().replaceAll(",", "/,").trim();

                if ("email_address".equalsIgnoreCase(columnName)) {
                    value = value.replaceAll("'", "");
                }

                strValue += "'" + value + "',";
            }
        }

        if (CommUtil.isNotEmpty(strColumn)) {
            strColumn = strColumn.trim().substring(0, strColumn.trim().length() - 1);
        }
        if (CommUtil.isNotEmpty(strValue)) {
            strValue = strValue.trim().substring(0, strValue.trim().length() - 1);
        }
        sqlColumn.append(strColumn);
        sqlValue.append(strValue);
        sqlColumn.append(") ");
        sqlValue.append(");");

        sqlColumn.append(sqlValue.toString());

        return sqlColumn.toString();
    }

    public static void createVendor(Sheet sheet) {
        Map<String, String> vendorMap = new HashMap<String, String>();
        vendorMap.put("供应商简称", "vendor_name");
        vendorMap.put("供应商全名", "vendor_fullname");
        vendorMap.put("供应商类别", "vendor_type");
        vendorMap.put("供应商地址", "vendor_address");

        Map<String, String> vendorTypeMap = new HashMap<String, String>();
        vendorTypeMap.put("原厂", "1");
        vendorTypeMap.put("分销商", "2");
        vendorTypeMap.put("合作伙伴", "3");
        vendorTypeMap.put("中间商", "4");
        vendorTypeMap.put("贸易商", "5");
        vendorTypeMap.put("代理商", "4");

        Map<String, String> vendorOtherMap = new HashMap<String, String>();
        vendorOtherMap.put("组", "vendor_group");
        vendorOtherMap.put("邮政编码", "zip_code");
        vendorOtherMap.put("城市", "city");
        vendorOtherMap.put("Cty", "country_code");
        vendorOtherMap.put("传真号", "fax");
        vendorOtherMap.put("电话1", "phone");
        vendorOtherMap.put("货币", "currency");

        Row titleRow = sheet.getRow(0);

        List<String> vendorList = new ArrayList<String>();
        vendorList.add("");
        List<String> vendorContactList = new ArrayList<String>();
        vendorContactList.add("");
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        int vendorIdMin = 1000;
        int vendorIdMax = 0;
        int sapCount = 0;
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                continue;
            }

            StringBuffer sqlColumn = new StringBuffer("insert into t_pms_vendor (");
            StringBuffer sqlValue = new StringBuffer(" values (");

            String strColumn = "vendor_id,vendor_no,user_name,";

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

            int vendorId = vendorIdMin + (rowNum - 1);
            if (rowNum == sheet.getLastRowNum()) {
                vendorIdMax = rowNum;
            }
            //dateFormat.format(Calendar.getInstance().getTime())
            String vendorNo = "V" + "20140911" + decimalFormat.format(rowNum);
            String strValue = vendorId + ",'" + vendorNo + "','张健',";

            String vendorContactSql = getVendorContactList(row, titleRow, vendorNo, vendorId);
            vendorContactList.add(vendorContactSql);

            String vendorName = "";
            for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) {
                    continue;
                }
                
               // cell.getCellType()==Cell.CELL_TYPE_NUMERIC

                String titleName = titleRow.getCell(cellNum).getStringCellValue();
                String columnName = vendorMap.get(titleName);
                if (CommUtil.isNotEmpty(columnName)) {
                    strColumn += columnName + ",";

                    if ("vendor_fullname".equalsIgnoreCase(columnName)) {
                        vendorName = cell.getStringCellValue().trim();
                    }

                    String value = cell.getStringCellValue().replaceAll(",", "/,").trim();
                    if ("vendor_type".equalsIgnoreCase(columnName)) {
                        value = vendorTypeMap.get(value);
                    }

                    strValue += "'" + value + "',";
                }
            }
            
            boolean countryFlag=false;//是否有国家值

            if (CommUtil.isNotEmpty(cnMap) && cnMap.containsKey(vendorName)) {
                Map<String, String> tempMap = cnMap.get(vendorName);
                Set<Entry<String, String>> set = vendorOtherMap.entrySet();
                if (CommUtil.isNotEmpty(tempMap.get("Cty"))) {
                    countryFlag=true;
                }
                for (Entry<String, String> entry : set) {
                    if (tempMap.containsKey(entry.getKey())) {
                        strColumn += entry.getValue() + ",";

                        String value = tempMap.get(entry.getKey());
                        if ("currency".equalsIgnoreCase(entry.getValue())) {
                            if ("USD".equalsIgnoreCase(value)) {
                                value = "1";
                            } else if ("RMB".equalsIgnoreCase(value)) {
                                value = "2";
                            }
                        }

                        strValue += "'" + value + "',";
                    }
                }
            }

            if (CommUtil.isNotEmpty(enMap) && enMap.containsKey(vendorName)) {
                Map<String, String> tempMap = enMap.get(vendorName);
                Set<Entry<String, String>> set = vendorOtherMap.entrySet();
                if (CommUtil.isNotEmpty(tempMap.get("Cty"))) {
                    countryFlag=true;
                }
                for (Entry<String, String> entry : set) {
                    if (tempMap.containsKey(entry.getKey())) {
                        strColumn += entry.getValue() + ",";

                        String value = tempMap.get(entry.getKey());
                        if ("currency".equalsIgnoreCase(entry.getValue())) {
                            if ("USD".equalsIgnoreCase(value)) {
                                value = "1";
                            } else if ("RMB".equalsIgnoreCase(value)) {
                                value = "2";
                            }
                        }

                        strValue += "'" + value + "',";
                    }
                }
            }
            if (countryFlag) {
                strColumn += "state,";
                strValue += "4,";
            }else{
                strColumn += "state,";
                strValue += "1,";
            }

            if (CommUtil.isNotEmpty(sapMap) && sapMap.containsKey(vendorName)) {
                Map<String, String> tempMap = sapMap.get(vendorName);
                int sapCode = Double.valueOf(tempMap.get("供应商代码")).intValue();
                if (sapCode > 0) {
                    strColumn += "sap_code,";
                    strValue += "'" + sapCode + "',";
                    sapCount++;
                }
            }

            if (CommUtil.isNotEmpty(strColumn)) {
                strColumn = strColumn.trim().substring(0, strColumn.trim().length() - 1);
            }
            if (CommUtil.isNotEmpty(strValue)) {
                strValue = strValue.trim().substring(0, strValue.trim().length() - 1);
            }
            sqlColumn.append(strColumn);
            sqlValue.append(strValue);
            sqlColumn.append(") ");
            sqlValue.append(");");

            sqlColumn.append(sqlValue.toString());
            vendorList.add(sqlColumn.toString());
        }
        vendorList.add("alter table t_pms_vendor auto_increment=" + (vendorIdMin + vendorList.size() - 1) + ";");
        vendorList.set(0, "delete from t_pms_vendor where vendor_id>=" + vendorIdMin + " and vendor_id<="
                + (vendorIdMin + vendorIdMax) + ";");
        writeSql("t_pms_vendor", vendorList);
        System.out.println("生成t_pms_vendor表成功，总条数：" + vendorList.size());

        vendorContactList.set(0, "delete from t_pms_vendor_contact where vendor_id>=" + vendorIdMin
                + " and vendor_id<=" + (vendorIdMin + vendorIdMax) + ";");
        writeSql("t_pms_vendor_contact", vendorContactList);
        System.out.println("生成t_pms_vendor_contact表成功，总条数：" + vendorContactList.size());

        System.out.println("找到SAP Code数：" + sapCount);
    }

    public static Map<String, Map<String, String>> getVendorOther(Sheet sheet) {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, String> infoMap = null;

        Row titleRow = sheet.getRow(0);

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                continue;
            }

            String vendorName = "";
            infoMap = new HashMap<String, String>();

            for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);

                if (titleRow.getCell(cellNum) == null) {
                    continue;
                }
                String columnName = titleRow.getCell(cellNum).getStringCellValue().trim();
                if (cell == null || !CommUtil.isNotEmpty(columnName)) {
                    continue;
                }

                if ("名称 1".equalsIgnoreCase(columnName)) {
                    vendorName = cell.getStringCellValue().trim();
                }

                Object obj = null;
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    obj = cell.getNumericCellValue();
                } else {
                    obj = cell.getStringCellValue();
                }

                infoMap.put(columnName, obj.toString().trim());
            }
            map.put(vendorName, infoMap);
        }

        return map;
    }

    public static Sheet readExcel(String fileName) {
        Sheet sheet = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Workbook workBook = new XSSFWorkbook(new FileInputStream(file));
                sheet = workBook.getSheetAt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public static void writeSql(String tableName, List<String> list) {
        try {
            if (!CommUtil.isNotEmpty(list)) {
                return;
            }
            File file = new File("F://test/" + tableName.concat(".sql"));
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (String str : list) {
                bufferedWriter.write(str + "\r\n");
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
