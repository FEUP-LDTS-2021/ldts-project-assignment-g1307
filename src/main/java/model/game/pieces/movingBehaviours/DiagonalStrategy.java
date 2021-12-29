package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class DiagonalStrategy implements MovingBehaviour{
    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> possibleMoves = new HashSet<>();

        int x = objectPosition.getCol() - 1;
        int y = objectPosition.getRow() - 1;

        while (b.positionInBoard(new Position(y, x))){
            Position movePosition = new Position(y, x);
            possibleMoves.add(movePosition);

            x--;
            y--;
        }

        x = objectPosition.getCol() - 1;
        y = objectPosition.getRow() + 1;

        while (b.positionInBoard(new Position(y, x))){
            Position movePosition = new Position(y, x);
            possibleMoves.add(movePosition);

            x--;
            y++;
        }

        x = objectPosition.getCol() + 1;
        y = objectPosition.getRow() + 1;

        while (b.positionInBoard(new Position(y, x))){
            Position movePosition = new Position(y, x);
            possibleMoves.add(movePosition);

            x++;
            y++;
        }

        x = objectPosition.getCol() + 1;
        y = objectPosition.getRow() - 1;

        while (b.positionInBoard(new Position(y, x))){
            Position movePosition = new Position(y, x);
            possibleMoves.add(movePosition);

            x++;
            y--;
        }

        return possibleMoves;
    }
}