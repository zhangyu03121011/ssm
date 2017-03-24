package com.common.tool.doc;

import java.util.List;

import com.common.sql.factory.DBFactory;
import com.common.sql.model.DriverVO;
import com.common.sql.model.TableVO;
import com.common.sql.proxy.DataSource;

public class DbTools {

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
