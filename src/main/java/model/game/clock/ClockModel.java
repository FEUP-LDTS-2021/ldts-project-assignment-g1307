package model.game.clock;

import model.Model;

import java.util.Timer;
import java.util.TimerTask;

public class ClockModel implements Model, Clock{

    private int time;
    private Timer timer;

    private boolean ended;
    private boolean paused;


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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

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

    public ClockModel(int time){
        this.time = time;
        ended = false;
        paused = true;
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
    public void skiptime(int time) {
        this.time = this.time - time;
        if(this.time <=0) cancel();
    }
}
