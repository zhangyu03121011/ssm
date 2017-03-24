package com.common.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.common.tool.vo.JsonVo;

public class JobApp {

    public static void main(String[] args) {
        // industryList();
        // jobList();
        test();
    }

    public static void test() {
        try {
            File file = new File("/home/huijunluo/KuaiPan/TtJob/base_data/按职位搜索.xls");
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            int rowstart = hssfSheet.getFirstRowNum();
            int rowEnd = hssfSheet.getLastRowNum();

            int r = 0;
            int m = 0;
            int n = 0;
            List<JsonVo> list1 = new ArrayList<JsonVo>();
            List<JsonVo> list2 = new ArrayList<JsonVo>();
            List<JsonVo> list3 = new ArrayList<JsonVo>();
            for (int i = rowstart; i <= rowEnd; i++) {

                HSSFRow row = hssfSheet.getRow(i);
                if (null == row)
                    continue;
                int cellStart = row.getFirstCellNum();
                int cellEnd = row.getLastCellNum();

                for (int k = cellStart; k <= cellEnd; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (null == cell)
                        continue;

                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        // System.out.print(cell.getNumericCellValue() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串

                        if (k == 0) {
                            r++;
                            m = 0;

                            JsonVo jsonVo = new JsonVo();
                            jsonVo.setKey(r);
                            jsonVo.setParentKey(-1);
                            jsonVo.setRootKey(-1);
                            jsonVo.setValue(cell.getStringCellValue());
                            list1.add(jsonVo);

                        } else if (k == 1) {
                            m++;
                            n = 0;

                            JsonVo jsonVo = new JsonVo();
                            jsonVo.setKey(m);
                            jsonVo.setParentKey(r);
                            jsonVo.setRootKey(r);
                            jsonVo.setValue(cell.getStringCellValue());
                            list2.add(jsonVo);

                        } else if (k == 2) {
                            n++;
                            JsonVo jsonVo = new JsonVo();
                            jsonVo.setKey(n);
                            jsonVo.setParentKey(m);
                            jsonVo.setRootKey(r);
                            jsonVo.setValue(cell.getStringCellValue());
                            list3.add(jsonVo);
                        }

                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        // System.out.println(cell.getBooleanCellValue() +
                        // "   ");
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        // System.out.print(cell.getCellFormula() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        // System.out.println(" ");
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        // System.out.println(" ");
                        break;
                    default:
                        // System.out.print("未知类型   ");
                        break;
                    }
                }

            }

            List<JsonVo> list = null;
            for (JsonVo jsonVo2 : list2) {
                list = new ArrayList<JsonVo>();
                for (JsonVo jsonVo3 : list3) {
                    if (jsonVo2.getKey() == jsonVo3.getParentKey() && jsonVo3.getRootKey() == jsonVo2.getParentKey()) {
                        // System.out.println(jsonVo2 + "==" + jsonVo3);
                        list.add(jsonVo3);
                    }
                }
                jsonVo2.setList(list);
            }

            for (JsonVo jsonVo1 : list1) {
                list = new ArrayList<JsonVo>();
                for (JsonVo jsonVo2 : list2) {
                    if (jsonVo1.getKey() == jsonVo2.getParentKey()) {
                        list.add(jsonVo2);
                    }
                }
                jsonVo1.setList(list);
            }

            JSONArray jsonArray = new JSONArray();
            for (JsonVo jsonVo : list1) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", jsonVo.getKey());
                jsonObject.put("value", jsonVo.getValue());

                JSONArray jsonArray2 = new JSONArray();
                for (JsonVo jsonVo2 : jsonVo.getList()) {
                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("key", jsonVo2.getKey());
                    jsonObject2.put("value", jsonVo2.getValue());
                    
                    JSONArray jsonArray3 = new JSONArray();
                    for (JsonVo jsonVo3 : jsonVo2.getList()) {
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("key", jsonVo3.getKey());
                        jsonObject3.put("value", jsonVo3.getValue());
                        jsonArray3.add(jsonObject3);
                    }
                    
                    jsonObject2.put("list", jsonArray3);
                    jsonArray2.add(jsonObject2);
                }
                
                jsonObject.put("list", jsonArray2);
                jsonArray.add(jsonObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("jobList", jsonArray);

            System.out.println(jsonObject.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jobList() {
        try {
            File file = new File("/home/huijunluo/KuaiPan/TtJob/base_data/按职位搜索.xls");
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            int rowstart = hssfSheet.getFirstRowNum();
            int rowEnd = hssfSheet.getLastRowNum();

            boolean isSameRow = false;
            boolean isSameRow2 = false;
            System.out.println("{jobList:[");
            int r = 0;
            int m = 0;
            int n = 0;
            String list = "";
            String result = "";
            String list3 = "";
            for (int i = rowstart; i <= rowEnd; i++) {

                HSSFRow row = hssfSheet.getRow(i);
                if (null == row)
                    continue;
                int cellStart = row.getFirstCellNum();
                int cellEnd = row.getLastCellNum();

                String str0 = "";
                String str1 = "";
                String str2 = "";

                for (int k = cellStart; k <= cellEnd; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (null == cell)
                        continue;

                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        // System.out.print(cell.getNumericCellValue() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        // System.out.print(cell.getStringCellValue() + "   ");

                        if (k == 0 && !StringUtils.isEmpty(cell.getStringCellValue())) {
                            str0 = cell.getStringCellValue();
                            r++;
                            m = 0;
                            if (i != rowstart) {
                                System.out.println(result + list.substring(0, list.length() - 1) + "]},");
                            }
                            // System.out.println(list);
                            list = "";
                            result = "";
                            isSameRow = false;
                        } else if (k == 1 && !StringUtils.isEmpty(cell.getStringCellValue())) {
                            str1 = cell.getStringCellValue();
                            m++;
                            n = 0;

                            if (StringUtils.isEmpty(str0)) {
                                isSameRow = true;
                            }

                            // if (m != 1) {
                            // list += (list + list3.substring(0, list3.length()
                            // - 1) + "]},");
                            // }

                            if (!isSameRow) {
                                // System.out.println(list);
                                result = "{key:" + r + ",value:'" + str0 + "',list:[";
                                // System.out.println("{key:" + r + ",value:'" +
                                // str0 + "',list:[]}");
                            }

                            // if (i != rowstart) {
                            System.out.println(list + "---" + list3);
                            // }

                            // System.out.println(list+"--------"+list3);

                            list3 = "";
                            // System.out.println(isSameRow + "--" + str0 + "--"
                            // + str1);

                            isSameRow2 = false;
                        } else if (k == 2) {

                            str2 = cell.getStringCellValue();
                            n++;

                            if (StringUtils.isEmpty(str1)) {
                                isSameRow2 = true;
                            }

                            list3 += "{key:" + n + ",value:'" + str2 + "'},";

                            if (!isSameRow2) {
                                list += "{key:" + m + ",value:'" + str1 + "',list:[";
                            }

                        }

                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        // System.out.println(cell.getBooleanCellValue() +
                        // "   ");
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        // System.out.print(cell.getCellFormula() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        // System.out.println(" ");
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        // System.out.println(" ");
                        break;
                    default:
                        // System.out.print("未知类型   ");
                        break;
                    }

                }
                // System.out.print("\n");
                if (i == rowEnd) {
                    System.out.println(result + list.substring(0, list.length() - 1) + "]}");
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("]}");
    }

    public static void industryList() {
        try {
            File file = new File("/home/huijunluo/KuaiPan/TtJob/base_data/按职行业搜索.xls");
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            int rowstart = hssfSheet.getFirstRowNum();
            int rowEnd = hssfSheet.getLastRowNum();

            boolean isSameRow = false;
            System.out.println("{industryList:[");
            int r = 0;
            int m = 0;
            String list = "";
            String result = "";
            for (int i = rowstart; i <= rowEnd; i++) {

                HSSFRow row = hssfSheet.getRow(i);
                if (null == row)
                    continue;
                int cellStart = row.getFirstCellNum();
                int cellEnd = row.getLastCellNum();

                String str0 = "";
                String str1 = null;

                for (int k = cellStart; k <= cellEnd; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (null == cell)
                        continue;

                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        // System.out.print(cell.getNumericCellValue() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        // System.out.print(cell.getStringCellValue() + "   ");

                        if (k == 0 && !StringUtils.isEmpty(cell.getStringCellValue())) {
                            str0 = cell.getStringCellValue();
                            r++;
                            m = 0;
                            if (i != rowstart) {
                                System.out.println(result + list.substring(0, list.length() - 1) + "]},");
                            }
                            // System.out.println(list);
                            list = "";
                            result = "";
                            isSameRow = false;
                        } else if (k == 1) {
                            str1 = cell.getStringCellValue();
                            m++;

                            if (StringUtils.isEmpty(str0)) {
                                isSameRow = true;
                            }

                            list += "{key:" + m + ",value:'" + str1 + "'},";
                            if (!isSameRow) {
                                // System.out.println(list);
                                result = "{key:" + r + ",value:'" + str0 + "',list:[";
                                // System.out.println("{key:" + r + ",value:'" +
                                // str0 + "',list:[]}");
                            }
                            // System.out.println(isSameRow + "--" + str0 + "--"
                            // + str1);
                        }

                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        // System.out.println(cell.getBooleanCellValue() +
                        // "   ");
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        // System.out.print(cell.getCellFormula() + "   ");
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        // System.out.println(" ");
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        // System.out.println(" ");
                        break;
                    default:
                        // System.out.print("未知类型   ");
                        break;
                    }

                }
                // System.out.print("\n");
                if (i == rowEnd) {
                    System.out.println(result + list.substring(0, list.length() - 1) + "]}");
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("]}");
    }

}