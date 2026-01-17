package com.example.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    // DB Connection Details
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root"; // Change to your username
    private static final String PASS = "password"; // Change to your password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            if ("Insert".equals(action)) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, email, course) VALUES (?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, course);
                ps.executeUpdate();
                request.setAttribute("message", "Student Inserted Successfully!");
            }
            else if ("Update".equals(action)) {
                // Update Name and Course based on Email
                PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=?, course=? WHERE email=?");
                ps.setString(1, name);
                ps.setString(2, course);
                ps.setString(3, email);
                int rows = ps.executeUpdate();
                request.setAttribute("message", rows > 0 ? "Student Updated!" : "Email not found!");
            }
            else if ("Delete".equals(action)) {
                // Delete based on Email
                PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE email=?");
                ps.setString(1, email);
                int rows = ps.executeUpdate();
                request.setAttribute("message", rows > 0 ? "Student Deleted!" : "Email not found!");
            }
            else if ("View".equals(action)) {
                List<Map<String, String>> students = new ArrayList<>();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students");

                while (rs.next()) {
                    Map<String, String> student = new HashMap<>();
                    student.put("name", rs.getString("name"));
                    student.put("email", rs.getString("email"));
                    student.put("course", rs.getString("course"));
                    students.add(student);
                }
                request.setAttribute("students", students);
                request.getRequestDispatcher("list.jsp").forward(request, response);
                return; // Stop execution here to avoid loading index.jsp
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}