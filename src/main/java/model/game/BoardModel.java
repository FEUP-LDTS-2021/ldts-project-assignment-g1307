package model.game;

import model.Model;

public class BoardModel implements Model {
    private final int rows;
    private final int columns;

    BoardModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
