package com.common.sql;

/**
 * 常量
 * 
 * @author 枫之叶
 * 
 */
public class Constant {
    /**
     * MSSQL
     */
    public static final String MSSQL = "mssql";

    /**
     * Oracle
     */
    public static final String ORACLE = "oracle";

    /**
     * MySQL
     */
    public static final String MYSQL = "mysql";

    /**
     * 表
     */
    public static final String TABLE = "table";

    /**
     * 增加语句
     */
    public static final String INSERT = "insert into table (#) values (?)";

    /**
     * 增加语句
     */
    public static final String INSERT_ALL = "insert into table values (?)";

    /**
     * 修改语句
     */
    public static final String UPDATE = "update table set ? where 1=1 ";

    /**
     * 查询语句
     */
    public static final String SELECT = "select ? from table where 1=1 ";

    /**
     * 删除语句
     */
    public static final String DELETE = "delete from table where 1=1 ";

    /**
     * MySQL分页
     */
    public static final String MYSQL_PAGE = " limit ?,?";

    /**
     * Oracle分页
     */
    public static final String ORACLE_PAGE = "select * from ( select row_.*, rownum rownum_ from ( # ) row_ where rownum <= ?) where rownum_ > ?";

    /**
     * MS SQL分页
     */
    public static final String MSSQL_PAGE = "SELECT * FROM ( SELECT Top 5 * FROM ( SELECT top 9 * FROM ( # ) as t order by t.## desc) t1 order by t1.## asc) t2";

}
