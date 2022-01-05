package model.game.builder;

import model.game.GameModel;

public interface GameBuilder {
    GameBuilder reset();
    GameBuilder buildPieces();
    GameBuilder buildRules();
    GameModel getResults();
}
