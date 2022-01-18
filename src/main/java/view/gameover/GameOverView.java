package view.gameover;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.gameover.GameOverModel;
import view.View;

import java.io.IOException;

public class GameOverView extends View<GameOverModel> {

    public GameOverView(GameOverModel model){ super(model, "square.ttf", 25, 25, 50); }

    GameOverView(GameOverModel model, Screen screen, TextGraphics textGraphics) {
        super(model,"square.ttf", screen, textGraphics);
    }

    public void drawGameOverMessage() {
        String gameOverMessage = model.gameOverMessage();

        TerminalPosition stringPos = new TerminalPosition(getWidthCenter() - gameOverMessage.length() / 2, getHeightCenter());
        graphics.setForegroundColor(TextColor.Factory.fromString("#4f763a"));
        graphics.putString(stringPos, gameOverMessage);
    }

    @Override
    public void draw() throws IOException {
        clear();

        drawGameOverMessage();

        refresh();
    }
}
