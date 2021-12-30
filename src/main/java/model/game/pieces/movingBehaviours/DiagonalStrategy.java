package model.game.pieces.movingBehaviours;

import model.game.board.BoardModel;
import model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class DiagonalStrategy implements MovingBehaviour{
    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> possibleMoves = new HashSet<>();

        int row = objectPosition.getRow();
        int col = objectPosition.getCol();

        for (int i = 1; b.positionInBoard(new Position(row +i,col + i)); i++) {
            possibleMoves.add(new Position(row+i, col+i));
        }
        for (int i = 1; b.positionInBoard(new Position(row-i,col-i)); i++) {
            possibleMoves.add(new Position(row-i, col-i));
        }
        for (int i = 1; b.positionInBoard(new Position(row+i,col-i)); i++) {
            possibleMoves.add(new Position(row+i, col-i));
        }
        for (int i = 1; b.positionInBoard(new Position(row-i,col+i)); i++) {
            possibleMoves.add(new Position(row-i, col+i));
        }
        return possibleMoves;
    }
}