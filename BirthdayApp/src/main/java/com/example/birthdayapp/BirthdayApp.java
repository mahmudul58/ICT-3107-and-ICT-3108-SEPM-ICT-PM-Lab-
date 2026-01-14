package com.example.birthdayapp;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BirthdayApp extends Application {

    TextField nameField = new TextField();
    DatePicker datePicker = new DatePicker();
    TextField searchField = new TextField();
    ListView<String> listView = new ListView<>();
    BirthdayLogic logic;

    @Override
    public void start(Stage stage) {
        logic = new BirthdayLogic(nameField, datePicker, searchField, listView);

        // --- Form ---
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(15);
        inputGrid.setVgap(15);
        inputGrid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("নাম:");
        nameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
        nameField.setPromptText("বন্ধুর নাম লিখুন");

        Label dateLabel = new Label("জন্ম তারিখ:");
        datePicker.setPromptText("তারিখ নির্বাচন করুন");

        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(dateLabel, 0, 1);
        inputGrid.add(datePicker, 1, 1);

        // --- Buttons ---
        Button addBtn = new Button("যোগ করুন");
        Button updateBtn = new Button("আপডেট");
        Button deleteBtn = new Button("মুছে ফেলুন");
        Button upcomingBtn = new Button("আসন্ন জন্মদিন (Upcoming)");
        Button searchBtn = new Button("খুঁজুন");
        Button refreshBtn = new Button("রিসেট");

        HBox actionBox = new HBox(10, addBtn, updateBtn, deleteBtn);
        actionBox.setAlignment(Pos.CENTER);
        HBox searchBox = new HBox(10, new Label("অনুসন্ধান:"), searchField, searchBtn, refreshBtn);
        searchBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, inputGrid, actionBox, upcomingBtn, new Separator(), searchBox, listView);
        root.setPadding(new Insets(20));

        // --- Events ---
        addBtn.setOnAction(e -> logic.addBirthday());
        updateBtn.setOnAction(e -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            logic.updateBirthday(selected != null ? selected.split(" - ")[0] : null);
        });
        deleteBtn.setOnAction(e -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            logic.deleteBirthday(selected != null ? selected.split(" - ")[0] : null);
        });
        upcomingBtn.setOnAction(e -> logic.loadUpcomingBirthdays());
        searchBtn.setOnAction(e -> logic.searchBirthday());
        refreshBtn.setOnAction(e -> logic.loadBirthdays());
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String[] parts = newVal.split(" - ");
                nameField.setText(parts[0]);
                try { datePicker.setValue(java.time.LocalDate.parse(parts[1])); } catch (Exception ignored) {}
            }
        });

        // --- Initial Load ---
        logic.loadBirthdays();
        logic.showTodayNotification();

        stage.setScene(new Scene(root, 460, 680));
        stage.setTitle("জন্মদিন ব্যবস্থাপনা");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
