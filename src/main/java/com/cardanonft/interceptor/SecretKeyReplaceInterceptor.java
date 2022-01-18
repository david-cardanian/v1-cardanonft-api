package com.cardanonft.interceptor;

import com.cardanonft.config.SecretsManagerConfig;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.sql.Connection;
import java.util.Properties;


/**
 */
@Intercepts({
        @Signature(type=StatementHandler.class, method="prepare", args={Connection.class, Integer.class})
})
public class SecretKeyReplaceInterceptor implements Interceptor {

    private String dbSecretKey;
    private String dbLang;

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    public SecretKeyReplaceInterceptor(String dbSecretKey, String dbLang) {
        this.dbSecretKey = String.format("'%s'", dbSecretKey);
        this.dbLang = String.format("'%s'", dbLang);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

//        originalSql = originalSql.replace("@key", String.format("'%s'", SecretsManagerConfig.getDbSecretKey())).replace("@lang", dbLang);

        metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
