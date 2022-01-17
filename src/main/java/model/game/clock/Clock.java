package model.game.clock;

public interface Clock {
    void pause();
    void resume();
    void cancel();
    @Override String toString();
    void skipTime(int time);
    boolean hasEnded();
}
