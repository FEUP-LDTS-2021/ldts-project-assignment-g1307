package model.game.clock;

import model.Model;

import java.util.Timer;
import java.util.TimerTask;

public class ClockModel implements Model, Clock{

    private int time;
    private final int increment;
    private Timer timer;

    private boolean ended;
    private boolean paused;

    public ClockModel(int time, int increment){
        this.time = time;
        this.increment = increment;
        timer = null;
        ended = false;
        paused = true;
    }

    public ClockModel(int time){
        this.time = time;
        this.increment = 0;
        timer = null;
        ended = false;
        paused = true;
    }


    public boolean hasEnded() {
        return ended;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getTime() {
        return time;
    }

    @Override
    public void pause() {
        if (timer == null) return;
        timer.cancel();
        paused = true;
    }

    @Override
    public void resume() {
        if(!ended){
            time += increment;
            paused = false;
            this.timer = new Timer();
            TimerTask task = new TimerTask(){
                @Override
                public void run(){
                    time--;
                    if(time <= 0) {
                        ended = true;
                        timer.cancel();
                    }
                }
            };
            this.timer.scheduleAtFixedRate(task, 1000, 1000);
        }
    }

    @Override
    public void cancel() {
        pause();
        ended = true;
    }

    @Override
    public String toString(){
        int sec, min, hour, aux;
        aux = time;
        hour = aux / 3600;
        aux = aux % 3600;
        min = aux/60;
        aux = aux%60;
        sec = aux;
        return String.format("%02d:%02d:%02d", hour, min, sec);

    }

    @Override
    public void skipTime(int time) {
        this.time = this.time - time;
        if(this.time <=0) cancel();
    }
}
