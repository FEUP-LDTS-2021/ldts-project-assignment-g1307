package controller.state;

import model.menu.MenuModel;
import view.menu.MenuView;

import java.io.IOException;

public class MenuState extends ControllerState<MenuModel,MenuView> {

    public MenuState(MenuView menuView) {
        super((MenuModel) menuView.getModel(), menuView);
    }

    public MenuState() {
        this.model = new MenuModel();
        this.view = new MenuView(model); // menuView MUST HAVE THE SAME MODEL -- this seems off too
    }

    @Override
    public ControllerState<?,?> run() throws IOException {
        draw();
        ControllerState<?,?> nextControllerState = null;
        switch (getKey(view.getScreen())) {
            case ArrowLeft -> {model.setPreviousOption(); nextControllerState = this;}
            case ArrowRight -> {model.setNextOption(); nextControllerState = this;}
            case Enter -> {
                switch (model.getCurrentOption()) {
                    case NEWGAME -> nextControllerState = new GameState();// TODO: CHANGE THIS
                    case EXIT -> view.close();
                }
            }
            case EOF -> view.getScreen().close();
            default -> nextControllerState = this;
        }
        return closeIfMoving(nextControllerState);
    }
}
