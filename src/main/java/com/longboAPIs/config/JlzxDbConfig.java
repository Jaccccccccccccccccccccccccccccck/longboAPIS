package com.longboAPIs.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.longboAPIs.dao.dbContent"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class JlzxDbConfig {

    @Autowired
    @Qualifier("JlzxDataSource")
    private DataSource ds1;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {


        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds1); // jlzx

        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("params","pageNum=pageNumKey;pageSize=pageSizeKey;");
        interceptor.setProperties(properties);
        factoryBean.setPlugins(new Interceptor[] {interceptor});

        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
        return template;
    }
}
