package model.game.pieces.movingBehaviours;

import model.game.board.BoardModel;
import model.game.Position;

import java.util.*;

public class TwoAndOneStrategy implements MovingBehaviour{

    private final Direction direction;

    private final List<Position> dPos = Arrays.asList(new Position(1,0), new Position(2,0));

    public TwoAndOneStrategy(Direction direction){
        this.direction = direction;
    }

    public enum Direction{
        NORTH(-1),
        SOUTH(1);
        final int change;
        Direction(int change){this.change = change;}
    }

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> possibleMoves = new HashSet<>();

        Position mulPos = new Position(direction.change,1);

        for (Position position:dPos) {
            if(b.positionInBoard(objectPosition.add(position.mull(mulPos)))) possibleMoves.add(objectPosition.add(position.mull(mulPos)));
        }

        return possibleMoves;
    }
}
