package com.ayseozcan.repository;

import com.ayseozcan.entity.Person;
import com.ayseozcan.util.JDBCHelper;

import java.sql.*;

public class PersonRepository implements IPersonRepository{
    @Override
    public void insertPerson(Person person){

        String sql = "insert into persons (first_name, last_name, email) values(?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());

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

    @Override
    public void deleteAllRecords() {
        String sql = "delete from persons";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int effectedRow = preparedStatement.executeUpdate();
            System.out.println("effected row/s: " + effectedRow);
            if(effectedRow>0){
                System.out.println("all records deleted");
            }
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

    @Override
    public void updateEmail(String email, int id) {
        String sql = "update persons set email = ? where id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            int effectedRow = preparedStatement.executeUpdate();

            if(effectedRow>0){
                connection.commit();
                System.out.println("email updated");
            }
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

    @Override
    public void getPersonById(int id) {
        String sql = "select * from persons where id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                displayPersons(rs);
            }else{
                System.out.println("invalid id");
            }
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

    @Override
    public void deletePerson(int id) {
        String sql = "delete from persons where id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int effectedRow = preparedStatement.executeUpdate();

            if(effectedRow>0){
                System.out.println("person deleted");
            }
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

}

