package model.game.clock;

import model.Model;
import model.game.GameSubscriber;

import java.util.Timer;

public class ClockModel implements Model, Clock{
    private boolean ended;
    private boolean paused;
    private int time;
    private Timer timer;

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public ClockModel(int time){

    }
}
