package model.game.move;

import model.game.Position;
import model.game.pieces.Piece;

public interface Move {
    void execute();
    Position getPosition();
    Piece getPiece();
}
