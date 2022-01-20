package chess.model.game.builder;

import chess.model.game.GameModel;

public interface GameBuilder {
    GameBuilder reset();
    GameBuilder buildPieces();
    GameBuilder buildRules();
    GameModel getResults();
}
