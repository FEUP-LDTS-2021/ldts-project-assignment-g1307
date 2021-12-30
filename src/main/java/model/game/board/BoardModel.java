package model.game.board;

import model.Model;
import model.game.Position;

public interface BoardModel extends Model {
    boolean positionInBoard(Position position);
}
