package chess.view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import chess.model.menu.MenuModel;
import chess.view.View;

import java.io.IOException;

public class MenuView extends View<MenuModel> {

    public MenuView(MenuModel model){
        super(model, "square.ttf", 25, 25, 50);
    }

    MenuView(MenuModel model, Screen screen, TextGraphics textGraphics) {
        super(model,"square.ttf", screen, textGraphics);
    }

    public void drawTitle() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#4f763a"));

        String[] arrSTitle = {
        "   /$$$$$$  /$$                                  ",
        "  /$$__  $$| $$                                  ",
        " | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$$",
        " | $$      | $$__  $$ /$$__  $$ /$$_____//$$_____/",
        " | $$      | $$  \\ $$| $$$$$$$$|  $$$$$$|  $$$$$$",
        " | $$    $$| $$  | $$| $$_____/ \\____  $$\\____  $$",
        " |  $$$$$$/| $$  | $$|  $$$$$$$ /$$$$$$$//$$$$$$$/",
        "  \\______/ |__/  |__/ \\_______/|_______/|_______/"};

        int j = 2;
        for (String s : arrSTitle) {
            graphics.putString(0,j,s);
            j++;
        }

    }
    public void drawOption() {
        int colOfRectangle = MenuModel.Option.maxLength() + 2;
        int rowOfRectangle = getHeightCenter() + 3;
        TerminalPosition terminalPosition = new TerminalPosition(getWidthCenter() -colOfRectangle / 2 , rowOfRectangle);
        TerminalSize terminalSize = new TerminalSize(colOfRectangle + 2, 5);
        graphics.drawRectangle(terminalPosition,terminalSize, '-');

        for ( MenuModel.Option opt : MenuModel.Option.values()) {
            int offSetToStaticCursor =  (colOfRectangle + 2) * opt.diffToOption(model.getCurrentOption());
            int insideBoxCol = getWidthCenter() - colOfRectangle/2;
            int colToWrite = insideBoxCol -offSetToStaticCursor + 2;
            TerminalPosition stringPos = new TerminalPosition(colToWrite, rowOfRectangle + 2);
            graphics.putString(stringPos, opt.toString());
        }
    }

    @Override
    public void draw() throws IOException {

        clear();

        drawOption();
        drawTitle();

        refresh();
    }

}
