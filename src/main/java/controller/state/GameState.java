package controller.state;

import model.game.GameCursor;
import model.game.GameModel;
import model.game.builder.GameBuilder;
import model.game.builder.StandardChessGame;
import view.game.GameView;

import java.io.IOException;

public class GameState extends ControllerState<GameModel,GameView>{

    public GameState() {
        this.model = new StandardChessGame().buildRules().buildPieces().getResults();
        this.view = new GameView(model);
    }

    @Override
    public ControllerState<?,?> run() throws IOException {
        draw();
        ControllerState<?,?> nextControllerState = null;
        GameCursor cursor = model.getCursor();
        switch (getKey(view.getScreen())) {
            case ArrowLeft -> { cursor.moveLeft(); nextControllerState = this;}
            case ArrowRight -> { cursor.moveRight(); nextControllerState = this;}
            case ArrowUp-> { cursor.moveUp(); nextControllerState = this;}
            case ArrowDown -> { cursor.moveDown(); nextControllerState = this;}
            case Enter -> {model.select(); nextControllerState = this;}
            case EOF -> view.getScreen().close();
            default -> nextControllerState = this;
        }
        return closeIfMoving(nextControllerState);
    }
}
