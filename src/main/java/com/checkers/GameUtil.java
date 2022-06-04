package com.checkers;

import java.util.ArrayList;

public class GameUtil {
    
    static final int EMPTY = 0, P1 = 1, KING_P1 = 2, P2 = 3, KING_P2 = 4;

    int[][] board;

    public GameUtil() {
        board = new int[10][10];
        initGame();
    }

    public void initGame() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (row % 2 == col % 2) {
                    if (row < 4)
                        board[row][col] = P2;
                    else if (row > 5)
                        board[row][col] = P1;
                    else
                        board[row][col] = EMPTY;
                } else {
                    board[row][col] = EMPTY;
                }
            }
        }
    }

    public int playerAt(int row, int col) {
        return board[row][col];
    }


    public void moves(Move move) {
        moves(move.initRow, move.initCol, move.finalRow, move.finalCol);
    }


    public void moves(int initRow, int initCol, int finalRow, int finalCol) {
        board[finalRow][finalCol] = board[initRow][initCol];
        board[initRow][initCol] = EMPTY;

        if (finalRow == 0 && board[finalRow][finalCol] == P1) {
            board[finalRow][finalCol] = KING_P1;
        }

        if (finalRow == 9 && board[finalRow][finalCol] == P2) {
            board[finalRow][finalCol] = KING_P2;
        }

        if (initRow - finalRow == -2 || initRow - finalRow == 2) {
            int jumpRow = (initRow + finalRow) / 2;
            int jumpCol = (initCol + finalCol) / 2;
            board[jumpRow][jumpCol] = EMPTY;
        }

    }

    private boolean validEat(int player, int playerRow, int playerCol, int enemyRow, int enemyCol,
            int finalRow, int finalCol) {

        if (finalRow < 0 || finalRow >= 10 || finalCol < 0 || finalCol >= 10
                || board[finalRow][finalCol] != EMPTY) {
            return false;
        }

        if (player == P1) {
            if (board[playerRow][playerCol] == P1 && finalRow > playerRow) {
                return false;
            }
            if (board[enemyRow][enemyCol] != P2 && board[enemyRow][enemyCol] != KING_P2) {
                return false;
            }
            return true;
        } else {
            if (board[playerRow][playerCol] == P2 && finalRow < playerRow) {
                return false;
            }
            if (board[enemyRow][enemyCol] != P1 && board[enemyRow][enemyCol] != KING_P1) {
                return false;
            }
            return true;
        }

    }

    private boolean validMove(int player, int fromRow, int fromCol, int toRow, int toCol) {
        if (toRow < 0 || toRow >= 10 || toCol < 0 || toCol >= 10 || board[toRow][toCol] != EMPTY) {
            return false;
        }

        if (player == P1) {
            if (board[fromRow][fromCol] == P1 && toRow > fromRow)
                return false;
            return true;
        } else {
            if (board[fromRow][fromCol] == P2 && toRow < fromRow)
                return false;
            return true;
        }

    }

    public Move[] getValidMoves(int player) {

        if (player != P1 && player != P2) {
            return null;
        }

        int playerKing = (player == P1) ? KING_P1 : KING_P2;
        ArrayList<Move> moves = new ArrayList<Move>();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                listOfValidEat(player, playerKing, moves, row, col);
            }
        }

        if (moves.size() == 0) {
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (board[row][col] == player || board[row][col] == playerKing) {
                        if (validMove(player, row, col, row + 1, col + 1)) {
                            moves.add(new Move(row, col, row + 1, col + 1));
                        }
                        if (validMove(player, row, col, row - 1, col + 1)) {
                            moves.add(new Move(row, col, row - 1, col + 1));
                        }
                        if (validMove(player, row, col, row + 1, col - 1)) {
                            moves.add(new Move(row, col, row + 1, col - 1));
                        }
                        if (validMove(player, row, col, row - 1, col - 1)) {
                            moves.add(new Move(row, col, row - 1, col - 1));
                        }
                    }
                }
            }
        }

        return arrayOfMoves(moves);
    }

    public Move[] getValidEatMoves(int player, int row, int col) {
        if (player != P1 && player != P2) {
            return null;
        }

        int playerKing = (player == P1) ? KING_P1 : KING_P2;
        ArrayList<Move> moves = new ArrayList<Move>();
        listOfValidEat(player, playerKing, moves, row, col);

        return arrayOfMoves(moves);
    }

    private void listOfValidEat(int player, int playerKing, ArrayList<Move> moves, int row,
            int col) {
        if (board[row][col] == player || board[row][col] == playerKing) {
            if (validEat(player, row, col, row - 1, col + 1, row - 2, col + 2)) {
                moves.add(new Move(row, col, row - 2, col + 2));
            }

            if (validEat(player, row, col, row - 1, col - 1, row - 2, col - 2)) {
                moves.add(new Move(row, col, row - 2, col - 2));
            }

            if (validEat(player, row, col, row + 1, col - 1, row + 2, col - 2)) {
                moves.add(new Move(row, col, row + 2, col - 2));
            }

            if (validEat(player, row, col, row + 1, col + 1, row + 2, col + 2)) {
                moves.add(new Move(row, col, row + 2, col + 2));
            }
        }
    }

    private Move[] arrayOfMoves(ArrayList<Move> moves) {
        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = moves.get(i);
            return moveArray;
        }
    }
}
