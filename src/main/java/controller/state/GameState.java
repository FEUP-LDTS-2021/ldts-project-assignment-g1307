package controller.state;

import model.game.GameModel;
import model.game.builder.GameBuilder;
import model.game.builder.StandardChessGame;
import view.game.GameView;

import java.io.IOException;

public class GameState extends ControllerState<GameModel,GameView>{

    public GameState(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
    }

    public GameState() {
        this.model = new StandardChessGame().buildRules().buildPieces().getResults();
        this.view = new GameView(model);
    }

    @Override
    public ControllerState<?,?> run() throws IOException {
        draw();
        return this;
    }
}
