package com.example.birthdayapp;

import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BirthdayLogic {

    private TextField nameField, searchField;
    private DatePicker datePicker;
    private ListView<String> listView;

    public BirthdayLogic(TextField nameField, DatePicker datePicker, TextField searchField, ListView<String> listView) {
        this.nameField = nameField;
        this.datePicker = datePicker;
        this.searchField = searchField;
        this.listView = listView;
    }

    public void addBirthday() {
        if (nameField.getText().isEmpty() || datePicker.getValue() == null) {
            showAlert("Error", "দয়া করে নাম এবং তারিখ উভয়ই দিন।");
            return;
        }
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO classmates(name, birthdate) VALUES (?,?)");
            ps.setString(1, nameField.getText());
            ps.setDate(2, Date.valueOf(datePicker.getValue()));
            ps.executeUpdate();
            nameField.clear();
            datePicker.setValue(null);
            loadBirthdays();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("DB Error", e.getMessage());
        }
    }

    public void updateBirthday(String oldName) {
        if (oldName == null || datePicker.getValue() == null) {
            showAlert("Error", "আপডেট করার জন্য তালিকা থেকে নাম নির্বাচন করুন এবং নতুন তারিখ দিন।");
            return;
        }
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE classmates SET birthdate=?, name=? WHERE name=?");
            ps.setDate(1, Date.valueOf(datePicker.getValue()));
            ps.setString(2, nameField.getText());
            ps.setString(3, oldName);
            ps.executeUpdate();
            loadBirthdays();
            showAlert("Success", "আপডেট সম্পন্ন হয়েছে!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBirthday(String name) {
        if (name == null) {
            showAlert("Error", "মুছে ফেলার জন্য একটি নাম নির্বাচন করুন।");
            return;
        }
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM classmates WHERE name=?");
            ps.setString(1, name);
            ps.executeUpdate();
            nameField.clear();
            datePicker.setValue(null);
            loadBirthdays();
            showAlert("Success", "মুছে ফেলা হয়েছে!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadBirthdays() {
        listView.getItems().clear();
        try (Connection con = DBUtil.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM classmates");
            while (rs.next()) {
                listView.getItems().add(rs.getString("name") + " - " + rs.getDate("birthdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadUpcomingBirthdays() {
        listView.getItems().clear();
        try (Connection con = DBUtil.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM classmates ORDER BY MONTH(birthdate), DAY(birthdate)");
            while (rs.next()) {
                listView.getItems().add(rs.getString("name") + " - " + rs.getDate("birthdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBirthday() {
        listView.getItems().clear();
        String key = searchField.getText();
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM classmates WHERE name LIKE ? OR MONTH(birthdate)=?");
            ps.setString(1, "%" + key + "%");
            int month = 0;
            try { month = Integer.parseInt(key); } catch (Exception ignored) {}
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listView.getItems().add(rs.getString("name") + " - " + rs.getDate("birthdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTodayNotification() {
        LocalDate today = LocalDate.now();
        List<String> bdayNames = new ArrayList<>();
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT name FROM classmates WHERE DAY(birthdate)=? AND MONTH(birthdate)=?");
            ps.setInt(1, today.getDayOfMonth());
            ps.setInt(2, today.getMonthValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdayNames.add(rs.getString("name"));
            }
            if (!bdayNames.isEmpty()) {
                showAlert("🎉 শুভ জন্মদিন", "আজ জন্মদিন: " + String.join(", ", bdayNames));
            }
        } catch (SQLException e) {}
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
