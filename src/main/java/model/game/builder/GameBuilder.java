package model.game.builder;

import model.game.GameModel;

//this interface should build and return a Game
//See more here... https://refactoring.guru/design-patterns/builder
// for now it only does the pieces related stuff
public interface GameBuilder {
    GameBuilder reset();
    GameBuilder buildPieces();
    GameBuilder buildRules();
    GameModel getResults();
}
