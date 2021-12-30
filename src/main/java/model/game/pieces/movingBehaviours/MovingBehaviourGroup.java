package model.game.pieces.movingBehaviours;

import model.game.board.BoardModel;
import model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class MovingBehaviourGroup implements MovingBehaviour{
    Set<MovingBehaviour> movingBehaviours;

    public MovingBehaviourGroup(Set<MovingBehaviour> movingBehaviours) {
        this.movingBehaviours = movingBehaviours;
    }
    public MovingBehaviourGroup() {movingBehaviours = new HashSet<>();}

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> onBoardMoves = new HashSet<>();
        for (MovingBehaviour movingBehaviour: movingBehaviours) {
            onBoardMoves.addAll(movingBehaviour.getMoves(b,objectPosition));
        }
        return onBoardMoves;
    }

    public MovingBehaviourGroup add(MovingBehaviour movingBehaviour) {
        movingBehaviours.add(movingBehaviour);
        return this;
    }

    public MovingBehaviourGroup remove(MovingBehaviour movingBehaviour) {
        movingBehaviours.remove(movingBehaviour);
        return this;
    }

    public Set<MovingBehaviour> getMovingBehaviours(){
        return movingBehaviours;
    }
}
