package model.game.pieces.movingBehaviours;

import model.game.board.BoardModel;
import model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class SideStrategy implements MovingBehaviour{

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> possibleMoves = new HashSet<>();

        for (int i = 1; b.positionInBoard(new Position(objectPosition.getRow(), i)); i++){
            Position movePosition = new Position(objectPosition.getRow(), i);

            if (!objectPosition.equals(movePosition))
                possibleMoves.add(movePosition);
        }

        for (int i = 1; b.positionInBoard(new Position(i,objectPosition.getCol())); i++){
            Position movePosition = new Position(i, objectPosition.getCol());

            if (!objectPosition.equals(movePosition))
                possibleMoves.add(movePosition);
        }

        return possibleMoves;
    }
}
