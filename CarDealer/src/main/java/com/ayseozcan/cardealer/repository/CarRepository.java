
package com.ayseozcan.cardealer.repository;

import com.ayseozcan.cardealer.entity.Car;
import com.ayseozcan.cardealer.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarRepository implements ICrud<Car> {

    @Override
    public void save(Car t) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            String sql = "insert into car (brand, car_model, model_year, dealership_id) values(?,?,?,?)";

            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getBrand());
            preparedStatement.setString(2, t.getCarModel());
            preparedStatement.setString(3, t.getModelYear());
            preparedStatement.setLong(4, t.getDelaerShipId());

            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println(t.getBrand() + "Markali araba kaydedildi");

        } catch (SQLException ex) {
            Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Car t, long id) {
        String sql = "update car set brand = ?, car_model =?, model_year =?, dealership_id=? where id =?";
        try {

            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, t.getBrand());
            preparedStatement.setString(2, t.getCarModel());
            preparedStatement.setString(3, t.getModelYear());
            preparedStatement.setLong(4, t.getDelaerShipId());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from car where id =?";
        try {

            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from car order by id";
        try {
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Long id = resultSet.getLong("id");
                String brand = resultSet.getString(2);
                String carModel = resultSet.getString(3);
                String modelYear = resultSet.getString(4);
                Long dealershipId = resultSet.getLong(5);
                
                Car car = new Car(id, brand, carModel, modelYear, dealershipId);
                cars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cars;
    }

    @Override
    public void saveAll(List<Car> t) {
        //PreparedStatement preparedStatement = null;
        //Connection connection = null;
        String sql = "insert into car (brand, car_model, model_year, dealership_id,id) values(?,?,?,?,?)";
        t.forEach(car -> {
            try {
                //connection.setAutoCommit(false);
                PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, car.getBrand());
                preparedStatement.setString(2, car.getCarModel());
                preparedStatement.setString(3, car.getModelYear());
                preparedStatement.setLong(4, car.getDelaerShipId());
                preparedStatement.setLong(5, car.getId());

                preparedStatement.executeUpdate();
                //connection.commit();

            } catch (SQLException ex) {
                Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public boolean databaseControl() {
        boolean control = false;
        String sql = "select * from car";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            control = resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return control;
    }
}
