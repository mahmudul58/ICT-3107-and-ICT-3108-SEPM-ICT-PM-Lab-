package com.example.quiz_game;
import java.sql.*;
import java.util.*;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/quizdb";
    private static final String USER = "root";
    private static final String PASS = "DB_password";

    public static List<Question> getRandomQuestions() {
        List<Question> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions ORDER BY RAND() LIMIT 5");

            while (rs.next()) {
                list.add(new Question(
                        rs.getString("question"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getString("correct")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveResult(String name, int score) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO results(name, score) VALUES (?,?)");
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
