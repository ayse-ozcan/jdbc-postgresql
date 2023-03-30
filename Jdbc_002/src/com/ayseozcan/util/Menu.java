package com.ayseozcan.util;

import com.ayseozcan.controller.PersonController;
import com.ayseozcan.entity.Person;
import java.util.Scanner;

public class Menu {

    private final PersonController personController;
    public Menu() {
        this.personController = new PersonController();
    }
    static Scanner scanner = new Scanner(System.in);
    static String email;
    static String firstName;
    static String lastName;
    static int id;
    public void menu(){
        int input;
        do{

            System.out.println("-------------*MENU*-------------");
            System.out.println("1- INSERT PERSON");
            System.out.println("2- GET ALL PERSON");
            System.out.println("3- DELETE ALL RECORDS");
            System.out.println("4- UPDATE EMAIL");
            System.out.println("5- GET PERSON BY ID");
            System.out.println("6- DELETE PERSON");
            System.out.println("7- EXIT");
            System.out.println("--------------------------------");
            System.out.print("Enter your choice: ");
            input = scanner.nextInt();
            scanner.nextLine();

            switch (input){
                case 1:
                    System.out.println("name?= ");
                    firstName = scanner.nextLine();
                    System.out.println("surname?= ");
                    lastName = scanner.nextLine();
                    System.out.println("email?= ");
                    email = scanner.nextLine();

                    Person person = new Person(firstName, lastName, email );
                    personController.insertPerson(person);
                    break;
                case 2:
                    personController.getAllPerson();
                    break;
                case 3:
                    personController.deleteAllRecords();
                    break;
                case 4:
                    System.out.println("email?= ");
                    email = scanner.nextLine();
                    System.out.println("id?=");
                    id = Integer.parseInt(scanner.nextLine());
                    personController.updateEmail(email, id);
                    break;
                case 5:
                    System.out.println("id?=");
                    id = scanner.nextInt();
                    personController.getPersonById(id);
                    break;
                case 6:
                    System.out.println("id?=");
                    id = scanner.nextInt();
                    personController.deletePerson(id);
                    break;
                case 7:
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }while(input != 7);


    }

}
