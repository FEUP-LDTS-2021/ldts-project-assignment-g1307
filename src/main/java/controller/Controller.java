package controller;

import controller.state.ControllerState;
import controller.state.MenuState;
import view.menu.MenuView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    ControllerState<?,?> state;

    public Controller() {
        this.state = new MenuState(); // this seems a bit off ... if we wanted a new menu state we would have to make a new Controller
                                      // TODO: find solution later ... Most Pieces children have the same problem ( when using fonts )
    }

    Controller(MenuView menuView) { // For testing, dep.injection
        this.state = new MenuState(menuView);
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
