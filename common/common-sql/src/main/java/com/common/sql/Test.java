package com.common.sql;

import java.util.List;

import com.common.sql.factory.DBFactory;
import com.common.sql.model.DriverVO;
import com.common.sql.model.TableVO;
import com.common.sql.proxy.DataSource;

public class Test {
    
    public static void main(String[] args) {
//        DataSource dataSource = DBFactory.dataSourceInstance();
//        dataSource.destory();
        DataSource dataSource = DBFactory.dataSourceInstance();
       // DriverVO driverVO = dataSource.getDriverVO();
       // driverVO.setJdbcUrl("jdbc:mysql://localhost:3306/pms");
       // dataSource.setDriverVO(driverVO);
        List<TableVO> tableVOs=dataSource.getTables();
        for (TableVO tableVO : tableVOs) {
            System.out.println(tableVO);
        }
    }
    
}
