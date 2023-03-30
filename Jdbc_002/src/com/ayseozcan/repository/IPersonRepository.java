package com.ayseozcan.repository;

import com.ayseozcan.entity.Person;

public interface IPersonRepository {
    public void insertPerson(Person person);
    public void getAllPerson();
    public void deleteAllRecords();
    public void updateEmail(String email, int id);
    public void getPersonById(int id);
    public void deletePerson(int id);

}
