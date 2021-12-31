package model.game.rules;

import model.game.Position;
import model.game.pieces.Piece;

import java.util.Set;

public interface Rule {
    Set<Position> obyRule(Piece pieceToFilter);
}
