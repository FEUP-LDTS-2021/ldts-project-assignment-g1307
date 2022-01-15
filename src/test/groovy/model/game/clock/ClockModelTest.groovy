package model.game.clock

import spock.lang.Specification
import java.util.Timer;
import java.util.TimerTask;

class ClockModelTest extends Specification{

    def "Clock pause and resume"(){
        ClockModel clock = new ClockModel(30); //30 sec clock
        int time = 10;
        Timer timer = new Timer();

        clock.resume();
        timer.scheduleAtFixedRate(new TimerTask() { //should start a 10 sec timer and then pause the clock
            @Override
            void run() {
                time--;
                if(time <=0){
                    timer.cancel()
                    clock.pause();

                    clock.isPaused() == true; //clock should now be paused and with 20 sec remaining
                    clock.getTime() == 20;
                    clock.resume();
                };
            }
        }, 0, 1000);
    }

    def "Clock end"(){
        ClockModel clock = new ClockModel(15); //15 sec clock
        int time = 16;
        Timer timer = new Timer();


        clock.resume();
        timer.scheduleAtFixedRate(new TimerTask() { //should start a 16 sec timer and check if the clock has ended
            @Override
            void run() {
                time--;
                if(time <=0){
                    timer.cancel()
                    clock.isEnded() == true;
                    clock.getTime() == 0;
                };
            }
        }, 0, 1000);
    }

    def "Clock Cancel"(){
        ClockModel clock = new ClockModel(60); //60 sec clock
        int time = 5;
        Timer timer = new Timer();

        clock.resume();
        timer.scheduleAtFixedRate(new TimerTask() { //should start a 5 sec timer and then cancel the clock
            @Override
            void run() {
                time--;
                if(time <=0){
                    timer.cancel()
                    clock.cancel();
                    clock.isEnded() == true;
                };
            }
        }, 0, 1000);
    }

    def "get remaining time without stopping"(){
        ClockModel clock = new ClockModel(20); //20 sec clock
        int time = 5;
        Timer timer = new Timer();

        clock.resume();
        timer.scheduleAtFixedRate(new TimerTask() { //should start a 5 sec timer and then check the remaining time on the clcck;
            @Override
            void run() {
                time--;
                if(time <=0){
                    timer.cancel()
                    clock.getTime() == 15;
                };
            }
        }, 0, 1000);
    }
}
