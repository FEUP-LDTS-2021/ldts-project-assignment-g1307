package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.Set;

public interface MovingBehaviour {
    public Set<Position> getMoves(BoardModel b, Position objectPosition);
}
