package model.game;

import model.game.pieces.Piece;

import java.util.Set;

public interface GameBuilder {
    void reset();
    void buildPieces();
    void buildRules();
    Set<Piece> getResults();
}
