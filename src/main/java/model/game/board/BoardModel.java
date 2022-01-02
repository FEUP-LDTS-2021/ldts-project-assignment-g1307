package model.game.board;

import model.Model;
import model.game.Position;

import java.util.Set;

public interface BoardModel extends Model {
    boolean positionInBoard(Position position);
    Set<BoardCase> getCases();
}
