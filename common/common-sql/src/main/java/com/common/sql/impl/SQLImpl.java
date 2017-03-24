package com.common.sql.impl;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.common.sql.Constant;
import com.common.sql.dao.ISQLDao;
import com.common.sql.model.ColumnVO;
import com.common.sql.model.DBVO;
import com.common.sql.model.ForeignKeyVO;
import com.common.sql.model.PrimaryKeyVO;
import com.common.sql.model.ResultColumnVO;
import com.common.sql.model.ResultRowVO;
import com.common.sql.model.TableVO;
import com.common.sql.proxy.DataSource;
import com.common.sql.util.CommUtil;

import java.util.Set;

@SuppressWarnings( { "unused", "unchecked" })
public class SQLImpl implements ISQLDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean addObject(String sql) {
        boolean flag = false;
        try {
            flag = dataSource.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteObject(String sql) {
        boolean flag = false;
        try {
            flag = dataSource.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateObject(String sql) {
        boolean flag = false;
        try {
            flag = dataSource.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<ResultRowVO> queryObject(String sql) {
        List<ResultRowVO> list = null;
        try {
            list = dataSource.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ResultRowVO> queryAllObject(String tableName) {
        List<ResultRowVO> list = null;
        try {
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName).replaceAll("\\?", "*");
            list = dataSource.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ResultRowVO> queryObject(String tableName, String[] columnName) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName);
            list = dataSource.executeQuery(map.get("sql").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> boolean addObject(String tableName, Map<String, T> param) {
        boolean flag = false;
        try {
            String sql = Constant.INSERT.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(param)) {
                String field = "";
                String value = "";
                Set<Entry<String, T>> set = param.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    field += entry.getKey() + ",";
                    value += "?,";
                }
                field = field.substring(0, field.trim().length() - 1);
                value = value.substring(0, value.trim().length() - 1);
                sql = sql.replaceAll("#", field).replaceAll("\\?", value);
                flag = dataSource.execute(sql, param.values().toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> boolean deleteObject(String tableName, Map<String, T> where) {
        boolean flag = false;
        try {
            String sql = Constant.DELETE.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(where)) {
                Set<Entry<String, T>> set = where.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    if (entry.getKey().trim().equalsIgnoreCase("sql")) {
                        sql += " " + entry.getValue() + " ";
                    } else {
                        sql += " and " + entry.getKey() + "=?";
                    }
                }
                flag = dataSource.execute(sql, where.values().toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> List<ResultRowVO> queryObject(String tableName, Map<String, T> where) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, where);
            list = dataSource.executeQuery(map.get("sql").toString(), (T[]) map.get("param"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> boolean updateObject(String tableName, Map<String, T> param) {
        boolean flag = false;
        try {
            String sql = Constant.UPDATE.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(param)) {
                String str = "";
                Set<Entry<String, T>> set = param.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    str += entry.getKey() + "=?,";
                }
                sql = sql.replaceAll("\\?", str.substring(0, str.trim().length() - 1));
                flag = dataSource.execute(sql, param.values().toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> boolean addObject(String tableName, T[] param) {
        boolean flag = false;
        try {
            String sql = Constant.INSERT_ALL.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(param)) {
                String value = "";
                for (T t : param) {
                    value += "?,";
                }
                value = value.substring(0, value.trim().length() - 1);
                sql = sql.replaceAll("\\?", value);
                flag = dataSource.execute(sql, param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> ResultRowVO addObjectReturn(String tableName, T[] param) {
        ResultRowVO resultRow = null;
        try {
            String sql = Constant.INSERT_ALL.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(param)) {
                String value = "";
                for (T t : param) {
                    value += "?,";
                }
                value = value.substring(0, value.trim().length() - 1);
                sql = sql.replaceAll("\\?", value);
                Object pk = dataSource.executeMethod(sql, param);
                resultRow = queryObject(pk, tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultRow;
    }

    public <T> List<ResultRowVO> queryObject(String tableName, String[] columnName, Map<String, T> where) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName, where);
            list = dataSource.executeQuery(map.get("sql").toString(), (T[]) map.get("param"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> queryObject(String tableName, String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> between, Map<String, Object> and) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName, param, like, between, and);
            list = dataSource.executeQuery(map.get("sql").toString(), (T[]) map.get("param"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> queryObject(String tableName, String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> gt, Map<String, Object> lt, Map<String, Object[]> in) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName, param, like, gt, lt, in);
            list = dataSource.executeQuery(map.get("sql").toString(), (T[]) map.get("param"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> boolean updateObject(String tableName, Map<String, T> param, Map<String, T> where) {
        boolean flag = false;
        try {
            String sql = Constant.UPDATE.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(param) && CommUtil.isNotEmpty(where)) {
                String str = "";
                List<Object> list = new ArrayList<Object>();
                Set<Entry<String, T>> set = param.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    str += entry.getKey() + "=?,";
                    list.add(entry.getValue());
                }
                str = str.substring(0, str.length() - 1);
                sql = sql.replaceAll("\\?", str);
                set = where.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    if (entry.getKey().trim().equalsIgnoreCase("sql")) {
                        sql += " " + entry.getValue() + " ";
                    } else {
                        sql += " and " + entry.getKey() + "=?";
                        list.add(entry.getValue());
                    }
                }
                flag = dataSource.execute(sql, list.toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> ResultRowVO updateObjectReturn(String tableName, Map<String, T> param, Map<String, T> where) {
        ResultRowVO resultRow = null;
        try {
            String sql = Constant.UPDATE.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(param) && CommUtil.isNotEmpty(where)) {
                String str = "";
                List<Object> list = new ArrayList<Object>();
                Set<Entry<String, T>> set = param.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    str += entry.getKey() + "=?,";
                    list.add(entry.getValue());
                }
                str = str.substring(0, str.length() - 1);
                sql = sql.replaceAll("\\?", str);
                set = where.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    if (entry.getKey().trim().equalsIgnoreCase("sql")) {
                        sql += " " + entry.getValue() + " ";
                    } else {
                        sql += " and " + entry.getKey() + "=?";
                        list.add(entry.getValue());
                    }
                }
                Object pk = dataSource.executeMethod(sql, list.toArray());
                resultRow = queryObject(pk, tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultRow;
    }

    public <T> boolean deleteObject(T pk, String tableName) {
        boolean flag = false;
        try {
            String sql = Constant.DELETE.replaceAll(Constant.TABLE, tableName) + " and "
                    + queryPrimaryKey(tableName).getPkColumnName() + "=?";
            flag = dataSource.execute(sql, pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> boolean deleteObject(String key, T value, String tableName) {
        boolean flag = false;
        try {
            String sql = Constant.DELETE.replaceAll(Constant.TABLE, tableName) + " and " + key + "=?";
            flag = dataSource.execute(sql, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> T queryObject(Object pk, String tableName) {
        List<ResultRowVO> list = null;
        try {
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName).replaceAll("\\?", "*") + " and "
                    + queryPrimaryKey(tableName).getPkColumnName() + "=?";
            list = dataSource.executeQuery(sql, pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommUtil.isNotEmpty(list) ? (T) list.get(0) : null;
    }

    public <T> List<ResultRowVO> queryObject(String key, T value, String tableName) {
        List<ResultRowVO> list = null;
        try {
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName).replaceAll("\\?", "*") + " and " + key
                    + "=?";
            list = dataSource.executeQuery(sql, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> queryObject(String key, T value, String[] columnName, String tableName) {
        List<ResultRowVO> list = null;
        try {
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName);
            String str = "";
            for (String column : columnName) {
                str += column + ",";
            }
            str = str.substring(0, str.length() - 1);
            sql = sql.replaceAll("\\?", str) + " and " + key + "=?";
            list = dataSource.executeQuery(sql, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> boolean updateObject(T pk, String columnName, T columnValue, String tableName) {
        boolean flag = false;
        try {
            String sql = Constant.UPDATE.replaceAll(Constant.TABLE, tableName);
            sql = sql.replaceAll("\\?", columnName + "=?") + " and " + queryPrimaryKey(tableName).getPkColumnName()
                    + "=?";
            flag = dataSource.execute(sql, new Object[] { columnValue, pk });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> boolean updateObject(String key, T value, String columnName, T columnValue, String tableName) {
        boolean flag = false;
        try {
            String sql = Constant.UPDATE.replaceAll(Constant.TABLE, tableName);
            sql = sql.replaceAll("\\?", columnName + "=?") + " and " + key + "=?";
            flag = dataSource.execute(sql, new Object[] { columnValue, value });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean execute(String sql) {
        boolean flag = false;
        try {
            flag = dataSource.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> boolean execute(String sql, T param) {
        boolean flag = false;
        try {
            flag = dataSource.execute(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public <T> boolean execute(String sql, T[] param) {
        boolean flag = false;
        try {
            flag = dataSource.execute(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<ResultRowVO> executeQuery(String sql) {
        List<ResultRowVO> list = null;
        try {
            list = dataSource.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> executeQuery(String sql, T param) {
        List<ResultRowVO> list = null;
        try {
            list = dataSource.executeQuery(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> executeQuery(String sql, T[] param) {
        List<ResultRowVO> list = null;
        try {
            list = dataSource.executeQuery(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean executeBatch(String[] sql) {
        boolean flag = false;
        Connection conn = null;
        Statement stmt = null;
        int[] result = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            if (CommUtil.isNotEmpty(sql)) {
                for (String sqlStr : sql) {
                    stmt.addBatch(sqlStr);
                }
            }
            result = stmt.executeBatch(); // 批量执行
            conn.commit();
            conn.setAutoCommit(true); // 此句必不可少,不然会引发死锁现象
            if (dataSource.isShowSql()) {
                for (String sqlStr : sql) {
                    System.out.println(sqlStr);
                }
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            dataSource.close();
        }
        return flag = (sql.length == result.length);
    }

    public <T> T queryFounction(String sql) {
        T result = null;
        try {
            List<ResultRowVO> list = dataSource.executeQuery(sql);
            if (CommUtil.isNotEmpty(list)) {
                if (list.size() > 1) {
                    result = (T) list;
                } else {
                    result = (T) list.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T queryFounction(String tableName, Map<String, Object> param) {
        T result = null;
        try {
            if (CommUtil.isNotEmpty(param)) {
                String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName);
                String str = "";
                String order = "";
                String where = "";
                String group = "";
                List<Object> paramArray = new ArrayList<Object>();
                Set<Entry<String, Object>> set = param.entrySet();
                for (Iterator<Entry<String, Object>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey().trim();
                    Object value = entry.getValue();
                    if (key.equalsIgnoreCase("avg") || key.equalsIgnoreCase("max") || key.equalsIgnoreCase("min")
                            || key.equalsIgnoreCase("count") || key.equalsIgnoreCase("sum")) {
                        str += key + "(" + value + "), ";
                    } else if (key.equalsIgnoreCase("grounp")) {
                        group += key + ", ";
                    } else if (key.equalsIgnoreCase("desc") || key.equalsIgnoreCase("asc")) {
                        order += " order by " + value + " " + key + ", ";
                    } else if (key.equalsIgnoreCase("sql")) {
                        where += " " + value + " ";
                    } else {
                        where += " and " + key + "=? ";
                        paramArray.add(value);
                    }
                }
                str = !"".equals(str) ? str.substring(0, str.trim().length() - 1) : "";
                if (CommUtil.isNotEmpty(group)) {
                    group = " grounp by " + group.substring(0, group.trim().length() - 1);
                    if (order != null && !order.trim().equals("")) {
                        group += order.substring(0, order.trim().length() - 1);
                    }
                    where += group;
                }
                sql = sql.replaceAll("\\?", str) + where;
                List<ResultRowVO> list = dataSource.executeQuery(sql, paramArray.toArray());
                if (CommUtil.isNotEmpty(list)) {
                    if (list.size() > 1) {
                        result = (T) list;
                    } else {
                        result = (T) list.get(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<ResultRowVO> queryPage(String sql, int currPage, int pageSize) {
        List<ResultRowVO> list = null;
        try {
            list = executePage(sql, null, null, currPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int queryCount(String sql) {
        int count = 0;
        try {
            List<ResultRowVO> list = dataSource.executeQuery(sql);
            if (CommUtil.isNotEmpty(list)) {
                count = Integer.valueOf(list.get(0).getColumnArray()[0].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<ColumnVO> queryColumn(String tableName) {
        List<ColumnVO> list = null;
        try {
            list = dataSource.getColumns(null, null, tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> T parseData(ResultRowVO resultRow, Class<T> obj) {
        T t = null;
        try {
            t = obj.newInstance();
            Method[] methods = obj.getDeclaredMethods();
            List<ResultColumnVO> columnList = resultRow.getColumnList();
            for (ResultColumnVO resultColumn : columnList) {
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("set" + resultColumn.getColumnName())) {
                        Object objValue = resultColumn.getColumnValue();
                        if (objValue != null) {
                            method.invoke(t, objValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> List<T> parseData(List<ResultRowVO> rowList, Class<T> obj) {
        List<T> list = null;
        try {
            if (CommUtil.isNotEmpty(rowList)) {
                list = new ArrayList<T>();
                for (ResultRowVO resultRow : rowList) {
                    list.add(parseData(resultRow, obj));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public PrimaryKeyVO queryPrimaryKey(String tableName) {
        PrimaryKeyVO prKeyInfo = null;
        try {
            prKeyInfo = dataSource.getPrimaryKeyInfo(null, null, tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prKeyInfo;
    }

    public List<ForeignKeyVO> queryForeignKey(String tableName) {
        List<ForeignKeyVO> list = null;
        try {
            list = dataSource.getForeignKeys(null, null, tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public TableVO queryTable(String tableName) {
        TableVO tableInfo = null;
        try {
            tableInfo = dataSource.getTableInfo(null, null, tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableInfo;
    }

    public <T> List<ResultRowVO> executePage(String sql, String tableName, T[] param, int currPage, int pageSize) {
        List<ResultRowVO> list = null;
        try {
            if (currPage < 1) {
                currPage = 1;
            }
            List<Object> paramArray = new ArrayList<Object>();
            if (param != null && param.length > 0) {
                for (int i = 0; i < param.length; i++) {
                    paramArray.add(param[i]);
                }
            }
            paramArray.add((currPage - 1) * pageSize);
            paramArray.add(pageSize);
            DBVO dbInfo = dataSource.getDBInfo();
            if (dbInfo.getDbName().equalsIgnoreCase(Constant.MYSQL)) {
                sql += Constant.MYSQL_PAGE;
            } else if (dbInfo.getDbName().equalsIgnoreCase(Constant.ORACLE)) {
                sql = sql.replaceAll("#", Constant.ORACLE_PAGE);
            } else if (dbInfo.getDbName().equalsIgnoreCase(Constant.MYSQL)
                    && (!"".equals(tableName.trim()) && tableName != null)) {
                sql = sql.replaceAll("##", queryPrimaryKey(tableName).getPkColumnName()).replaceAll("#",
                        Constant.MSSQL_PAGE);
            } else {
                ResultSet rs = dataSource.getResultSet(sql, paramArray.toArray());
                int index = 0;
                Object[] obj = null;
                list = new ArrayList<ResultRowVO>();
                int row = (currPage - 1) * pageSize;
                if (!rs.absolute(row)) {
                    for (int i = 0; i < row; i++) {
                        rs.next();
                    }
                }
                while (rs.next()) {
                    ResultSetMetaData rSetMetaData = rs.getMetaData();
                    int columnCount = rSetMetaData.getColumnCount();
                    obj = new Object[columnCount];
                    ResultRowVO resultRow = new ResultRowVO();
                    List<ResultColumnVO> columnList = new ArrayList<ResultColumnVO>();
                    for (int i = 1; i <= columnCount; i++) {
                        obj[i - 1] = rs.getObject(i);
                        ResultColumnVO resultColumn = new ResultColumnVO();
                        resultColumn.setCatalogName(rSetMetaData.getCatalogName(index));
                        resultColumn.setColumnClassName(rSetMetaData.getColumnClassName(index));
                        resultColumn.setColumnCount(rSetMetaData.getColumnCount());
                        resultColumn.setColumnName(rSetMetaData.getColumnName(index));
                        resultColumn.setColumnSize(rSetMetaData.getColumnDisplaySize(index));
                        resultColumn.setColumnType(rSetMetaData.getColumnType(index));
                        resultColumn.setColumnTypeName(rSetMetaData.getColumnTypeName(index));
                        resultColumn.setColumnValue(obj[i - 1]);
                        resultColumn.setSchemaName(rSetMetaData.getSchemaName(index));
                        resultColumn.setTableName(rSetMetaData.getTableName(index));
                        columnList.add(resultColumn);
                    }
                    resultRow.setId(++index);
                    resultRow.setColumnArray(obj);
                    resultRow.setColumnList(columnList);
                    list.add(resultRow);
                }
                return list;
            }
            list = dataSource.executeQuery(sql, paramArray.toArray());
        } catch (Exception e) {
            try {
                throw new Exception("SQL:" + sql, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            dataSource.close();
        }
        return list;
    }

    public List<ResultRowVO> queryPage(String tableName, Map<String, Object> where, int currPage, int pageSize) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, where);
            list = executePage(map.get("sql").toString(), tableName, (Object[]) map.get("param"), currPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ResultRowVO> queryPage(String tableName, String[] columnName, int currPage, int pageSize) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName);
            list = executePage(map.get("sql").toString(), tableName, null, currPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ResultRowVO> queryPage(String tableName, String[] columnName, Map<String, Object> where, int currPage,
            int pageSize) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName, where);
            list = executePage(map.get("sql").toString(), tableName, (Object[]) map.get("param"), currPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> queryPage(String tableName, String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> between, Map<String, Object> and, int currPage, int pageSize) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName, param, like, between, and);
            list = executePage(map.get("sql").toString(), tableName, (T[]) map.get("param"), currPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<ResultRowVO> queryPage(String tableName, String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> gt, Map<String, Object> lt, Map<String, Object[]> in,
            int currPage, int pageSize) {
        List<ResultRowVO> list = null;
        try {
            Map<String, Object> map = querySql(tableName, columnName, param, like, gt, lt, in);
            list = executePage(map.get("sql").toString(), tableName, (T[]) map.get("param"), currPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private <T> Map<String, Object> querySql(String tableName, String[] columnName) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            String str = "";
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName);
            if (CommUtil.isNotEmpty(columnName)) {
                for (String column : columnName) {
                    str += column + ",";
                }
                sql = sql.replaceAll("\\?", str.substring(0, str.length() - 1));
            } else {
                sql = sql.replaceAll("\\?", "*");
            }
            map.put("sql", sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private <T> Map<String, Object> querySql(String tableName, String[] columnName, Map<String, T> where) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName);
            String str = "";
            if (CommUtil.isNotEmpty(columnName)) {
                for (String column : columnName) {
                    str += column + ",";
                }
                str = str.substring(0, str.length() - 1);
                sql = sql.replaceAll("\\?", str);
            } else {
                sql = sql.replaceAll("\\?", "*");
            }
            if (CommUtil.isNotEmpty(where)) {
                String order = "";
                Set<Entry<String, T>> set = where.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    if (entry.getKey().trim().equalsIgnoreCase("asc") || entry.getKey().trim().equalsIgnoreCase("desc")) {
                        str = " order by " + entry.getValue() + " " + entry.getKey();
                    } else if (entry.getKey().trim().equalsIgnoreCase("sql")) {
                        sql += " " + entry.getValue() + " ";
                    } else {
                        sql += " and " + entry.getKey() + "=?";
                    }
                }
                sql += order;
                map.put("param", where.values().toArray());
            }
            map.put("sql", sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private <T> Map<String, Object> querySql(String tableName, Map<String, T> where) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName).replaceAll("\\?", "*");
            if (CommUtil.isNotEmpty(where)) {
                String order = "";
                Set<Entry<String, T>> set = where.entrySet();
                for (Iterator<Entry<String, T>> iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, T> entry = iterator.next();
                    if (entry.getKey().trim().equalsIgnoreCase("asc") || entry.getKey().trim().equalsIgnoreCase("desc")) {
                        order = " order by " + entry.getValue() + " " + entry.getKey();
                    } else if (entry.getKey().trim().equalsIgnoreCase("sql")) {
                        sql += " " + entry.getValue() + " ";
                    } else {
                        sql += " and " + entry.getKey() + "=?";
                    }
                }
                sql += order;
                map.put("param", where.values().toArray());
            }
            map.put("sql", sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @SuppressWarnings("rawtypes")
    private Map<String, Object> querySql(String tableName, String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> gt, Map<String, Object> lt, Map<String, Object[]> in) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName);
            List<Object> paramArray = new ArrayList<Object>();
            if (CommUtil.isNotEmpty(columnName)) {
                String str = "";
                for (String column : columnName) {
                    str += column + ",";
                }
                str = str.substring(0, str.length() - 1);
                sql = sql.replaceAll("\\?", str);
            } else {
                sql = sql.replaceAll("\\?", "*");
            }

            String order = "";
            if (CommUtil.isNotEmpty(param)) {
                Iterator<Entry<String, Object>> iterator = param.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey().trim();
                    Object value = entry.getValue();
                    if (key.equalsIgnoreCase("asc") || key.equalsIgnoreCase("desc")) {
                        order += " order by " + value + " " + key + ", ";
                    } else if (key.equalsIgnoreCase("sql")) {
                        sql += " " + value + " ";
                    } else {
                        sql += " and " + key + "=? ";
                        paramArray.add(value);
                    }
                }
            }
            if (CommUtil.isNotEmpty(gt)) {
                Iterator iteratorGt = gt.entrySet().iterator();
                while (iteratorGt.hasNext()) {
                    Entry entryGt = (Entry) iteratorGt.next();
                    sql += " and " + entryGt.getKey() + " >? ";
                    paramArray.add(entryGt.getValue());
                }
            }
            if (CommUtil.isNotEmpty(lt)) {
                Iterator iteratorLt = lt.entrySet().iterator();
                while (iteratorLt.hasNext()) {
                    Entry entryLt = (Entry) iteratorLt.next();
                    sql += " and " + entryLt.getKey() + " <? ";
                    paramArray.add(entryLt.getValue());
                }
            }
            if (CommUtil.isNotEmpty(in)) {
                Iterator iteratorIn = in.entrySet().iterator();
                while (iteratorIn.hasNext()) {
                    Entry entryIn = (Entry) iteratorIn.next();
                    String inStr = "";
                    Object[] obj = (Object[]) entryIn.getValue();
                    for (int i = 0; i < obj.length; i++) {
                        inStr += "?,";
                        paramArray.add(obj[i]);
                    }
                    inStr = inStr.substring(0, inStr.trim().length() - 1);
                    sql += " and " + entryIn.getKey() + " in (" + inStr + ")";
                }
            }
            if (CommUtil.isNotEmpty(like)) {
                Iterator iteratorLike = like.entrySet().iterator();
                while (iteratorLike.hasNext()) {
                    Entry entryLike = (Entry) iteratorLike.next();
                    sql += " and " + entryLike.getKey() + " like ? ";
                    paramArray.add("%" + entryLike.getValue() + "%");
                }
            }
            if (CommUtil.isNotEmpty(order)) {
                sql += order;
            }
            map.put("sql", sql);
            map.put("param", paramArray.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @SuppressWarnings("rawtypes")
    private Map<String, Object> querySql(String tableName, String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> between, Map<String, Object> and) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            String sql = Constant.SELECT.replaceAll(Constant.TABLE, tableName);
            List<Object> paramArray = new ArrayList<Object>();
            if (CommUtil.isNotEmpty(columnName)) {
                String str = "";
                for (String column : columnName) {
                    str += column + ",";
                }
                str = str.substring(0, str.length() - 1);
                sql = sql.replaceAll("\\?", str);
            } else {
                sql = sql.replaceAll("\\?", "*");
            }

            String order = "";
            if (CommUtil.isNotEmpty(param)) {
                Iterator<Entry<String, Object>> iterator = param.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey().trim();
                    Object value = entry.getValue();
                    if (key.equalsIgnoreCase("asc") || key.equalsIgnoreCase("desc")) {
                        order += " order by " + value + " " + key + ", ";
                    } else if (key.equalsIgnoreCase("sql")) {
                        sql += " " + value + " ";
                    } else {
                        sql += " and " + key + "=? ";
                        paramArray.add(value);
                    }
                }
            }
            if (CommUtil.isNotEmpty(between) && CommUtil.isNotEmpty(and) && between.size() == and.size()) {
                Iterator iteratorBetween = between.entrySet().iterator();
                Iterator iteratorAnd = and.entrySet().iterator();
                while (iteratorBetween.hasNext() && iteratorAnd.hasNext()) {
                    Entry entryBetween = (Entry) iteratorBetween.next();
                    Entry entryAnd = (Entry) iteratorAnd.next();
                    sql += " and " + entryBetween.getKey() + " between ?" + " and ?  ";
                    paramArray.add(entryBetween.getValue());
                    paramArray.add(entryAnd.getValue());
                }
            }
            if (CommUtil.isNotEmpty(like)) {
                Iterator iteratorLike = like.entrySet().iterator();
                while (iteratorLike.hasNext()) {
                    Entry entryLike = (Entry) iteratorLike.next();
                    sql += " and " + entryLike.getKey() + " like ? ";
                    paramArray.add("%" + entryLike.getValue() + "%");
                }
            }
            if (CommUtil.isNotEmpty(order)) {
                sql += order;
            }
            map.put("sql", sql);
            map.put("param", paramArray.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
