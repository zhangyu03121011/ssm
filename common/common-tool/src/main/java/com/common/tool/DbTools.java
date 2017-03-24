package com.common.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.common.sql.factory.DBFactory;
import com.common.sql.model.ColumnVO;
import com.common.sql.model.DriverVO;
import com.common.sql.model.TableVO;
import com.common.sql.proxy.DataSource;
import com.common.sql.util.CommUtil;

public class DbTools {

    // public static final String PATH = "D:\\db\\";
    public static final String PATH = "/home/huijunluo/Gml/db/";

    public static void main(String[] args) {

        System.out.println("======================ttjob==========================");
        //compreDb("ttjob", "ttjob");
        compreDb("ods_ttkjob", "ods_ttkjob");
        
        System.out.println();
        System.out.println();
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println();
        System.out.println();

        // System.out.println("======================fms==========================");
        // compreDb("fms", "test_fms");
        //
        // System.out.println();
        // System.out.println();
        // System.out.println("================================================");
        // System.out.println("================================================");
        // System.out.println("================================================");
        // System.out.println();
        // System.out.println();
        //
        // System.out.println("======================report==========================");
        // compreDb("report", "test_report");

    }

    public static void compreDb(String srcDb, String destDb) {
        // local表
        List<TableVO> localTables = null;
                
        //localTables = getTables("ttjob", "123456", "jdbc:mysql://192.168.0.180:3306/" + srcDb);
        //localTables = getTables("ttkj", "ttkj123", "jdbc:mysql://192.168.0.160:8066/" + srcDb);
        //localTables = getTables("ttkj", "testttkj123", "jdbc:mysql://192.168.0.180:8066/" + srcDb);
        
        //localTables = getTables("odsttkj", "odsttkj123", "jdbc:mysql://192.168.0.160:8066/" + srcDb);
        localTables = getTables("odsttkj", "testodsttkj123", "jdbc:mysql://192.168.0.180:8066/" + srcDb);

        // localTables = getTables("luohuijun", "LP4BAksHzMlzHA4Y",
        // "jdbc:mysql://10.1.100.51:3306/" + srcDb);
        
        // test表
       // List<TableVO> testTables = getTables("ttjob", "123456", "jdbc:mysql://192.168.0.180:3306/" + destDb);
        
       // List<TableVO> testTables = getTables("ttjob", "ttjob123456TTJOB", "jdbc:mysql://42.96.206.99:7306/" + destDb);
        //List<TableVO> testTables = getTables("ttkjob", "kd67#yUoKKdzaweS", "jdbc:mysql://121.42.173.45:43066/" + destDb);
        List<TableVO> testTables = getTables("odsttkjob", "df@Eop986dLmnvx", "jdbc:mysql://121.42.173.45:43066/" + destDb);
        
        System.out.println("================================================");
        System.out.println("local总表数：" + localTables.size());
        System.out.println("test总表数：" + testTables.size());
        System.out.println("================================================");
        System.out.println();

        // local表
        int localPriCount = comprePrimary(localTables);
        // test表
        int testPriCount = comprePrimary(testTables);

        System.out.println("================================================");
        System.out.println("local总主键数：" + localPriCount);
        System.out.println("test总主键数：" + testPriCount);
        System.out.println("================================================");
        System.out.println();

        // local表
        int localAutoincrementCount = compreAutoincrement(localTables);
        // test表
        int testAutoincrementCount = compreAutoincrement(testTables);

        System.out.println("================================================");
        System.out.println("local总自动增长数：" + localAutoincrementCount);
        System.out.println("test总自动增长数：" + testAutoincrementCount);
        System.out.println("================================================");
        System.out.println();

        // 以local表比较
        List<TableVO> noLocalTables = compreTables(localTables, testTables);
        writeLoseTable(noLocalTables, srcDb + "_test_lose_table.sql");

        // 以test表比较
        List<TableVO> noTestTables = compreTables(testTables, localTables);
        writeLoseTable(noTestTables, srcDb + "_local_lose_table.sql");

        System.out.println("================================================");
        System.out.println("local存在，test不存在，总表数：" + noLocalTables.size());
        System.out.println("test存在，local不存在，总表数：" + noTestTables.size());
        System.out.println("================================================");
        System.out.println();

        // 以local表列比较
        Map<String, List<ColumnVO>> noLocalColumns = compreColumns(localTables, testTables);
        writeLoseColumn(noLocalColumns, srcDb + "_test_lose_column.sql", true);

        // 以test表列比较
        Map<String, List<ColumnVO>> noTestColumns = compreColumns(testTables, localTables);
        writeLoseColumn(noTestColumns, srcDb + "_local_lose_column.sql", false);

        System.out.println("================================================");
        System.out.println("local列存在，test列不存在，总列数：" + noLocalColumns.size());
        System.out.println("test列存在，local列不存在，总列数：" + noTestColumns.size());
        System.out.println("================================================");
        System.out.println();

        // 以local表列类型，大小比较
        Map<String, Map<ColumnVO, ColumnVO>> noLocalSameColumnsType = compreColumnsType(localTables, testTables);
        writeColumnType(noLocalSameColumnsType, srcDb + "_test_no_same_column_type.sql");

        // 以test表列类型，大小比较
        Map<String, Map<ColumnVO, ColumnVO>> noTestSameColumnsType = compreColumnsType(localTables, testTables);
        writeColumnType(noTestSameColumnsType, srcDb + "_local_no_same_column_type.sql");

        System.out.println("================================================");
        System.out.println("local 比 test列类型，大小不同，总列数：" + noLocalSameColumnsType.size());
        System.out.println("test 比 local列类型，大小不同，总列数：" + noTestSameColumnsType.size());
        System.out.println("================================================");
        System.out.println();
    }

    public static void writeColumnType(Map<String, Map<ColumnVO, ColumnVO>> map, String fileName) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        List<String> list = null;
        try {
            File file = new File(PATH + fileName);
            if (file.exists()) {
                file.delete();
            }
            if (!CommUtil.isNotEmpty(map)) {
                return;
            }
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            list = new ArrayList<String>();
            Set<Entry<String, Map<ColumnVO, ColumnVO>>> set = map.entrySet();
            for (Entry<String, Map<ColumnVO, ColumnVO>> entry : set) {
                String tableName = entry.getKey();
                Map<ColumnVO, ColumnVO> tempMap = entry.getValue();
                if (!CommUtil.isNotEmpty(tempMap) || tempMap.size() == 0) {
                    continue;
                }
                Set<Entry<ColumnVO, ColumnVO>> tempSet = tempMap.entrySet();
                for (Entry<ColumnVO, ColumnVO> tempEntry : tempSet) {
                    ColumnVO srcColumn = tempEntry.getKey();
                    ColumnVO destColumn = tempEntry.getValue();
                    String srcStr = "local   table_name：" + tableName + " column_name：" + srcColumn.getColumnName()
                            + " column_type：" + srcColumn.getTypeName() + " column_size：" + srcColumn.getColumnSize();
                    String destStr = "test   table_name：" + tableName + " column_name：" + destColumn.getColumnName()
                            + " column_type：" + destColumn.getTypeName() + " column_size：" + destColumn.getColumnSize();
                    list.add(srcStr + "\r\n");
                    list.add(destStr + "\r\n");
                }
                list.add("\r\n\r\n");
            }
            if (CommUtil.isNotEmpty(list)) {
                for (String str : list) {
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeLoseColumn(Map<String, List<ColumnVO>> loseMap, String fileName, boolean isAdd) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        List<String> list = null;
        try {
            File file = new File(PATH + fileName);
            if (file.exists()) {
                file.delete();
            }
            if (!CommUtil.isNotEmpty(loseMap)) {
                return;
            }
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            list = new ArrayList<String>();
            Set<Entry<String, List<ColumnVO>>> set = loseMap.entrySet();
            for (Entry<String, List<ColumnVO>> entry : set) {
                String tableName = entry.getKey();
                List<ColumnVO> tempList = entry.getValue();
                for (ColumnVO columnVO : tempList) {
                    String str = "alter table " + tableName + " drop column " + columnVO.getColumnName() + ";";
                    if (isAdd) {
                        str = "alter table " + tableName + " add column " + columnVO.getColumnName() + " "
                                + columnVO.getTypeName();
                        if (CommUtil.isNotEmpty(columnVO.getColumnSize())) {
                            str += "(" + columnVO.getColumnSize() + ")";
                        }
                        if (CommUtil.isNotEmpty(columnVO.getDefaultValue())) {
                            str += " default " + columnVO.getDefaultValue();
                        }
                        if (CommUtil.isNotEmpty(columnVO.getIsNull()) && "0".equals(columnVO.getIsNull())) {
                            str += " not null";
                        }
                        if ("yes".equalsIgnoreCase(columnVO.getIsAutoincrement())) {
                            str += " AUTO_INCREMENT";
                        }
                        str += ";";
                    }
                    list.add(str + "\r\n");
                }
                list.add("\r\n\r\n");
            }
            if (CommUtil.isNotEmpty(list)) {
                for (String str : list) {
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeLoseTable(List<TableVO> loseList, String fileName) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(PATH + fileName);
            if (file.exists()) {
                file.delete();
            }
            if (!CommUtil.isNotEmpty(loseList)) {
                return;
            }
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            List<String> list = new ArrayList<String>();
            for (TableVO tableVO : loseList) {
                list.add(String.format("DROP TABLE IF EXISTS `%S`;", tableVO.getTableName()));
                list.add(String.format("CREATE TABLE `%S` (", tableVO.getTableName()));

                List<ColumnVO> columnList = tableVO.getColumnVOs();
                if (CommUtil.isNotEmpty(columnList)) {
                    for (ColumnVO columnVO : columnList) {

                        String str = String.format("'%S'", columnVO.getColumnName()) + " " + columnVO.getTypeName();
                        if (CommUtil.isNotEmpty(columnVO.getColumnSize())) {
                            str += "(" + columnVO.getColumnSize() + ")";
                        }
                        if (CommUtil.isNotEmpty(columnVO.getDefaultValue())) {
                            str += " default " + columnVO.getDefaultValue();
                        }
                        if (CommUtil.isNotEmpty(columnVO.getIsNull()) && "0".equals(columnVO.getIsNull())) {
                            str += " not null";
                        }
                        if ("yes".equalsIgnoreCase(columnVO.getIsAutoincrement())) {
                            str += " AUTO_INCREMENT";
                        }
                        str += ",";
                        list.add(str);
                    }

                }

                if (CommUtil.isNotEmpty(tableVO.getPrimaryKeyVO())) {
                    list.add(String.format("PRIMARY KEY (`%S`)", tableVO.getPrimaryKeyVO().getPkColumnName()));
                }

                list.add(String.format(") ENGINE=InnoDB AUTO_INCREMENT=%S DEFAULT CHARSET=utf8 COLLATE=utf8_bin;\r\n",
                        1000));
            }

            if (CommUtil.isNotEmpty(list)) {
                for (String str : list) {
                    bufferedWriter.write(str + "\r\n");
                    bufferedWriter.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int compreAutoincrement(List<TableVO> tables) {
        int count = 0;
        for (TableVO tableVO : tables) {
            if (tableVO == null) {
                continue;
            }

            List<ColumnVO> columns = tableVO.getColumnVOs();
            for (ColumnVO columnVO : columns) {

                if (columnVO.getIsAutoincrement().trim().equalsIgnoreCase("yes")) {
                    count++;
                    break;
                }

            }

        }
        return count;
    }

    public static int comprePrimary(List<TableVO> tables) {
        int count = 0;
        for (TableVO tableVO : tables) {
            if (tableVO == null) {
                continue;
            }
            if (CommUtil.isNotEmpty(tableVO.getPrimaryKeyVO())
                    && CommUtil.isNotEmpty(tableVO.getPrimaryKeyVO().getPkColumnName())) {
                count++;
            }

        }
        return count;
    }

    public static boolean compreColumnType(ColumnVO srcColumn, ColumnVO destColumn) {
        if (!srcColumn.getTypeName().trim().equalsIgnoreCase(destColumn.getTypeName().trim())) {
            return false;
        } else if (CommUtil.isNotEmpty(srcColumn.getColumnSize()) && !CommUtil.isNotEmpty(destColumn.getColumnSize())) {
            return false;
        } else if (!CommUtil.isNotEmpty(srcColumn.getColumnSize()) && CommUtil.isNotEmpty(destColumn.getColumnSize())) {
            return false;
        } else if (CommUtil.isNotEmpty(srcColumn.getColumnSize()) && CommUtil.isNotEmpty(destColumn.getColumnSize())
                && !srcColumn.getColumnSize().trim().equalsIgnoreCase(destColumn.getColumnSize().trim())) {
            return false;
        }
        return true;
    }

    public static Map<String, Map<ColumnVO, ColumnVO>> compreColumnsType(List<TableVO> srcTtables,
            List<TableVO> destTables) {
        Map<String, Map<ColumnVO, ColumnVO>> map = new HashMap<String, Map<ColumnVO, ColumnVO>>();
        Map<ColumnVO, ColumnVO> diffColumns = null;
        for (TableVO srcTableVO : srcTtables) {
            if (srcTableVO == null) {
                continue;
            }
            for (TableVO destTableVO : destTables) {
                if (destTableVO == null) {
                    continue;
                }
                if (srcTableVO.getTableName().trim().equalsIgnoreCase(destTableVO.getTableName().trim())) {

                    List<ColumnVO> srcColumns = srcTableVO.getColumnVOs();
                    List<ColumnVO> destColumns = destTableVO.getColumnVOs();
                    diffColumns = new HashMap<ColumnVO, ColumnVO>();
                    for (ColumnVO srcColumnVO : srcColumns) {

                        for (ColumnVO destColumnVO : destColumns) {

                            if (srcColumnVO.getColumnName().trim()
                                    .equalsIgnoreCase(destColumnVO.getColumnName().trim())) {
                                boolean flag = compreColumnType(srcColumnVO, destColumnVO);
                                if (!flag) {
                                    diffColumns.put(srcColumnVO, destColumnVO);
                                }
                                break;
                            }

                        }
                    }

                    if (CommUtil.isNotEmpty(diffColumns) && diffColumns.size() > 0) {
                        map.put(srcTableVO.getTableName(), diffColumns);
                    }

                    break;
                }
            }
        }
        return map;
    }

    public static Map<String, List<ColumnVO>> compreColumns(List<TableVO> srcTtables, List<TableVO> destTables) {
        Map<String, List<ColumnVO>> map = new HashMap<String, List<ColumnVO>>();
        List<ColumnVO> loseColumns = null;
        boolean flag = false;
        for (TableVO srcTableVO : srcTtables) {
            if (srcTableVO == null) {
                continue;
            }
            for (TableVO destTableVO : destTables) {
                if (destTableVO == null) {
                    continue;
                }
                if (srcTableVO.getTableName().trim().equalsIgnoreCase(destTableVO.getTableName().trim())) {

                    List<ColumnVO> srcColumns = srcTableVO.getColumnVOs();
                    List<ColumnVO> destColumns = destTableVO.getColumnVOs();
                    loseColumns = new ArrayList<ColumnVO>();
                    for (ColumnVO srcColumnVO : srcColumns) {

                        flag = false;
                        for (ColumnVO destColumnVO : destColumns) {

                            if (srcColumnVO.getColumnName().trim()
                                    .equalsIgnoreCase(destColumnVO.getColumnName().trim())) {
                                flag = true;
                                break;
                            }

                        }
                        if (!flag) {
                            loseColumns.add(srcColumnVO);
                        }
                    }

                    if (CommUtil.isNotEmpty(loseColumns)) {
                        map.put(srcTableVO.getTableName(), loseColumns);
                    }

                    break;
                }
            }
        }
        return map;
    }

    public static List<TableVO> compreTables(List<TableVO> srcTtables, List<TableVO> destTables) {
        boolean flag = false;
        List<TableVO> loseTables = new ArrayList<TableVO>();
        for (TableVO srcTableVO : srcTtables) {
            if (srcTableVO == null) {
                continue;
            }
            flag = false;
            for (TableVO destTableVO : destTables) {
                if (destTableVO == null) {
                    continue;
                }
                if (srcTableVO.getTableName().trim().equalsIgnoreCase(destTableVO.getTableName().trim())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                loseTables.add(srcTableVO);
            }
        }
        return loseTables;
    }

    public static List<TableVO> getTables(String username, String password, String url) {
        DataSource dataSource = DBFactory.dataSourceInstance();
        dataSource.destory();
        dataSource = DBFactory.dataSourceInstance();
        DriverVO driverVO = dataSource.getDriverVO();
        driverVO.setUser(username);
        driverVO.setPassword(password);
        driverVO.setJdbcUrl(url);
        dataSource.setDriverVO(driverVO);
        List<TableVO> list = dataSource.getTables();
        dataSource.destory();
        return list;
    }

}
