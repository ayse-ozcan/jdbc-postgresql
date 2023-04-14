
package com.ayseozcan.cardealer.repository;
import com.ayseozcan.cardealer.entity.Dealership;
import com.ayseozcan.cardealer.util.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DealershipRepository implements ICrud<Dealership>{

    @Override
    public void save(Dealership t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Dealership t, long id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Dealership> findAll() {
        List<Dealership> dealerships = new ArrayList<>();
        String sql = "select * from dealership order by id";
        try {
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String country = resultSet.getString(3);
                String city = resultSet.getString(4);
                
                Dealership dealership = new Dealership(id, name, country, city);
                dealerships.add(dealership);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dealerships;
    }

    @Override
    public void saveAll(List<Dealership> t) {
        //PreparedStatement preparedStatement = null;
        //Connection connection = null;
        String sql = "insert into dealership (name, country, city, id) values(?,?,?,?)";
        t.forEach(dealership -> {
            try {
                PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, dealership.getName());
                preparedStatement.setString(2, dealership.getCountry());
                preparedStatement.setString(3, dealership.getCity());
                preparedStatement.setLong(4, dealership.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CarRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public boolean databaseControl() {
        boolean control = false;
        String sql = "select * from dealership";
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
