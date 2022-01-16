package view.gameover;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jdk.jshell.spi.ExecutionControl;
import model.gameover.GameOverModel;
import view.View;

import java.io.IOException;

public class GameOverView extends View<GameOverModel> {

    public GameOverView(GameOverModel model){ super(model, "square.ttf", 25, 25, 50); }

    GameOverView(GameOverModel model, Screen screen, TextGraphics textGraphics) {
        super(model,"square.ttf", screen, textGraphics);
    }

    public void drawGameOverMessage() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

    @Override
    public void clear(){
    }

    @Override
    public void draw() throws IOException {
    }
}
