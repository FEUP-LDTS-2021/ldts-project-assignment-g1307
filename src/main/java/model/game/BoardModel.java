package model.game;

import model.Model;

public interface BoardModel extends Model {
    boolean positionInBoard(Position position);
}
