import com.ayseozcan.controller.PersonController;
import com.ayseozcan.entity.Person;
import java.util.Date;


public class Main {

    /*
    1-create personDb database --> id, first_name, last_name, joined_date
    2-person class --> entity package
    3-JDBCHelper class --> util package
      getConnection() returns connection
    4-insertPerson() --> repository package
    5-getAllPersons()

     */
    public static void main(String[] args) {
        /*
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("osman","akar","osman@gmail.com", new Date());
        personRepository.insertPerson(person);
        personRepository.getAllPerson();
         */

        PersonController personController = new PersonController();
        Person person = new Person("Mert","Bahar","bahar@gmail.com",new Date());
        personController.insertPerson(person);
        personController.getAllPerson();
    }
}