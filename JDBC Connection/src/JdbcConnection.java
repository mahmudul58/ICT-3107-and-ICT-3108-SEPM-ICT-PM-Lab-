import java.sql.*;
import java.util.Scanner;

public class JdbcConnection {
    public static void main(String[] args) {

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/student",
                        "root",
                        ""
                );
                Scanner sc = new Scanner(System.in)
        ) {

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            String insertSQL = "INSERT INTO users(name) VALUES (?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSQL);
            insertPs.setString(1, name);
            insertPs.executeUpdate();
            System.out.println("Data inserted.");

            String selectSQL = "SELECT id, name FROM users";
            PreparedStatement selectPs = conn.prepareStatement(selectSQL);
            ResultSet rs = selectPs.executeQuery();

            System.out.println("Records:");
            while (rs.next()) {
                System.out.println(
                        "ID : " + rs.getInt("id") + " , Name : " + rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
