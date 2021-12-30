package model.game.pieces.movingBehaviours;

import model.game.board.BoardModel;
import model.game.Position;

import java.util.Set;

public interface MovingBehaviour {
    Set<Position> getMoves(BoardModel b, Position objectPosition);
}
