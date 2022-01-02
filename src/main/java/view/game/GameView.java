package view.game;

import model.game.GameModel;
import view.View;

import java.io.IOException;

public class GameView extends View<GameModel> {
    public GameView(GameModel model) {
        super(model,"CHEQ_TT.TTF");
    }

    @Override
    public void draw() throws IOException {

    }
}
