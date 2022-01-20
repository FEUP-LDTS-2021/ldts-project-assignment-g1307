package chess.model.game.board;

import chess.model.Model;
import chess.model.game.Position;

import java.util.List;

public interface BoardModel extends Model {
    boolean positionInBoard(Position position);
    List<BoardCase> getCases();
}
