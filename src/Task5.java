import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task5 {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoindb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "2319A1923b12";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading... \nSuccses!");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conected!");
            Statement st1 = connection.createStatement();
            ResultSet resultSet = st1.executeQuery(
                    "select ename, phone, adress from emploees join emploeesdetails " +
                            "where emploees.id = emploeesdetails.id");
            System.out.println("Contact info:");
            while (resultSet.next()){
                System.out.println("------------------------------------");
                System.out.println(resultSet.getString("ename"));
                System.out.println(resultSet.getString("phone"));
                System.out.println(resultSet.getString("adress"));
                System.out.println("------------------------------------");
            }

            ResultSet resultSet2 = st1.executeQuery(
                    "select ename, birthDate, phone from emploees join emploeesdetails " +
                            "where emploees.id = emploeesdetails.id and emploeesdetails.ciwilstatus = 'single'");
            System.out.println("Single info:");
            while (resultSet2.next()){
                System.out.println("------------------------------------");
                System.out.println(resultSet2.getString("ename"));
                System.out.println(resultSet2.getString("phone"));
                System.out.println(resultSet2.getString("birthDate"));
                System.out.println("------------------------------------");
            }

            ResultSet resultSet3 = st1.executeQuery(
                    "select ename, birthDate, phone from emploees join emploeesdetails " +
                            "on emploees.id = emploeesdetails.id " +
                            "join posittions on emploees.EName = posittions.name " +
                            "where posittions.position = 'manager'");
            System.out.println("Managers info:");
            while (resultSet3.next()){
                System.out.println("------------------------------------");
                System.out.println(resultSet3.getString("ename"));
                System.out.println(resultSet3.getString("phone"));
                System.out.println(resultSet3.getString("birthDate"));
                System.out.println("------------------------------------");
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}