package model.game.rules;

import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public interface Rule {
    Set<Move> obyRule(Piece pieceToFilter);
}
