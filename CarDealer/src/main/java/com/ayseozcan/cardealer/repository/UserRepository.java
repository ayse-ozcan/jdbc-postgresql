
package com.ayseozcan.cardealer.repository;

import com.ayseozcan.cardealer.entity.User;
import com.ayseozcan.cardealer.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserRepository implements ICrud<User> {

    @Override
    public void save(User t) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String sql = "insert into users(name,email,phone,password,security_question,answer,address,is_active) values(?,?,?,?,?,?,?,?)";
        try {
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getEmail());
            preparedStatement.setString(3, t.getPhone());
            preparedStatement.setString(4, t.getPassword());
            preparedStatement.setString(5, t.getSecurityQuestion());
            preparedStatement.setString(6, t.getAnswer());
            preparedStatement.setString(7, t.getAddress());
            preparedStatement.setBoolean(8, true);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(User t, long id) {

    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void saveAll(List<User> t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public User login(String email, String password) {
        User user = null;
        String sql = "select * from users where email=? and password=?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setIsActive(resultSet.getBoolean("is_active"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUserInformation(String email) {
        User user = null;
        String sql = "select * from users where email=?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setSecurityQuestion(resultSet.getString("security_question"));
                user.setAnswer(resultSet.getString("answer"));
                user.setId(resultSet.getLong("id"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void changePassword(String password, Long id) {
        String sql = "update users set password = ? where id =?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //java email regex format
    public boolean validateField(String name, String email, String password, String phone, String question, String answer) {
        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!name.equals("") && !email.equals("") && email.matches(emailPattern) 
                && !password.equals("") && !phone.equals("") 
                && !question.equals("") && !answer.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}
