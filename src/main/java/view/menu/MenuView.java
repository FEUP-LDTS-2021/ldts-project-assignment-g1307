package view.menu;

import com.googlecode.lanterna.TerminalPosition;
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


    }
    public void drawOption() {
        //graphics.drawRectangle()
    }

    @Override
    public void draw() throws IOException {


    }
}
