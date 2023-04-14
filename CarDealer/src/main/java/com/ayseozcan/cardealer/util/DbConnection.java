
package com.ayseozcan.cardealer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private Connection connection;
    private static DbConnection instance;
    
    public DbConnection(){
        createConnection();
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    public static DbConnection getInstance(){
        if(instance == null){
            instance = new DbConnection();
        }else{
            try{
                if(instance.getConnection().isClosed()){
                    instance= new DbConnection();
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return instance;
    }

    public Connection createConnection() {

        try {
            connection = DriverManager.getConnection(Constant.url, Constant.username, Constant.password);
            System.out.println("Baglanti basarili");
        } catch (SQLException ex) {
            System.out.println("Baglanti hatasi");
        }
        return connection;
    }
}
