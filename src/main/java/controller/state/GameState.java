package controller.state;

import model.game.GameCursor;
import model.game.GameModel;
import model.game.builder.StandardChessGame;
import model.gameover.GameOverModel;
import view.game.GameView;

import java.io.IOException;

public class GameState extends ControllerState<GameModel,GameView>{

    public GameState(int time, int increment) {
        this.model = new StandardChessGame(time, increment).buildRules().buildPieces().getResults();
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
            case Backspace -> nextControllerState = new MenuState();
            case Enter -> {model.select(); nextControllerState = this;}
            case EOF -> view.getScreen().close();
            default -> nextControllerState = this;
        }

        if (model.gameEnded())
            nextControllerState = new GameOverState(new GameOverModel( model.winner() , model.checkMate()));

        return closeIfMoving(nextControllerState);
    }
}
