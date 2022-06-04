package com.checkers;

public class Move {
    int initRow, initCol, finalRow, finalCol;


    public Move(int initRow, int initCol, int finalRow, int finalCol) {
        this.initRow = initRow;
        this.initCol = initCol;
        this.finalRow = finalRow;
        this.finalCol = finalCol;
    }

    boolean isEatMove() {
        return initRow - finalRow == 2 || initRow - finalRow == -2;
    }
}
