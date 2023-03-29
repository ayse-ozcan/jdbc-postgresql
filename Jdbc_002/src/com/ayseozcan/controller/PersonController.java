package com.ayseozcan.controller;

import com.ayseozcan.entity.Person;
import com.ayseozcan.service.PersonService;

public class PersonController {
    private final PersonService personService;

    public PersonController() {
       this.personService = new PersonService();
    }

    public void insertPerson(Person person){
        personService.insertPerson(person);
    }
    public void getAllPerson(){
        personService.getAllPerson();
    }
}
