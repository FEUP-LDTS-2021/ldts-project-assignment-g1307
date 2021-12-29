package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class MovingBehaviourGroup implements MovingBehaviour{
    Set<MovingBehaviour> movingBehaviours;

    MovingBehaviourGroup(Set<MovingBehaviour> movingBehaviours) {
        this.movingBehaviours = movingBehaviours;
    }

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> onBoardMoves = new HashSet<>();
        for (MovingBehaviour movingBehaviour: movingBehaviours) {
            onBoardMoves.addAll(movingBehaviour.getMoves(b,objectPosition));
        }
        return onBoardMoves;
    }

    public void add(MovingBehaviour movingBehaviour) {
        movingBehaviours.add(movingBehaviour);
    }

    public void remove(MovingBehaviour movingBehaviour) {
        movingBehaviours.remove(movingBehaviour);
    }

    public Set<MovingBehaviour> getMovingBehaviours(){
        return movingBehaviours;
    }
}
