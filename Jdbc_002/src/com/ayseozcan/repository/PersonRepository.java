package com.ayseozcan.repository;

import com.ayseozcan.entity.Person;
import com.ayseozcan.util.JDBCHelper;

import java.sql.*;

public class PersonRepository implements IPersonRepository{
    @Override
    public void insertPerson(Person person){

        String sql = "insert into persons (first_name, last_name, joined_date, email) values(?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setDate(3 ,new Date(person.getJoinedDate().getTime()));
            preparedStatement.setString(4, person.getEmail());

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void displayPersons(ResultSet rs) throws SQLException {
        while(rs.next()){
            System.out.println(rs.getInt(1)+ " "
                    + rs.getString(2)+ " "
                    +  rs.getString(3)+ " "
                    +  rs.getDate(4)+ " "
                    +  rs.getString(5));
        }
    }
    @Override
    public void getAllPerson(){

        String sql = "select * from persons ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            displayPersons(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
                JDBCHelper.closeResultSet(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

