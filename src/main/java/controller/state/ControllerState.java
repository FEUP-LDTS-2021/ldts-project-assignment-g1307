package controller.state;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.Model;
import view.View;

import java.io.IOException;

public abstract class ControllerState<T extends Model,W extends View<T>> {
    T model;
    W view;

    ControllerState(T menuModel, W menuView) {
        this.model = menuModel;
        this.view = menuView;
    }

    protected ControllerState() {
    }

    protected void draw() throws IOException {
        view.draw();
    }

    protected ControllerState<?,?> closeIfMoving(ControllerState<?,?> nextState) throws IOException {
        if (nextState != this)
            this.getView().close();
        return nextState;
    }

    public KeyType getKey(Screen screen) throws IOException {
        KeyStroke key;
        key = screen.pollInput();

        if (key == null)
            return KeyType.Unknown;

        return key.getKeyType();
    }

    public View<?> getView() {
        return view;
    }
    public abstract ControllerState<?,?> run() throws IOException;
}
