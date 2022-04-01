package com.computer.community_laundry.support.config;

import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description: 默认数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.zzbj.community_laundry.mapper", sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class DefaultDataSourceConfig {
    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "defaultDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource setDataSource() {
        return new HikariDataSource();
    }

    /**
     * 配置sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "defaultSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置实体类映射
        bean.setTypeAliasesPackage("com.zzbj.community_laundry.support.entity");
        //指定mapper文件所在位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //支持驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        //控制台打印SQL日志
        configuration.setLogImpl(StdOutImpl.class);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    /**
     * 配置mybatis的分页插件pageHelper
     * @return
     */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        //配置mysql数据库的方言
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean(name = "branchJdbcTemplate")
    public JdbcTemplate setJdbcTemplate(@Qualifier("defaultDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "defaultTransactionManager")
    public DataSourceTransactionManager defaultTransactionManager(@Qualifier("defaultDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
