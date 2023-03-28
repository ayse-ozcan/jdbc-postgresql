import org.postgresql.util.PSQLException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        /*
        database --> school
        students table --> name, surname, city
        execute()
        create()
        update()
        countByCityName()
         */

        String url = "jdbc:postgresql://localhost:5432/school";
        String username = "postgres";
        String password = "*******";

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Baglanti gerceklesti");
            String sql = "insert into students(name,surname,city) values('hakan','sonbahar','ankara')";
            //String sql2 = "delete from students where id = 1";
            //execute(connection,sql);
            //Student student = new Student("mesut", "ari", "ankara", 30);
            //createStudent(connection,student);
            //Student student = new Student("zeki", "oz", "sivas", 57);
            //updateStudent(connection,student,2);
            //findStudentByCityName(connection,"ankara");
            delete(connection,3);

        } catch (PSQLException e) {
            System.err.println("database ismini kontrol edin");
        }catch (Exception e){
            System.err.println("beklenmedik hata");

        }finally {
            try {
                if(connection != null){
                    connection.close();
                    System.out.println("baglanti kapatildi");
                }
            } catch (SQLException e) {
                System.err.println("kapatma hatasi");
            }
        }
    }

    public static void execute(Connection connection, String sql){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("islem basarili");
        } catch (SQLException e) {
            System.out.println("insert islemi basarisiz");
        }
    }

    public static void createStudent(Connection connection, Student student){
        String sql = "insert into students(name,surname,city,age) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, student.getAge());

            preparedStatement.executeUpdate();
            System.out.println(student.getName() + "veri tabanina eklendi");
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateStudent(Connection connection, Student student, int id){
        String sql = "update students set name=? , surname=?, city=?, age=? where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, id);

            int effectedRow = preparedStatement.executeUpdate();
            if(effectedRow > 0){
                System.out.println("update islemi gerceklesti");
            }else{
                System.out.println("lutfen girilen id yi kontrol edin");
            }
            System.out.println(id + "numarali id li kullanici guncellendi");
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findStudentByCityName(Connection connection, String cityName){
        //String sql = "select count(*) from students where lower(city) = ?";
        String sql = "select name, surname from students where lower(city) = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
           // preparedStatement.setString(1,cityName.toLowerCase());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));

                //System.out.println(resultSet.getInt("count"));
                //System.out.println(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(Connection connection, int id){
        String sql = "delete from students where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int effectedRow = preparedStatement.executeUpdate();
            if( effectedRow > 0){
                System.out.println("silme islemi gerceklesti");
            }else{
                System.out.println("id yi kontrol edin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}