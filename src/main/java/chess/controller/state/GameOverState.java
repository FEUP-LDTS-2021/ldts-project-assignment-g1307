package chess.controller.state;

import chess.model.gameover.GameOverModel;
import chess.view.gameover.GameOverView;

import java.io.IOException;

public class GameOverState extends ControllerState<GameOverModel, GameOverView>{

    public GameOverState(GameOverView gameOverView){ super((GameOverModel) gameOverView.getModel(), gameOverView);}

    public GameOverState(GameOverModel gameOverModel) {
        this.model = gameOverModel;
        this.view = new GameOverView(model);
    }

    @Override
    public ControllerState<?,?> run() throws IOException {
        draw();
        ControllerState<?,?> nextControllerState = null;
        switch (getKey(view.getScreen())) {
            case EOF -> view.getScreen().close();
            case Unknown -> nextControllerState = this;
            default -> {nextControllerState = new MenuState();
                view.getScreen().close();}

        }

        return closeIfMoving(nextControllerState);
    }
}
