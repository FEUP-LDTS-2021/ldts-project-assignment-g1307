package view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.menu.MenuModel;
import view.View;

import java.io.IOException;

public class MenuView extends View {

    MenuView(MenuModel model){
        super(model, "square.ttf"); // TODO : we could pass the font as an argument too
    }

    MenuView(MenuModel model, Screen screen, TextGraphics textGraphics) {
        super(model,"square.ttf", screen, textGraphics);
    }

    public static void main(String[] args) {
        View menuView = new MenuView(new MenuModel());
        try {
            menuView.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTitle() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#312e2b"));
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
        TerminalPosition terminalPosition = new TerminalPosition(getWidthCenter() - 3,getHeightCenter() + 3);
        TerminalSize terminalSize = new TerminalSize(6, 4);
        graphics.drawRectangle(terminalPosition,terminalSize, '-');
    }

    @Override
    public void draw() throws IOException {

        clear();

        drawOption();
        drawTitle();

        refresh();
    }
}
