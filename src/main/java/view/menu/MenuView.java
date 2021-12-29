package view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import model.menu.MenuModel;
import view.View;

public class MenuView extends View {

    MenuView(MenuModel model){
        super(model, "square.ttf");
    }

    public static void main(String[] args) {
        View menuView = new MenuView(new MenuModel());
    }

    public void drawTitle() {

    }
    public void drawOption() {

    }

    @Override
    protected void draw() {

    }
}
