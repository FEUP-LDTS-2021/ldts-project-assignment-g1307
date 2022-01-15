package model.game.clock

import spock.lang.Specification
import java.util.concurrent.TimeUnit

class ClockModelTest extends Specification{

    def "Clock pause and resume"(){
        ClockModel clock = new ClockModel(30) //30 sec clock

        clock.resume()
        TimeUnit.SECONDS.sleep(10) //code sleeps for 10 seconds
        clock.pause()

        expect:
        clock.isPaused() == true
        clock.getTime() == 19 //1 sec dellay

        clock.resume();
        clock.isPaused() == false;
    }

    def "Clock end"(){
        ClockModel clock = new ClockModel(15) //15 sec clock

        clock.resume()
        TimeUnit.SECONDS.sleep(16) //stops code for 15 sec

        expect:
        clock.isEnded() == true
    }

    def "Clock Cancel"(){
        ClockModel clock = new ClockModel(20) //20 sec clock

        clock.resume()
        TimeUnit.SECONDS.sleep(5)
        clock.cancel()

        expect:
        clock.isEnded() == true
        clock.getTime() == 14
    }

    def "get remaining time without stopping"(){
        ClockModel clock = new ClockModel(20) //20 sec clock

        clock.resume()
        TimeUnit.SECONDS.sleep(5)
        int remaining = clock.getTime();

        expect:
        remaining == 14;
    }

    def "remaining time in string"(){
        ClockModel clock = new ClockModel(70)
        clock.resume()
        TimeUnit.SECONDS.sleep(5)
        String remaining = clock.toString();

        expect:
        remaining == "00:01:04"
    }

    def "simulating passage of time"(){

    }
}
