package model.game;

import model.game.pieces.Piece;

import java.util.Set;

public interface GameBuilder {
    void reset();
    void buildPawns();
    void buildKings();
    void buildQueens();
    void buildRooks();
    void buildKnights();
    void buildBishops();
    void buildPieces();
    void buildRules();
    Set<Piece> getResults();
}
