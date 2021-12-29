package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.*;

public class TwoAndOneStrategy implements MovingBehaviour{

    private Direction direction;

    public TwoAndOneStrategy(Direction direction){
        this.direction = direction;
    }

    public enum Direction{
        NORTH,
        SOUTH
    }

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> possibleMoves = new HashSet<>();

        int row = objectPosition.getRow();
        int col = objectPosition.getCol();
        List<Position> possiblePositions;

        if (direction == Direction.NORTH){
            possiblePositions = Arrays.asList(new Position(row - 1, col), new Position(row - 2, col),
                     new Position(row -1, col - 1), new Position(row - 1, col + 1));
        }
        else {
            possiblePositions = Arrays.asList(new Position(row + 1, col), new Position(row + 2, col),
                    new Position(row + 1, col - 1), new Position(row + 1, col + 1));
        }

        for (Position position : possiblePositions){
            if(b.positionInBoard(position)) possibleMoves.add(position);
        }

        return possibleMoves;
    }
}
