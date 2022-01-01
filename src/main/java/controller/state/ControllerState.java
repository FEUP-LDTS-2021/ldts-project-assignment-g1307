package controller.state;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.menu.MenuModel;
import view.View;
import view.menu.MenuView;

import java.io.IOException;

public abstract class ControllerState {
    MenuModel model;
    MenuView view;

    ControllerState(MenuModel menuModel, MenuView menuView) {
        this.model = menuModel;
        this.view = menuView;
    }

    protected void draw() {

    }


    public KeyType getKey(Screen screen) throws IOException {
        return null;
    }

    public View<?> getView() {
        return view;
    }
    public abstract ControllerState run() throws IOException;
}
