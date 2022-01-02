package model.game.board;

import model.game.Position;
import model.game.board.BoardModel;

public class SquareBoard implements BoardModel {
    private final int rows;
    private final int columns;

    public SquareBoard(int rowsAndCols) {
        this.rows = rowsAndCols;
        this.columns = rowsAndCols;
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
}
