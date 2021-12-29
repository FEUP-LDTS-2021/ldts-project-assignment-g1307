package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.Set;

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
        return null;
    }
}
