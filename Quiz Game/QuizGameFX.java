package com.example.quiz_game;

import javafx.application.Application;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;

public class QuizGameFX extends Application {

    private List<Question> questions;

    // Game State
    private int index = 0;
    private int score = 0;
    private int timeSeconds = 30;
    private String playerName;

    // UI Elements
    Label qLabel = new Label();
    Label timerLabel = new Label("Time: 30");

    RadioButton a = new RadioButton();
    RadioButton b = new RadioButton();
    RadioButton c = new RadioButton();
    RadioButton d = new RadioButton();
    ToggleGroup tg = new ToggleGroup();

    Button nextBtn = new Button("Next");
    Button submitBtn = new Button("Submit");
    Button exitBtn = new Button("Exit");

    Timeline timeline;
    Stage mainStage;

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;

        TextField nameField = new TextField();
        Button startBtn = new Button("Start");

        // Welcome Scene
        VBox welcome = new VBox(10,
                new Label("Welcome to Quiz Game"),
                new Label("Enter Name:"),
                nameField,
                startBtn
        );
        welcome.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene welcomeScene = new Scene(welcome, 300, 200);

        // Setup Radio Buttons
        a.setToggleGroup(tg);
        b.setToggleGroup(tg);
        c.setToggleGroup(tg);
        d.setToggleGroup(tg);

        submitBtn.setVisible(false);

        // Quiz Scene
        VBox quiz = new VBox(10,
                timerLabel,
                qLabel,
                a, b, c, d,
                nextBtn,
                submitBtn,
                exitBtn
        );
        quiz.setStyle("-fx-padding: 20; -fx-alignment: center-left;");
        Scene quizScene = new Scene(quiz, 500, 400);

        // --- Event Handlers ---

        startBtn.setOnAction(e -> {
            playerName = nameField.getText();
            if (playerName.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please enter your name").show();
                return;
            }

            questions = DBUtil.getRandomQuestions();

            if (questions == null || questions.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No questions found!").show();
                return;
            }

            // Initialize Game State
            index = 0;
            score = 0;
            timeSeconds = 30; // Set total time
            timerLabel.setText("Time: " + timeSeconds);

            showQuestion();
            startGlobalTimer(); // Start the global timer once
            stage.setScene(quizScene);
        });

        nextBtn.setOnAction(e -> handleNextQuestion());

        submitBtn.setOnAction(e -> {
            checkAnswer(); // Check the last question
            finishGame();
        });

        exitBtn.setOnAction(e -> {
            if (timeline != null) timeline.stop();
            stage.close();
        });

        stage.setScene(welcomeScene);
        stage.setTitle("Quiz Game");
        stage.show();
    }

    private void showQuestion() {
        Question q = questions.get(index);

        qLabel.setText((index + 1) + ". " + q.getQuestion());
        a.setText(q.getA());
        b.setText(q.getB());
        c.setText(q.getC());
        d.setText(q.getD());

        tg.selectToggle(null);

        if (index == questions.size() - 1) {
            nextBtn.setVisible(false);
            submitBtn.setVisible(true);
        } else {
            nextBtn.setVisible(true);
            submitBtn.setVisible(false);
        }


    }

    private void handleNextQuestion() {
        checkAnswer();
        index++;
        showQuestion();
    }

    private void checkAnswer() {
        Toggle selected = tg.getSelectedToggle();

        if (selected == null) return;

        String ans = "";
        if (selected == a) ans = "A";
        else if (selected == b) ans = "B";
        else if (selected == c) ans = "C";
        else if (selected == d) ans = "D";

        if (ans.equals(questions.get(index).getCorrect())) {
            score++;
        }
    }

    private void startGlobalTimer() {
        if (timeline != null) timeline.stop();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeSeconds--;
            timerLabel.setText("Time: " + timeSeconds);

            if (timeSeconds <= 0) {
                timeline.stop();
                checkAnswer();
                finishGame();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void finishGame() {
        if (timeline != null) timeline.stop();

        try {
            DBUtil.saveResult(playerName, score);
        } catch (Exception ex) {
            System.err.println("Error saving: " + ex.getMessage());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        if (timeSeconds <= 0) {
            alert.setHeaderText("Time's Up! Thank you " + playerName);
        } else {
            alert.setHeaderText("Quiz Completed! Thank you " + playerName);
        }

        alert.setContentText("Your Score: " + score + " / " + questions.size());
        alert.showAndWait();
        mainStage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}