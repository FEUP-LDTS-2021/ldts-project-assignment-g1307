package controller;

import controller.state.ControllerState;
import controller.state.MenuState;
import model.menu.MenuModel;
import view.menu.MenuView;

import java.io.IOException;

public class Controller { // a command might me needed ... mouse is not available in lanterna
    ControllerState state;

    public Controller() {
        MenuModel model = new MenuModel();
        this.state = new MenuState(model,new MenuView(model));
    }

    Controller(MenuModel menuModel, MenuView menuView) { // For testing, dep.injection
        this.state = new MenuState(menuModel,menuView);
    }

    public void run() {

    }
}
