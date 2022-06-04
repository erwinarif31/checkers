package com.checkers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    public void setBoard() {
        GraphicsContext square = getGraphicsContext2D();
        square.setFont(Font.font(18));

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                drawBoard(square, row, col);
                piecePlacement(square, row, col);
            }
        }

        if (isPlaying) {

            square.setStroke(Color.rgb(147, 129, 255));
            square.setLineWidth(2);
            for (int i = 0; i < validMoves.length; i++) {
                square.strokeRect(4 + validMoves[i].initCol * 40, 4 + validMoves[i].initRow * 40,
                        36, 36);
            }

            if (selectedRow >= 0) {
                square.setStroke(Color.rgb(12, 124, 89));
                square.setLineWidth(2);
                for (int i = 0; i < validMoves.length; i++) {
                    if (validMoves[i].initCol == selectedCol
                            && validMoves[i].initRow == selectedRow) {
                        square.strokeRect(4 + validMoves[i].finalCol * 40,
                                4 + validMoves[i].finalRow * 40, 36, 36);
                    }
                }
            }
        }
    }

    private void drawBoard(GraphicsContext square, int row, int col) {
        if (row % 2 == col % 2) {
            square.setFill(Color.rgb(245, 212, 161));
        } else {
            square.setFill(Color.rgb(142, 95, 58));
        }
        square.fillRect(2 + col * 40, 2 + row * 40, 40, 40);
    }

    private void piecePlacement(GraphicsContext square, int row, int col) {
        switch (game.playerAt(row, col)) {
            case GameUtil.P1:
                square.setFill(GameUI.player1Color);
                square.fillOval(10 + col * 40, 10 + row * 40, 24, 24);
                break;
            case GameUtil.P2:
                square.setFill(GameUI.player2Color);
                square.fillOval(10 + col * 40, 10 + row * 40, 24, 24);
                break;
            case GameUtil.KING_P1:
                square.setFill(GameUI.player1Color);
                square.fillOval(8 + col * 40, 8 + row * 40, 28, 28);
                square.setFill(Color.GOLD);
                square.fillText("👑", 12.5 + col * 40, 29 + row * 40);
                break;
            case GameUtil.KING_P2:
                square.setFill(GameUI.player2Color);
                square.fillOval(8 + col * 40, 8 + row * 40, 28, 28);
                square.setFill(Color.GOLD);
                square.fillText("👑", 12.5 + col * 40, 29 + row * 40);
                break;
        }
    }


    public void mousePressed(MouseEvent e) {

    }

}
