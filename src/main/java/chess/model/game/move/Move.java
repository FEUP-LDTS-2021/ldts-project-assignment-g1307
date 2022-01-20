package chess.model.game.move;

import chess.model.game.Position;
import chess.model.game.pieces.Piece;

public interface Move {
    void execute();
    Position getPosition();
    Piece getPiece();
}
