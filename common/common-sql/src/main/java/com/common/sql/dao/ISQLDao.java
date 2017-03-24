package com.common.sql.dao;

import java.util.List;
import java.util.Map;

import com.common.sql.model.ColumnVO;
import com.common.sql.model.ForeignKeyVO;
import com.common.sql.model.PrimaryKeyVO;
import com.common.sql.model.ResultRowVO;
import com.common.sql.model.TableVO;

/**
 * SQL常用的操作方法
 * 
 * @author 枫之叶
 * 
 */
public interface ISQLDao {
    /**
     * 根据SQL增加数据
     * 
     * @param
     * @param sql
     * @return
     */
    public boolean addObject(String sql) throws Exception;

    /**
     * 根据SQL删除数据
     * 
     * @param
     * @param sql
     * @return
     */
    public boolean deleteObject(String sql) throws Exception;

    /**
     * 根据SQL修改数据
     * 
     * @param
     * @param sql
     * @return
     */
    public boolean updateObject(String sql) throws Exception;

    /**
     * 根据SQL查询数据
     * 
     * @param
     * @param sql
     * @return
     */
    public List<ResultRowVO> queryObject(String sql) throws Exception;

    /**
     * 查询所有
     * 
     * @param
     * @param tableName
     * @return
     */
    public List<ResultRowVO> queryAllObject(String tableName) throws Exception;

    /**
     * 查询所有指定的列
     * 
     * @param
     * @param tableName
     * @param columnName
     * @return
     */
    public List<ResultRowVO> queryObject(String tableName, String[] columnName)
            throws Exception;

    /**
     * 根据指定的列插入数据
     * 
     * @param
     * @param tableName
     * @param param
     * @return
     */
    public <T> boolean addObject(String tableName, Map<String, T> param)
            throws Exception;

    /**
     * 根据指定的条件删除数据
     * 
     * @param
     * @param tableName
     * @param where
     * @return
     */
    public <T> boolean deleteObject(String tableName, Map<String, T> where)
            throws Exception;

    /**
     * 根据指定的条件修改数据
     * 
     * @param
     * @param tableName
     * @param param
     * @return
     */
    public <T> boolean updateObject(String tableName, Map<String, T> param)
            throws Exception;

    /**
     * 根据指定的条件查询数据
     * 
     * @param
     * @param tableName
     * @param param
     * @return
     */
    public <T> List<ResultRowVO> queryObject(String tableName,
            Map<String, T> where) throws Exception;

    /**
     * 增加数据，插入所有的列
     * 
     * @param
     * @param tableName
     * @param param
     * @return
     */
    public <T> boolean addObject(String tableName, T[] param) throws Exception;

    /**
     * 增加数据，插入所有的列
     * 
     * @param
     * @param tableName
     * @param param
     * @return
     */
    public <T> ResultRowVO addObjectReturn(String tableName, T[] param)
            throws Exception;

    /**
     * 根据条件修改
     * 
     * @param
     * @param tableName
     * @param param
     * @param where
     * @return
     */
    public <T> ResultRowVO updateObjectReturn(String tableName,
            Map<String, T> param, Map<String, T> where) throws Exception;

    /**
     * 根据条件修改
     * 
     * @param
     * @param tableName
     * @param param
     * @param where
     * @return
     */
    public <T> boolean updateObject(String tableName, Map<String, T> param,
            Map<String, T> where) throws Exception;

    /**
     * 根据条件查询指定列的值
     * 
     * @param
     * @param tableName
     * @param columnName
     * @param where
     * @return
     */
    public <T> List<ResultRowVO> queryObject(String tableName,
            String[] columnName, Map<String, T> where) throws Exception;

    /**
     * 高级查询，支付like,between-and
     * 
     * @param
     * @param tableName
     * @param columnName
     * @param param
     * @param like
     * @param between
     * @param and
     * @return
     */
    public <T> List<ResultRowVO> queryObject(String tableName,
            String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> between,
            Map<String, Object> and) throws Exception;

    /**
     * 高级查询，支付like,gt,lt,in
     * 
     * @param
     * @param tableName
     * @param columnName
     * @param param
     * @param like
     * @param gt
     * @param lt
     * @param in
     * @return
     */
    public <T> List<ResultRowVO> queryObject(String tableName,
            String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> gt,
            Map<String, Object> lt, Map<String, Object[]> in) throws Exception;

    /**
     * 根据主键查询数据
     * 
     * @param
     * @param pk
     * @param tableName
     * @return
     */
    public <T> T queryObject(Object pk, String tableName) throws Exception;

    /**
     * 根据key-value查询数据
     * 
     * @param
     * @param key
     * @param value
     * @param tableName
     * @return
     */
    public <T> List<ResultRowVO> queryObject(String key, T value,
            String tableName) throws Exception;

    /**
     * 根据key-value查询指定的列值
     * 
     * @param
     * @param key
     * @param value
     * @param columnName
     * @param tableName
     * @return
     */
    public <T> List<ResultRowVO> queryObject(String key, T value,
            String[] columnName, String tableName) throws Exception;

    /**
     * 根据主键删除
     * 
     * @param
     * @param pk
     * @param tableName
     * @return
     */
    public <T> boolean deleteObject(T pk, String tableName) throws Exception;

    /**
     * 根据key-value删除数据
     * 
     * @param
     * @param key
     * @param value
     * @param tableName
     * @return
     */
    public <T> boolean deleteObject(String key, T value, String tableName)
            throws Exception;

    /**
     * 根据主键修改columnName的值为columnValue
     * 
     * @param
     * @param pk
     * @param columnName
     * @param columnValue
     * @param tableName
     * @return
     */
    public <T> boolean updateObject(T pk, String columnName, T columnValue,
            String tableName) throws Exception;

    /**
     * 根据key-value修改columnName的值为columnValue
     * 
     * @param
     * @param key
     * @param value
     * @param columnName
     * @param columnValue
     * @param tableName
     * @return
     */
    public <T> boolean updateObject(String key, T value, String columnName,
            T columnValue, String tableName) throws Exception;

    /**
     * 备用方法，执行增，删，改操作
     * 
     * @param
     * @param sql
     * @return
     */
    public boolean execute(String sql) throws Exception;

    /**
     * 备用方法，执行增，删，改操作
     * 
     * @param
     * @param sql
     * @param param
     * @return
     */
    public <T> boolean execute(String sql, T param) throws Exception;

    /**
     * 备用方法，执行增，删，改操作
     * 
     * @param
     * @param sql
     * @param param
     * @return
     */
    public <T> boolean execute(String sql, T[] param) throws Exception;

    /**
     * 备用方法，根据SQL查询数据
     * 
     * @param
     * @param sql
     * @return
     */
    public List<ResultRowVO> executeQuery(String sql) throws Exception;

    /**
     * 备用方法，根据SQL查询数据
     * 
     * @param
     * @param sql
     * @param param
     * @return
     */
    public <T> List<ResultRowVO> executeQuery(String sql, T param)
            throws Exception;

    /**
     * 备用方法，根据SQL查询数据
     * 
     * @param
     * @param sql
     * @param param
     * @return
     */
    public <T> List<ResultRowVO> executeQuery(String sql, T[] param)
            throws Exception;

    /**
     * 批量更新数据
     * 
     * @param
     * @param sql
     * @return
     */
    public boolean executeBatch(String[] sql) throws Exception;

    /**
     * 根据SQL查询函数
     * 
     * @param
     * @param tableName
     * @return
     */
    public <T> T queryFounction(String sql) throws Exception;

    /**
     * 根据条件查询函数
     * 
     * @param
     * @param tableName
     * @return
     */
    public <T> T queryFounction(String tableName, Map<String, Object> param)
            throws Exception;

    /**
     * 根据SQL查询分页
     * 
     * @param
     * @param sql
     * @return
     */
    public List<ResultRowVO> queryPage(String sql, int currPage, int pageSize)
            throws Exception;

    /**
     * 根据SQL查询分页，支持Where条件
     * 
     * @param tableName
     * @param where
     * @param currPage
     * @param pageSize
     * @return
     */
    public List<ResultRowVO> queryPage(String tableName,
            Map<String, Object> where, int currPage, int pageSize)
            throws Exception;

    /**
     * 根据SQL查询分页，支持查询指定列
     * 
     * @param tableName
     * @param columnName
     * @param currPage
     * @param pageSize
     * @return
     */
    public List<ResultRowVO> queryPage(String tableName, String[] columnName,
            int currPage, int pageSize) throws Exception;

    /**
     * 根据SQL查询分页，支持where，并查询指定列
     * 
     * @param tableName
     * @param columnName
     * @param where
     * @param currPage
     * @param pageSize
     * @return
     */
    public List<ResultRowVO> queryPage(String tableName, String[] columnName,
            Map<String, Object> where, int currPage, int pageSize)
            throws Exception;

    /**
     * 根据SQL高级查询并分页
     * 
     * @param tableName
     * @param columnName
     * @param param
     * @param like
     * @param between
     * @param and
     * @param currPage
     * @param pageSize
     * @return
     */
    public <T> List<ResultRowVO> queryPage(String tableName,
            String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> between,
            Map<String, Object> and, int currPage, int pageSize)
            throws Exception;

    /**
     * 根据SQL高级查询并分页
     * 
     * @param tableName
     * @param columnName
     * @param param
     * @param like
     * @param gt
     * @param lt
     * @param in
     * @param currPage
     * @param pageSize
     * @return
     */
    public <T> List<ResultRowVO> queryPage(String tableName,
            String[] columnName, Map<String, Object> param,
            Map<String, Object> like, Map<String, Object> gt,
            Map<String, Object> lt, Map<String, Object[]> in, int currPage,
            int pageSize) throws Exception;

    /**
     * 查询SQL的总数
     * 
     * @param
     * @param sql
     * @return
     */
    public int queryCount(String sql) throws Exception;

    /**
     * 查询所有的列名称
     * 
     * @param
     * @param tableName
     * @return
     */
    public List<ColumnVO> queryColumn(String tableName) throws Exception;

    /**
     * 查询主键名称
     * 
     * @param
     * @param tableName
     * @return
     */
    public PrimaryKeyVO queryPrimaryKey(String tableName) throws Exception;

    /**
     * 查询外键名称
     * 
     * @param
     * @param tableName
     * @return
     */
    public List<ForeignKeyVO> queryForeignKey(String tableName)
            throws Exception;

    /**
     * 查询表的信息
     * 
     * @param
     * @param tableName
     * @return
     */
    public TableVO queryTable(String tableName) throws Exception;

    /**
     * 数据类型转化,将行数据转换为相应的对象
     * 
     * @param
     * @param source
     * @param obj
     * @return
     */
    public <T> T parseData(ResultRowVO resultRow, Class<T> obj)
            throws Exception;

    /**
     * 数据类型转化,将行数据转换为相应的对象
     * 
     * @param
     * @param source
     * @param obj
     * @return
     */
    public <T> List<T> parseData(List<ResultRowVO> rowList, Class<T> obj)
            throws Exception;

    /**
     * 分页处理类
     * 
     * @param sql
     * @param tableName
     * @param currPage
     * @param pageSize
     * @return
     */
    public <T> List<ResultRowVO> executePage(String sql, String tableName,
            T[] param, int currPage, int pageSize) throws Exception;
}
