package model.game.builder;

import model.game.pieces.Piece;

import java.util.Set;

//this interface should build and return a Game
//See more here... https://refactoring.guru/design-patterns/builder
// for now it only does the pieces related stuff
public interface GameBuilder {
    void reset();
    void buildPieces();
    void buildRules();
    Set<Piece> getResults();
}
