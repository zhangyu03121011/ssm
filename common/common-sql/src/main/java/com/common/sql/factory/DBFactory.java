package com.common.sql.factory;

import java.sql.Connection;

import com.common.sql.dao.ISQLDao;
import com.common.sql.proxy.DataSource;

/**
 * 数据库工厂
 * 
 * @author 枫之叶
 * 
 */
public abstract class DBFactory {
    /**
     * 获取数据源对象
     * 
     * @return
     */
    public static DataSource dataSourceInstance() {
        return DataSource.newInstance();
    }

    /**
     * 获取数据源对象,允许外部数据源
     * 
     * @return
     */
    public static DataSource dataSourceInstance(Connection conn) {
        return DataSource.newInstance(conn);
    }

    /**
     * 获取SQL_Dao对象
     * 
     * @return
     */
    public static ISQLDao sqlDaoInstance() {
        return DataSource.newInstance().sqlDaoInstance();
    }

    /**
     * 获取SQL_Dao对象,允许外部数据源
     * 
     * @return
     */
    public static ISQLDao sqlDaoInstance(Connection conn) {
        return DataSource.newInstance(conn).sqlDaoInstance();
    }

}
