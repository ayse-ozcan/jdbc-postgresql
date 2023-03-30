import com.ayseozcan.controller.PersonController;
import com.ayseozcan.controller.PostController;
import com.ayseozcan.entity.Person;
import com.ayseozcan.util.Menu;

import java.util.Date;


public class Main {

    /*
    1-create personDb database --> id, first_name, last_name, joined_date
    2-person class --> entity package
    3-JDBCHelper class --> util package
      getConnection() returns connection
    4-insertPerson() --> repository package
    5-getAllPersons()
    6-deleteAllRecords()
    7-updateEmail()
    8-getPersonById()
    9-deletePerson()
    10-post class
     */
    public static void main(String[] args) {
        /*
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("osman","akar","osman@gmail.com", new Date());
        personRepository.insertPerson(person);
        personRepository.getAllPerson();
         */

        //PersonController personController = new PersonController();
        //Person person = new Person("Eec","Oz","ece@gmail.com",new Date());
        //personController.insertPerson(person);
        //personController.getAllPerson();
        //personController.deleteAllRecords();
        //personController.updateEmail("bb@gmail.com",20);
        //personController.updateEmail("cc@gmail.com",21);
        //personController.getPersonById(30);
        //personController.deletePerson(21);
        Menu menu = new Menu();
        menu.menu();

        PostController postController = new PostController();
        postController.getAllTweet();
    }
}