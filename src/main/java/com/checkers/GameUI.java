package com.checkers;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * JavaFX App
 */
public class GameUI extends Application {
    Board board;

    public static Button newGameBtn;
    public static Button resignBtn;
    public static Button playBtn;
    public static Button quitBtn;
    public static Button homeBtn;
    public static TextField p1Name;
    public static TextField p2Name;
    public static ColorPicker p1Color;
    public static ColorPicker p2Color;
    public static Label statusInfo;
    public static String player1Name;
    public static String player2Name;
    public static Color player2Color;
    public static Color player1Color;
    private Image icon;

    @Override
    public void start(Stage primaryStage) throws Exception {
        icon = new Image(getClass().getResourceAsStream("img/icon.jpg"));

        playBtn = new Button("Play");
        playBtn.setStyle(
                "-fx-background-color: rgb(17, 19, 68); -fx-text-fill: rgb(239, 233, 231); -fx-font-weight: bold;");
        playBtn.relocate(150, 280);
        playBtn.resize(100, 30);
        playBtn.setManaged(false);

        statusInfo = new Label();
        statusInfo.setFont(Font.font(null, FontWeight.BOLD, 14));
        statusInfo.setTextFill(Color.rgb(249, 207, 242));

        HBox labelContainer = new HBox();
        labelContainer.getChildren().add(statusInfo);
        labelContainer.setManaged(false);
        labelContainer.relocate(30, 465);
        labelContainer.resize(500, 30);
        labelContainer.setAlignment(Pos.CENTER);

        newGameBtn = new Button("New Game");
        newGameBtn.resize(100, 30);
        newGameBtn.setOnAction(e -> {
            board.newGame();
        });
        newGameBtn.setStyle(
                "-fx-background-color: rgb(17, 19, 68); -fx-text-fill: rgb(239, 233, 231); -fx-font-weight: bold;");
        newGameBtn.setManaged(false);

        resignBtn = new Button("Resign");
        resignBtn.resize(100, 30);
        resignBtn.setOnAction(e -> board.resign());
        resignBtn.setStyle(
                "-fx-background-color: rgb(17, 19, 68); -fx-text-fill: rgb(239, 233, 231); -fx-font-weight: bold;");
        resignBtn.setManaged(false);

        quitBtn = new Button("Quit");
        quitBtn.setStyle(
                "-fx-background-color: rgb(17, 19, 68); -fx-text-fill: rgb(239, 233, 231); -fx-font-weight: bold;");
        quitBtn.relocate(300, 280);
        quitBtn.resize(100, 30);
        quitBtn.setManaged(false);
        quitBtn.setOnAction(e -> System.exit(0));

        homeBtn = new Button("Home");
        homeBtn.setStyle(
                "-fx-background-color: rgb(17, 19, 68); -fx-text-fill: rgb(239, 233, 231); -fx-font-weight: bold;");
        homeBtn.resize(100, 30);
        homeBtn.setManaged(false);

        // HOME-SCENE
        p1Name = new TextField("Player 1");
        p1Name.setPrefWidth(200);
        p1Name.relocate(75, 180);

        p2Name = new TextField("Player 2");
        p2Name.setPrefWidth(200);
        p2Name.relocate(75, 230);

        p1Color = new ColorPicker(Color.BLACK);
        p1Color.setPrefWidth(150);
        p1Color.relocate(325, 180);

        p2Color = new ColorPicker(Color.RED);
        p2Color.setPrefWidth(150);
        p2Color.relocate(325, 230);

        Pane homeScreen = new Pane();
        homeScreen.setStyle("-fx-background-color: #52154E");
        homeScreen.setPrefWidth(550);
        homeScreen.setPrefHeight(600);
        homeScreen.getChildren().addAll(quitBtn, p1Name, p1Color, p2Name, p2Color, playBtn);

        Scene homeScene = new Scene(homeScreen);

        // END OF HOME-SCENE

        // PLAYING-SCENE
        board = new Board();
        board.setBoard();
        board.relocate(75, 50);
        board.setOnMousePressed(e -> {
            board.mousePressed(e);
        });

        resignBtn.relocate(105, 510);
        newGameBtn.relocate(225, 510);
        homeBtn.relocate(345, 510);
        Pane root = new Pane();
        root.setPrefWidth(550);
        root.setPrefHeight(600);
        root.getChildren().addAll(board, newGameBtn, resignBtn, homeBtn, labelContainer);
        root.setStyle("-fx-background-color: #52154E");
        Scene scene = new Scene(root);

        playBtn.setOnAction(e -> {
            stage.setScene(scene);
            player1Name = new String(p1Name.getText());
            player2Name = new String(p2Name.getText());
            if (p1Color.getValue().equals(Color.rgb(142, 95, 58))
                    || p1Color.getValue().equals(Color.rgb(245, 212, 161))
                    || p1Color.getValue().equals(Color.GOLD)) {
                player1Color = Color.BLACK;
            } else {
                player1Color = p1Color.getValue();
            }

            if (p2Color.getValue().equals(Color.rgb(142, 95, 58))
                    || p2Color.getValue().equals(Color.rgb(245, 212, 161))
                    || p2Color.getValue().equals(Color.GOLD)) {
                player2Color = Color.RED;
            } else {
                player2Color = p2Color.getValue();
            }

            if (p1Color.getValue().equals(p2Color.getValue())) {
                player2Color = Color.AQUA;
            }

            statusInfo.setText(App.p1Name.getText() + "'s turn");
            board.newGame();
        });

        homeBtn.setOnAction(e -> {
            stage.setScene(homeScene);
        });

        // END OF PLAYING SCENE1

        VBox winScreen = new VBox();
        winScreen.setPrefWidth(550);
        winScreen.setPrefHeight(600);
        Scene winScene = new Scene(winScreen);

        // stage.getIcons().add(icon);
        stage.setScene(homeScene);
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("CHECXERS");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
