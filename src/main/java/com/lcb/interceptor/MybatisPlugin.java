package com.lcb.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author changbao.l Date: 2020-04-15 Time: 9:26 上午
 * @version $
 * <p>
 * 自定义分页插件
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class MybatisPlugin implements Interceptor {

    private String databaseType = "mysql";


    /**
     * 自定义拦截器需要实现的逻辑
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 1.执行count语句

        // 2.重写SQL
        return invocation.proceed();
    }

    /**
     * 此处需要你返回一个动态代理后的对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 可以通过配置文件控制自定义插件的相关行为
     */
    @Override
    public void setProperties(Properties properties) {
        String databaseType = properties.getProperty("databaseType");
        if (databaseType != null) {
            this.databaseType = databaseType;
        }
    }
}
