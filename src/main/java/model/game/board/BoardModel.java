package model.game.board;

import model.Model;
import model.game.Position;

import java.util.List;

public interface BoardModel extends Model {
    boolean positionInBoard(Position position);
    int getRows();
    int getColumns();
    List<BoardCase> getCases();
}
