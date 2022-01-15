package model.game.clock

import spock.lang.Specification


class ClockModelTest extends Specification{

    def "Clock pause and resume"(){
        ClockModel clock = new ClockModel(30) //30 sec clock
        boolean check1, check2;
        int checkTime;

        clock.resume()
        clock.skiptime(10) //simulates the passage of 10 seconds in the clock
        clock.pause()
        check1 = clock.isPaused()
        checkTime = clock.getTime()
        clock.resume()
        check2 = clock.isPaused()
        clock.cancel()

        expect:

        check1
        checkTime == 20
        !check2

    }

    def "Clock end"(){
        ClockModel clock = new ClockModel(15)
        boolean check

        clock.resume()
        clock.skiptime(15)
        check = clock.isEnded()
        clock.cancel()

        expect:
        check

    }

    def "Clock Cancel"(){
        ClockModel clock = new ClockModel(20)

        clock.resume()
        clock.skiptime(5)
        clock.cancel()

        expect:
        clock.isEnded()
        clock.getTime() == 15
    }

    def "get remaining time without stopping"(){
        ClockModel clock = new ClockModel(20)

        clock.resume()
        clock.skiptime(5)
        int remaining = clock.getTime();
        clock.cancel();

        expect:
        remaining == 15
    }

    def "remaining time in string"(){
        ClockModel clock = new ClockModel(70)
        clock.resume()
        clock.skiptime(5)
        String remaining = clock.toString();
        clock.cancel();

        expect:
        remaining == "00:01:05"
    }

    def "simulating passage of time"(){
        ClockModel clock = new ClockModel(30)
        clock.resume()
        clock.skiptime(10)
        int remaining = clock.getTime()
        clock.cancel();

        expect:
        remaining == 20

    }
}
