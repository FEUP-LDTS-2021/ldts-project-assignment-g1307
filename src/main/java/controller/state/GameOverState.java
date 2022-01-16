package controller.state;

import model.gameover.GameOverModel;
import view.gameover.GameOverView;

import java.io.IOException;

public class GameOverState extends ControllerState<GameOverModel, GameOverView>{

    public GameOverState(GameOverModel gameOverModel) {
        this.model = gameOverModel;
        this.view = new GameOverView(model);
    }

    @Override
    public ControllerState<?,?> run() throws IOException {
        return null;
    }
}
