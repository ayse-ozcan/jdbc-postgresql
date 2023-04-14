
package com.ayseozcan.cardealer.util;

import com.ayseozcan.cardealer.entity.Car;
import com.ayseozcan.cardealer.entity.Dealership;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {
    
    public static List<String> readFile(String path){
        List<String> fileText = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String read = "";
            while((read=reader.readLine()) != null){
                fileText.add(read);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya hatasi");
        } catch (IOException ex) {
            System.err.println("IO hatasi");
        }
        return fileText;
    }
    
    public static List<Car> getCarList(List<String> list){
        
        List<Car> cars = new ArrayList<>();
        for (String string : list) {
           cars.add(convertToCar(string.split(",")));
        }
        return cars;
    }
    
    public static Car convertToCar(String[] array){
        Car car = new Car(Long.parseLong(array[0]),array[1],array[2],array[3],Long.parseLong(array[4]));
        return car;
    }
    public static List<Dealership> getDealershipList(List<String> list){
        
        List<Dealership> dealerships = new ArrayList<>();
        for (String string : list) {
           dealerships.add(convertToDealership(string.split(",")));
        }
        return dealerships;
    }
    
    public static Dealership convertToDealership(String[] array){
        Dealership dealership = new Dealership(Long.parseLong(array[0]),array[1],array[2],array[3]);
        return dealership;
    }
    
    public static void main(String[] args) {
        List<Car> list = getCarList(readFile(Constant.carFile));
        
        for (Car car : list) {
            System.out.println(car);
        }
    }
}
