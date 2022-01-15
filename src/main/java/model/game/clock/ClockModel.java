package model.game.clock;

import model.Model;
import model.game.GameSubscriber;

import java.util.Timer;

public class ClockModel implements Model, Clock{
    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private boolean ended;
    private boolean paused;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private int time;
    private Timer timer;

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void cancel() {

    }

    public ClockModel(int time){

    }
}
