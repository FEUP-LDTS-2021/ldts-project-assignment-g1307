package chess.model.game.rules;

import chess.model.game.move.Move;
import chess.model.game.pieces.Piece;

import java.util.Set;

public interface Rule {
    void obyRule(Set<Move> movesToFilter, Piece piece);
}
