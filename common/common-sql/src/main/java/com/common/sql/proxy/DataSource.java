package com.common.sql.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import com.common.sql.dao.ISQLDao;
import com.common.sql.impl.SQLImpl;
import com.common.sql.model.CatalogVO;
import com.common.sql.model.ColumnVO;
import com.common.sql.model.DBVO;
import com.common.sql.model.DriverVO;
import com.common.sql.model.ForeignKeyVO;
import com.common.sql.model.PrimaryKeyVO;
import com.common.sql.model.ResultColumnVO;
import com.common.sql.model.ResultRowVO;
import com.common.sql.model.SchemaVO;
import com.common.sql.model.TableVO;
import com.common.sql.util.CommUtil;

/**
 * SQL数据源
 * 
 * @author 枫之叶
 * 
 */
public class DataSource {

    /**
     * 是否显示SQL
     */
    private boolean showSql = false;

    /**
     * 当前操作的SQL语句
     */
    public String sql = null;

    /**
     * 数据库连接信息
     */
    private DriverVO driverVO = null;

    /**
     * Connection连接对象
     */
    private static Connection conn = null;

    private static DataSource dataSource = null;

    private static PreparedStatement pstmt = null;

    private static ResultSet rs = null;

    private static Statement stmt = null;

    private static CallableStatement callStmt = null;

    private static ResultSetMetaData rSetMetaData = null;

    private static DatabaseMetaData dataMetaData = null;

    /**
     * 异步对象
     */
    private static final Object obj = new Object();

    private static SQLImpl objectDao = null;

    /**
     * 私有类
     */
    private DataSource() {
        driverVO = new DriverVO();
    }

    /**
     * 获取Object_Dao对象
     * 
     * @return
     */
    public synchronized ISQLDao sqlDaoInstance() {
        try {
            if (objectDao == null) {
                objectDao = new SQLImpl();
                objectDao.setDataSource(dataSource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectDao;
    }

    /**
     * 获取DataSource单例对象
     * 
     * @return
     */
    public static DataSource newInstance() {
        try {
            if (dataSource == null) {
                synchronized (obj) {
                    if (dataSource == null) {
                        dataSource = new DataSource();
                        dataSource.getProperties();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 获取DataSource单例对象
     * 
     * @return
     */
    public static DataSource newInstance(Connection conn) {
        try {
            if (dataSource == null) {
                synchronized (obj) {
                    if (dataSource == null) {
                        dataSource = new DataSource();
                    }
                }
            }
            DataSource.conn = conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 获取Properties文件，读取数据库连接配置
     */
    private void getProperties() {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            prop.load(DataSource.class.getClassLoader().getResourceAsStream("config.properties"));
            Set<Entry<Object, Object>> element = prop.entrySet();
            for (Iterator<Entry<Object, Object>> iterator = element.iterator(); iterator.hasNext();) {
                Entry<Object, Object> entry = (Entry<Object, Object>) iterator.next();
                String key = entry.getKey().toString().trim();
                String value = entry.getValue().toString().trim();
                if (key != null) {
                    if (key.toLowerCase().indexOf("driver") > -1) {
                        driverVO.setDriverClass(value);
                    } else if (key.toLowerCase().indexOf("url") > -1) {
                        driverVO.setJdbcUrl(value);
                    } else if (key.toLowerCase().indexOf("user") > -1) {
                        driverVO.setUser(value);
                    } else if (key.toLowerCase().indexOf("password") > -1) {
                        driverVO.setPassword(value);
                    } else if (key.toLowerCase().indexOf("showsql") > -1 || key.toLowerCase().indexOf("show_sql") > -1) {
                        showSql = Boolean.valueOf(value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取数据库连接
     * 
     * @return
     */
    public synchronized Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = null;
                Class.forName(driverVO.getDriverClass());
                Properties props =new Properties();
                props.setProperty("user", driverVO.getUser());
                props.setProperty("password", driverVO.getPassword());
                props.setProperty("remarks", "true"); //设置可以获取remarks信息 
                props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
                conn=DriverManager.getConnection(driverVO.getJdbcUrl(), props);
                //conn = DriverManager.getConnection(driverVO.getJdbcUrl(), driverVO.getUser(), driverVO.getPassword());
            }
        } catch (Exception e) {
            close(conn);
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取DatabaseMetaData数据集
     * 
     * @return
     */
    public DatabaseMetaData getDatabaseMetaData() {
        try {
            getConnection();
            if (dataMetaData == null) {
                synchronized (obj) {
                    if (dataMetaData == null) {
                        dataMetaData = conn.getMetaData();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMetaData;
    }

    /**
     * 获取数据库信息
     * 
     * @return
     */
    public DBVO getDBInfo() {
        DBVO dbInfo = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                dbInfo = new DBVO();
                dbInfo.setDbType(dataMetaData.getDatabaseProductName());
                dbInfo.setDbName(dataMetaData.getDatabaseProductName());
                dbInfo.setDbVersion(dataMetaData.getDatabaseProductVersion());
                dbInfo.setDbDriverVersion(dataMetaData.getDriverVersion());
                dbInfo.setDbUrl(dataMetaData.getURL());
                dbInfo.setDbUserName(dataMetaData.getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbInfo;
    }

    /**
     * 获取Catalog信息
     * 
     * @return
     */
    public List<CatalogVO> getCatalogInfo() {
        List<CatalogVO> list = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                list = new ArrayList<CatalogVO>();
                ResultSet rs = dataMetaData.getCatalogs();
                while (rs.next()) {
                    CatalogVO catalogInfo = new CatalogVO();
                    catalogInfo.setCatalogName(rs.getString("TABLE_CAT"));
                    catalogInfo.setDbvo(getDBInfo());
                    list.add(catalogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取Schema信息
     * 
     * @return
     */
    public List<SchemaVO> getSchemaInfo() {
        List<SchemaVO> list = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                list = new ArrayList<SchemaVO>();
                ResultSet rs = dataMetaData.getSchemas();
                while (rs.next()) {
                    SchemaVO schemaInfo = new SchemaVO();
                    schemaInfo.setSchemaName(rs.getString("TABLE_SCHEM"));
                    CatalogVO catalogInfo = new CatalogVO();
                    catalogInfo.setCatalogName(rs.getString("TABLE_CATALOG"));
                    catalogInfo.setDbvo(getDBInfo());
                    list.add(schemaInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取所有表信息
     * 
     * @return
     */
    public List<TableVO> getTables() {
        List<TableVO> list = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                list = new ArrayList<TableVO>();
                ResultSet rs = dataMetaData.getTables(null, null, null, new String[] { "TABLE" });
                while (rs.next()) {
                    TableVO tableInfo = new TableVO();
                    tableInfo.setRemarks(rs.getString("REMARKS"));
                    tableInfo.setTableName(rs.getString("TABLE_NAME"));
                    tableInfo.setTableType(rs.getString("TABLE_TYPE"));

                    SchemaVO schemaInfo = getSchemaInfo(rs);
                    tableInfo.setPrimaryKeyVO(getPrimaryKeyInfo(schemaInfo.getCatalogVO().getCatalogName(), schemaInfo
                            .getSchemaName(), tableInfo.getTableName()));
                    tableInfo.setForeignKeyVOs(getForeignKeys(schemaInfo.getCatalogVO().getCatalogName(), schemaInfo
                            .getSchemaName(), tableInfo.getTableName()));
                    tableInfo.setColumnVOs(getColumns(schemaInfo.getCatalogVO().getCatalogName(), schemaInfo
                            .getSchemaName(), tableInfo.getTableName()));

                    tableInfo.setSchemaVO(schemaInfo);
                    list.add(tableInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取表信息
     * 
     * @return
     */
    public TableVO getTableInfo(String catalog, String schema, String tableName) {
        TableVO tableInfo = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                rs = dataMetaData.getTables(catalog, schema, tableName, new String[] { "TABLE" });
                if (rs.next()) {
                    tableInfo = new TableVO();
                    tableInfo.setRemarks(rs.getString("REMARKS"));
                    tableInfo.setTableName(rs.getString("TABLE_NAME"));
                    tableInfo.setTableType(rs.getString("TABLE_TYPE"));
                    tableInfo.setPrimaryKeyVO(getPrimaryKeyInfo(catalog, schema, tableName));
                    tableInfo.setForeignKeyVOs(getForeignKeys(catalog, schema, tableName));
                    tableInfo.setColumnVOs(getColumns(catalog, schema, tableName));
                    tableInfo.setSchemaVO(getSchemaInfo(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableInfo;
    }

    /**
     * 获取所有的列信息
     * 
     * @return
     */
    public List<ColumnVO> getColumns(String catalog, String schemaPattern, String tableNamePattern) {
        List<ColumnVO> list = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                list = new ArrayList<ColumnVO>();
                ResultSet rs = dataMetaData.getColumns(catalog, schemaPattern, tableNamePattern, "%");
                while (rs.next()) {
                    ColumnVO columnInfo = new ColumnVO();
                    
                    columnInfo.setColumnName(rs.getString("COLUMN_NAME"));
                    columnInfo.setDataType(rs.getString("DATA_TYPE"));
                    columnInfo.setTypeName(rs.getString("TYPE_NAME"));
                    columnInfo.setColumnSize(rs.getString("COLUMN_SIZE"));
                    columnInfo.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                    columnInfo.setIsNull(rs.getString("NULLABLE"));
                    columnInfo.setRemarks(rs.getString("REMARKS"));
                    columnInfo.setDefaultValue(rs.getString("COLUMN_DEF"));
                    columnInfo.setColumnIndex(rs.getInt("ORDINAL_POSITION"));
                    columnInfo.setIsAutoincrement(rs.getString("IS_AUTOINCREMENT"));
                    
                    list.add(columnInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取列信息
     * 
     * @return
     */
    public List<ColumnVO> getColumnVO(String catalog, String schemaPattern, String tableNamePattern,
            String columnNamePattern) {
        List<ColumnVO> list = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                list = new ArrayList<ColumnVO>();
                ResultSet rs = dataMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
                while (rs.next()) {
                    ColumnVO columnInfo = new ColumnVO();
                    columnInfo.setColumnName(rs.getString("COLUMN_NAME"));
                    columnInfo.setDataType(rs.getString("DATA_TYPE"));
                    columnInfo.setTypeName(rs.getString("TYPE_NAME"));
                    columnInfo.setColumnSize(rs.getString("COLUMN_SIZE"));
                    columnInfo.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                    columnInfo.setIsNull(rs.getString("NULLABLE"));
                    columnInfo.setRemarks(rs.getString("REMARKS"));
                    columnInfo.setDefaultValue(rs.getString("COLUMN_DEF"));
                    columnInfo.setColumnIndex(rs.getInt("ORDINAL_POSITION"));
                    columnInfo.setIsAutoincrement(rs.getString("IS_AUTOINCREMENT"));
                    list.add(columnInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取主键信息
     * 
     * @return
     */
    public PrimaryKeyVO getPrimaryKeyInfo(String catalog, String schema, String tableName) {
        PrimaryKeyVO primaryKeyInfo = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                ResultSet rs = dataMetaData.getPrimaryKeys(catalog, schema, tableName);
                if (rs.next()) {
                    primaryKeyInfo = new PrimaryKeyVO();
                    primaryKeyInfo.setPkName(rs.getString("PK_NAME"));
                    primaryKeyInfo.setPkIndex(rs.getString("KEY_SEQ"));
                    primaryKeyInfo.setPkColumnName(rs.getString("COLUMN_NAME"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKeyInfo;
    }

    /**
     * 获取外键信息
     * 
     * @return
     */
    public List<ForeignKeyVO> getForeignKeys(String catalog, String schema, String tableName) {
        List<ForeignKeyVO> list = null;
        try {
            DatabaseMetaData dataMetaData = getDatabaseMetaData();
            if (dataMetaData != null) {
                list = new ArrayList<ForeignKeyVO>();
                ResultSet rs = dataMetaData.getImportedKeys(catalog, schema, tableName);
                while (rs.next()) {
                    ForeignKeyVO foreignKeyInfo = new ForeignKeyVO();
                    foreignKeyInfo.setFkColumnName(rs.getString("FKCOLUMN_NAME"));
                    foreignKeyInfo.setFkIndex(rs.getString("KEY_SEQ"));
                    foreignKeyInfo.setFkName(rs.getString("FK_NAME"));
                    foreignKeyInfo.setFkTableName(rs.getString("FKTABLE_NAME"));
                    foreignKeyInfo.setPkColumnName(rs.getString("PKCOLUMN_NAME"));
                    foreignKeyInfo.setPkTableName(rs.getString("PKTABLE_NAME"));
                    list.add(foreignKeyInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取Schema信息
     * 
     * @param rs
     * @return
     */
    private SchemaVO getSchemaInfo(ResultSet rs) {
        SchemaVO schemaInfo = null;
        try {
            if (rs != null) {
                schemaInfo = new SchemaVO();
                CatalogVO catalogInfo = new CatalogVO();
                catalogInfo.setCatalogName(rs.getString("TABLE_CAT"));
                catalogInfo.setDbvo(getDBInfo());
                schemaInfo.setSchemaName(rs.getString("TABLE_SCHEM"));
                schemaInfo.setCatalogVO(catalogInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schemaInfo;
    }

    /**
     * 获取Statement对象
     * 
     * @return
     */
    public Statement getStatement() {
        try {
            stmt = getConnection().createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }

    /**
     * 获取PreparedStatement对象
     * 
     * @param sql
     * @return
     */
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            pstmt = getConnection().prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 获取PreparedStatement对象
     * 
     * @param sql
     * @param param
     * @return
     */
    public <T> PreparedStatement getPreparedStatement(String sql, T param) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            pstmt.setObject(1, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 获取PreparedStatement对象
     * 
     * @param sql
     * @param param
     * @return
     */
    public <T> PreparedStatement getPreparedStatement(String sql, T[] param) {
        try {
            pstmt = getConnection().prepareStatement(sql);
            if (CommUtil.isNotEmpty(param)) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 获取CallableStatement对象，用于执行存储过程
     * 
     * @param sql
     * @return
     */
    public CallableStatement getCallableStatement(String sql) {
        try {
            callStmt = getConnection().prepareCall(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callStmt;
    }

    /**
     * 获取结果集对象（ResultSet）
     * 
     * @param pstmt
     * @return
     */
    public ResultSet getResultSet(PreparedStatement pstmt) {
        try {
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 获取结果集对象（ResultSet）
     * 
     * @param stmt
     * @param sql
     * @return
     */
    public ResultSet getResultSet(Statement stmt, String sql) {
        try {
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 获取结果集对象（ResultSet）
     * 
     * @param sql
     * @return
     */
    public ResultSet getResultSet(String sql) {
        try {
            pstmt = getPreparedStatement(sql);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 获取结果集对象（ResultSet）
     * 
     * @param sql
     * @param param
     * @return
     */
    public <T> ResultSet getResultSet(String sql, T[] param) {
        try {
            pstmt = getPreparedStatement(sql, param);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 获取结果集对象（ResultSet）
     * 
     * @param sql
     * @param param
     * @return
     */
    public <T> ResultSet getResultSet(String sql, T param) {
        try {
            pstmt = getPreparedStatement(sql, param);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * ResultSetMetaData结果集处理
     * 
     * @param reSetMetaData
     * @param columnValue
     * @param index
     * @return
     */
    private ResultColumnVO getResultColumn(ResultSetMetaData rSetMetaData, Object columnValue, int index) {
        ResultColumnVO resultColumn = null;
        try {
            if (rSetMetaData != null) {
                resultColumn = new ResultColumnVO();
                resultColumn.setCatalogName(rSetMetaData.getCatalogName(index));
                resultColumn.setColumnClassName(rSetMetaData.getColumnClassName(index));
                resultColumn.setColumnCount(rSetMetaData.getColumnCount());
                resultColumn.setColumnName(rSetMetaData.getColumnName(index));
                resultColumn.setColumnSize(rSetMetaData.getColumnDisplaySize(index));
                resultColumn.setColumnType(rSetMetaData.getColumnType(index));
                resultColumn.setColumnTypeName(rSetMetaData.getColumnTypeName(index));
                resultColumn.setColumnValue(columnValue);
                resultColumn.setSchemaName(rSetMetaData.getSchemaName(index));
                resultColumn.setTableName(rSetMetaData.getTableName(index));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultColumn;
    }

    /**
     * 根据SQL查询数据
     * 
     * @param <T>
     * @param sql
     * @return
     */
    public <T> List<ResultRowVO> executeQuery(String sql) throws Exception {
        List<ResultRowVO> list = null;
        try {
            int index = 0;
            this.sql = sql;
            Object[] obj = null;
            rs = getResultSet(sql);
            list = new ArrayList<ResultRowVO>();
            while (rs.next()) {
                rSetMetaData = rs.getMetaData();
                int columnCount = rSetMetaData.getColumnCount();
                obj = new Object[columnCount];
                ResultRowVO resultRow = new ResultRowVO();
                List<ResultColumnVO> columnList = new ArrayList<ResultColumnVO>();
                for (int i = 1; i <= columnCount; i++) {
                    obj[i - 1] = getValue(rs, rSetMetaData, i);
                    ResultColumnVO resultColumn = getResultColumn(rSetMetaData, obj[i - 1], i);
                    columnList.add(resultColumn);
                }
                resultRow.setId(++index);
                resultRow.setColumnArray(obj);
                resultRow.setColumnList(columnList);
                list.add(resultRow);
            }
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return list;
    }

    /**
     * 获取相应列的值
     * 
     * @param rs
     * @param rSetMetaData
     * @param index
     * @return
     */
    private Object getValue(ResultSet rs, ResultSetMetaData rSetMetaData, int index) {
        Object obj = "";
        try {
            String type = rSetMetaData.getColumnTypeName(index);
            if (type.toLowerCase().indexOf("clob") > -1) {
                if (rs.getClob(index) != null) {
                    Reader reader = rs.getClob(index).getCharacterStream();
                    char[] c = new char[1024];
                    int len = 0;
                    StringBuffer buff = new StringBuffer();
                    if (reader != null) {
                        while ((len = reader.read(c)) > 0) {
                            buff.append(c, 0, len);
                        }
                        obj = buff.toString();
                        reader.close();
                    }
                }
            } else if (type.toLowerCase().indexOf("blob") > -1) {
                if (rs.getClob(index) != null) {
                    InputStream reader = rs.getBlob(index).getBinaryStream();
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    StringBuffer buff = new StringBuffer();
                    if (reader != null) {
                        while ((len = reader.read(bytes)) > 0) {
                            buff.append(new String(bytes, 0, len));
                        }
                        obj = buff.toString();
                        reader.close();
                    }
                }
            } else {
                obj = rs.getObject(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 根据SQL查询数据
     * 
     * @param <T>
     * @param sql
     * @return
     */
    public <T> List<ResultRowVO> executeQuery(String sql, T[] param) throws Exception {
        List<ResultRowVO> list = null;
        try {
            int index = 0;
            this.sql = sql;
            Object[] obj = null;
            rs = getResultSet(sql, param);
            list = new ArrayList<ResultRowVO>();
            while (rs.next()) {
                rSetMetaData = rs.getMetaData();
                int columnCount = rSetMetaData.getColumnCount();
                obj = new Object[columnCount];
                ResultRowVO resultRow = new ResultRowVO();
                List<ResultColumnVO> columnList = new ArrayList<ResultColumnVO>();
                for (int i = 1; i <= columnCount; i++) {
                    obj[i - 1] = getValue(rs, rSetMetaData, i);
                    ResultColumnVO resultColumn = getResultColumn(rSetMetaData, obj[i - 1], i);
                    columnList.add(resultColumn);
                }
                resultRow.setId(++index);
                resultRow.setColumnArray(obj);
                resultRow.setColumnList(columnList);
                list.add(resultRow);
            }
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return list;
    }

    /**
     * 根据SQL查询数据
     * 
     * @param <T>
     * @param sql
     * @return
     */
    public <T> List<ResultRowVO> executeQuery(String sql, T param) throws Exception {
        List<ResultRowVO> list = null;
        try {
            int index = 0;
            this.sql = sql;
            Object[] obj = null;
            rs = getResultSet(sql, param);
            list = new ArrayList<ResultRowVO>();
            while (rs.next()) {
                rSetMetaData = rs.getMetaData();
                int columnCount = rSetMetaData.getColumnCount();
                obj = new Object[columnCount];
                ResultRowVO resultRow = new ResultRowVO();
                List<ResultColumnVO> columnList = new ArrayList<ResultColumnVO>();
                for (int i = 1; i <= columnCount; i++) {
                    obj[i - 1] = getValue(rs, rSetMetaData, i);
                    ResultColumnVO resultColumn = getResultColumn(rSetMetaData, obj[i - 1], i);
                    columnList.add(resultColumn);
                }
                resultRow.setId(++index);
                resultRow.setColumnArray(obj);
                resultRow.setColumnList(columnList);
                list.add(resultRow);
            }
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return list;
    }

    /**
     * 执行增，删，改操作
     * 
     * @param pstmt
     * @return
     */
    public boolean execute(PreparedStatement pstmt) {
        boolean flag = false;
        try {
            flag = pstmt.executeUpdate() > 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    /**
     * 执行增，删，改操作
     * 
     * @param stmt
     * @param sql
     * @return
     */
    public boolean execute(Statement stmt, String sql) {
        boolean flag = false;
        try {
            this.sql = sql;
            flag = stmt.executeUpdate(sql) > 1;
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    /**
     * 执行增，删，改操作
     * 
     * @param sql
     * @param param
     * @return
     */
    public <T> boolean execute(String sql, T[] param) throws Exception {
        boolean flag = false;
        try {
            this.sql = sql;
            pstmt = getPreparedStatement(sql, param);
            flag = pstmt.executeUpdate() > 1;
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return flag;
    }

    /**
     * 执行增，删，改操作
     * 
     * @param sql
     * @param param
     * @return
     */
    public <T> boolean execute(String sql, T param) throws Exception {
        boolean flag = false;
        try {
            this.sql = sql;
            pstmt = getPreparedStatement(sql, param);
            flag = pstmt.executeUpdate() > 1;
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return flag;
    }

    /**
     * 执行增，删，改操作
     * 
     * @param sql
     * @return
     */
    public <T> boolean execute(String sql) throws Exception {
        boolean flag = false;
        try {
            this.sql = sql;
            pstmt = getPreparedStatement(sql);
            flag = pstmt.executeUpdate() > 1;
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return flag;
    }

    /**
     * 执行增，改操作,返回主键值
     * 
     * @param sql
     * @return
     */
    public <T> Object executeMethod(String sql, T[] param) throws Exception {
        Object obj = null;
        try {
            this.sql = sql;
            pstmt = getConnection().prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            if (CommUtil.isNotEmpty(param)) {
                for (int i = 1; i <= param.length; i++) {
                    pstmt.setObject(i, param[i]);
                }
            }
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                obj = rs.getObject(1);
            }
            if (showSql) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            throw new Exception("SQL:" + sql, e);
        } finally {
            close();
        }
        return obj;
    }

    /**
     * 关闭数据库连接
     * 
     * @param conn
     */
    public void close(Connection conn) {
        close(conn, null, null, null, null);
    }

    /**
     * 关闭结果集
     * 
     * @param conn
     */
    public void close(ResultSet rs) {
        close(null, null, null, null, rs);
    }

    /**
     * 关闭数据库连接
     * 
     * @param conn
     * @param stmt
     */
    public void close(Connection conn, Statement stmt) {
        close(conn, stmt, null, null, null);
    }

    /**
     * 关闭数据库连接
     */
    public void close() {
        close(conn, stmt, pstmt, callStmt, rs);
    }

    /**
     * 关闭数据库连接
     * 
     * @param conn
     * @param stmt
     * @param rs
     */
    public void close(Connection conn, Statement stmt, PreparedStatement pstmt, CallableStatement callStmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (callStmt != null) {
                callStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs = null;
            stmt = null;
            pstmt = null;
            callStmt = null;
            conn = null;
        }
    }

    /**
     * 对象销毁
     * 
     * @throws
     */
    public void destory() {
        close();
        showSql = false;
        sql = null;
        driverVO = null;
        dataSource = null;
        rs = null;
        stmt = null;
        pstmt = null;
        callStmt = null;
        conn = null;
        rSetMetaData = null;
        dataMetaData = null;
        objectDao = null;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public DriverVO getDriverVO() {
        return driverVO;
    }

    public void setDriverVO(DriverVO driverVO) {
        this.driverVO = driverVO;
    }
}
