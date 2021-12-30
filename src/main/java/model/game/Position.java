package model.game;

import java.util.Objects;

public class Position {

    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public Position add(Position position) {
        return new Position(this.row + position.getRow(),this.col + position.getCol());
    }

    public Position mull(Position position) {
        return new Position(this.row * position.getRow(),this.col * position.getCol());
    }
}
