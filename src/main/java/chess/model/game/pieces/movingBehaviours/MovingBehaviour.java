package chess.model.game.pieces.movingBehaviours;

import chess.model.game.board.BoardModel;
import chess.model.game.Position;

import java.util.Set;

public interface MovingBehaviour {
    Set<Position> getMoves(BoardModel b, Position objectPosition);
}
