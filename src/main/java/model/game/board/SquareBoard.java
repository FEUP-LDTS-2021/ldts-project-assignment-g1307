package model.game;

import java.util.HashSet;
import java.util.Set;

public class SquareBoard implements BoardModel {
    private final int rows;
    private final int columns;

    private final Set<Position> whiteSquares;

    public SquareBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        whiteSquares = createWhiteSquares();
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

    private Set<Position> createWhiteSquares(){
        Set<Position> res = new HashSet<>();
        for(int row = 1; row <= 32; row++){
            for(int col = 1; col <= 32; col++){
                if((row+col)%2 == 0) res.add(new Position(row, col));
            }
        }
        return res;
    }

    public Set<Position> getWhiteSquares() {
        return whiteSquares;
    }
}
