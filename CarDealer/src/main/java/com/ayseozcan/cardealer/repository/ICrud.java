
package com.ayseozcan.cardealer.repository;

import java.util.List;


public interface ICrud <T>{
    
   void save(T t);
   void update(T t,long id);
   void delete(Long id);
   List<T> findAll();
   void saveAll(List<T> t);
   
   
}
