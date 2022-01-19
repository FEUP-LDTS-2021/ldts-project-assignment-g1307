package chess.controller;

import chess.controller.state.ControllerState;
import chess.controller.state.MenuState;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    ControllerState<?,?> state;

    public Controller() {
        this.state = new MenuState();
    }

    public void run() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (state == null)
                executor.shutdown();
            try {
                state = state.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 25, TimeUnit.MILLISECONDS);
    }
}
