package chess.controller.state;

import chess.model.menu.MenuModel;
import chess.view.menu.MenuView;

import java.io.IOException;

public class MenuState extends ControllerState<MenuModel,MenuView> {

    public MenuState(MenuView menuView) {
        super((MenuModel) menuView.getModel(), menuView);
    }

    public MenuState() {
        this.model = new MenuModel();
        this.view = new MenuView(model);
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
                    case BULLET -> nextControllerState = new GameState(60, 0);
                    case BLITZ -> nextControllerState = new GameState(300, 3);
                    case CLASSICAL -> nextControllerState = new GameState(3600, 0);
                    case RAPID -> nextControllerState = new GameState(600, 0);
                    case EXIT -> {
                    }
                }
            }
            case EOF -> view.getScreen().close();
            default -> nextControllerState = this;
        }
        return closeIfMoving(nextControllerState);
    }
}
