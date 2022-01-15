package model.game.clock;

import model.Model;
import model.game.GameSubscriber;

import java.util.Timer;
import java.util.TimerTask;

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
        timer.cancel();
        paused = true;
    }

    @Override
    public void resume() {
        if(!ended){
            paused = false;
            this.timer = new Timer();
            TimerTask task = new TimerTask(){
                @Override
                public void run(){
                    //view method should collect remaining time at this point in time
                    time--;
                    if(time <= 0) {
                        ended = true;
                        timer.cancel();
                        //should call method to end the game
                    }
                }
            };
            this.timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }

    @Override
    public void cancel() {
        pause();
        ended = true;
    }

    public ClockModel(int time){
        this.time = time;
        ended = false;
        paused = true;
    }

    @Override
    public String toString(){
    }
}
