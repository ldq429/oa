package com.imooc.oa.datesouce;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource)this.dataSource).init();
        } catch (SQLException throwable) {
            throw new RuntimeException(throwable);
        }
        return this.dataSource;
    }

    public DruidDataSourceFactory(){
        this.dataSource = new DruidDataSource();
    }
}
