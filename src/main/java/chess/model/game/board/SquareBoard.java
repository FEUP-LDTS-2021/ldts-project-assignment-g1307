package chess.model.game.board;

import chess.model.game.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SquareBoard implements BoardModel {
    private final int rows;
    private final int columns;

    private final List<BoardCase> boardCases;
    private final Color blackCaseColor;
    private final Color whiteCaseColor;

    public SquareBoard(int rowsAndCols, Color whiteCaseColor, Color blackCaseColor) {
        this.rows = rowsAndCols;
        this.columns = rowsAndCols;
        this.whiteCaseColor = whiteCaseColor;
        this.blackCaseColor = blackCaseColor;
        boardCases = createBoardCases();
    }

    public SquareBoard(int rowsAndCols) {
        this.rows = rowsAndCols;
        this.columns = rowsAndCols;
        this.whiteCaseColor = new Color(238,238,210);
        this.blackCaseColor = new Color(118,150,86);
        boardCases = createBoardCases();
    }

    private List<BoardCase> createBoardCases() {
        List<BoardCase> boardCases = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                if((row+col)%2 == 0)
                    boardCases.add(new BoardCase(whiteCaseColor, new Position(row,col)));
                else
                    boardCases.add(new BoardCase(blackCaseColor, new Position(row,col)));
            }
        }
        return boardCases;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public boolean positionInBoard(Position position) {
        return position.getCol() > 0 && position.getCol() <= columns && position.getRow() > 0 && position.getRow() <= rows;
    }

    @Override
    public List<BoardCase> getCases() {
        return boardCases;
    }
}
