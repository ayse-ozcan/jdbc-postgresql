package com.ayseozcan.repository;

import com.ayseozcan.entity.Person;

public interface IPersonRepository {
    public void insertPerson(Person person);
    public void getAllPerson();

}
