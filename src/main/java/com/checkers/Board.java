package com.checkers;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class Board extends Canvas {
    GameUtil game;
    boolean isPlaying;
    int activePlayer;
    int selectedRow, selectedCol;
    Move[] validMoves;

    public Board() {
        super(400, 400);
        game = new GameUtil();
        newGame();
    }

    public void newGame() {
        game.initGame();
        activePlayer = GameUtil.P1;
        validMoves = game.getValidMoves(GameUtil.P1);
        selectedRow = -1;
        isPlaying = true;

        setBoard();
    }

    public void resign() {
        if (!isPlaying) {
            GameUI.statusInfo.setText("The game hasn't started yet");
            return;
        }

        if (activePlayer == GameUtil.P1)
            gameOver(GameUI.player1Name + " resigns." + GameUI.player2Name + " wins.");
        else
            gameOver(GameUI.player2Name + " resigns. " + GameUI.player1Name + " wins.");
    }

    public void gameOver(String string) {
        GameUI.statusInfo.setText(string);
        isPlaying = false;
    }

    public void setBoard() {}

    public void mousePressed(MouseEvent e) {}

}
