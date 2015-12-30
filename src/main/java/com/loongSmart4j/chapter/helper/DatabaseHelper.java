package com.loongSmart4j.chapter.helper;

import com.loongSmart4j.chapter.model.Customer;
import com.loongSmart4j.chapter.util.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Loong on 2015/12/31.
 * 数据库操作助手类
 */
public final class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String PASSWORD;
    private static final String UESRNAME;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = PropsUtil.getString(conf, "jdbc.driver");
        URL = PropsUtil.getString(conf, "jdbc.url");
        UESRNAME = PropsUtil.getString(conf, "jdbc.username");
        PASSWORD = PropsUtil.getString(conf, "jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    /**
     * 获取数据库链接
     */
    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, UESRNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /**
     * 关闭数据库链接
     */
    public static void closeConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    /**
     * 查询实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = null;
        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询实体列表
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity = null;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entity;
    }

    /**
     * 执行查询语句
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> mapList;
        try {
            Connection connection = getConnection();
            mapList = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("execute Query failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return mapList;
    }

    /**
     * 执行更新语句（包括update、insert、delete）
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, params);
        } catch (SQLException e) {
            LOGGER.error("execute Update failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return rows;
    }
}
