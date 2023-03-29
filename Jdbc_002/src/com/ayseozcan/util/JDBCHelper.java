package com.ayseozcan.util;

import java.sql.*;

public class JDBCHelper {
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(JDBConstant.URL,JDBConstant.USERNAME,JDBConstant.PASSWORD);
        return connection;
    }
    public static void closeConnection(Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
            System.out.println("connection closed");
        }
    }
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if(preparedStatement != null){
            preparedStatement.close();
            System.out.println("preparedStatement closed");
        }
    }
    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if(resultSet != null){
            resultSet.close();
            System.out.println("resultSet closed");
        }
    }

}
