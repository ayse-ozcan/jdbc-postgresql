package com.ayseozcan.service;

import com.ayseozcan.entity.Person;
import com.ayseozcan.repository.PersonRepository;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService() {
        this.personRepository = new PersonRepository();
    }
    public void insertPerson(Person person){
        if(person.getEmail().contains("@")){
            personRepository.insertPerson(person);
        }else{
            System.out.println("please check your mail");
        }
    }

    public void getAllPerson() {
        personRepository.getAllPerson();
    }
    public void deleteAllRecords(){
        personRepository.deleteAllRecords();
    }
    public void updateEmail(String email, int id){
        personRepository.updateEmail(email, id);
    }
    public void getPersonById(int id){
        personRepository.getPersonById(id);
    }
    public void deletePerson(int id){
        personRepository.deletePerson(id);
    }
}
