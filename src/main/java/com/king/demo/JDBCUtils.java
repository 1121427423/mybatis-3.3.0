package com.king.demo;

import com.king.pojo.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC原生连接数据库
 * @author hspcadmin
 */
public class JDBCUtils {

    private static final String JDBC_URL = "jdbc:mysql://123.60.148.210:3306/mybatis";

    private static final String JDBC_USERNAME = "root";

    private static final String JDBC_PASSWORD = "123456";

    private static Connection connection = null;

    public static Connection getConnection() {
        /** 第一步： 获取连接 */
        try {
            if(connection == null) {
                connection = DriverManager
                        .getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


    public static void getResult(String sql) {
        /** 第二步： 预编译SQL */
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            statement = getConnection()
                    .prepareStatement(sql);
            /** 第三步： 执行查询 */
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /** 第四步： 读取结果 */
        readResultSet(resultSet);

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<?> readResultSet(ResultSet resultSet) {
        List<Role> roles = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Role role = new Role(resultSet.getLong("id"),resultSet.getString("role_name"),resultSet.getString("note"));
                System.out.println(role);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        String sql = "select * from role";
        getResult(sql);
    }

}
