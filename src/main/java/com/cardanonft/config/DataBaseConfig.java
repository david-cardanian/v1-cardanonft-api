package com.cardanonft.config;

import com.cardanonft.interceptor.SecretKeyReplaceInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * db 접근 세팅
 */

@Configuration
@EnableAutoConfiguration
public class DataBaseConfig {

    @Value("${db.secret.key}")
    private String dbSecretKey;

    @Value("${db.lang}")
    private String dbLang;

    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer(){
        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(HikariDataSource dataSource) throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cardanonft");
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/daoMapper/*.xml"));

        Interceptor[] interceptors = new Interceptor[1];
        interceptors[0] = new SecretKeyReplaceInterceptor(dbSecretKey, dbLang);
        sqlSessionFactoryBean.setPlugins(interceptors);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
