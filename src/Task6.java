import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task6 {
    private static final String URL = "jdbc:mysql://localhost:3306/task6";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "2319A1923b12";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading... \nSuccses!");
        }catch (Exception e){
            e.printStackTrace();
        }
        Connection connection;
        try{
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conected!");
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            statement.executeUpdate("delete from task6.test");

            statement.executeUpdate("insert into task6.test (id, name, age) VALUES " +
                    "(1, 'Ivan', 25)," +
                    "(2, 'Zahar', 32)," +
                    "(3, 'Andriy', 29)");
            System.out.println("Data added");
            resultSet = statement.executeQuery("select * from task6.test");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getInt("age"));
                            }

            statement.executeUpdate("delete from task6.test where id = 2");
            resultSet = null;
            System.out.println("Data deleted!");
            resultSet = statement.executeQuery("select * from task6.test");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getInt("age"));
            }

            statement.executeUpdate("alter table task6.test add constraint  unique (id)");
            System.out.println("constraint added");

            statement.executeUpdate("alter table task6.test drop column Cyty");
            statement.executeUpdate("alter table task6.test add column (Cyty varchar(20) default 'unknown')");
            System.out.println("new column added");
            statement.executeUpdate("update task6.test set task6.test.Cyty = 'Kiev' where id = 3");
            System.out.println("data updated");
            resultSet = null;
            resultSet = statement.executeQuery("select * from task6.test");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name")
                        + " " + resultSet.getInt("age") + " " + resultSet.getString("Cyty"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
