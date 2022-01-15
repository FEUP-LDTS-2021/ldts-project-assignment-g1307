package model.game.clock;

import java.util.Timer;

public interface Clock {
    public void pause();
    public void resume();
    public void cancel();
    public String toString();
    public void skiptime(int time);
}
