package controller.state;

import model.menu.MenuModel;
import view.menu.MenuView;

import java.io.IOException;

public class MenuState extends ControllerState {

    public MenuState(MenuModel menuModel, MenuView menuView) {
        super(menuModel, menuView);
    }

    @Override
    public ControllerState run() throws IOException {

        return null;
    }
}
