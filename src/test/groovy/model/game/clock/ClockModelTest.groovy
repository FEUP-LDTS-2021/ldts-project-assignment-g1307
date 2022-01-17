package model.game.clock

import spock.lang.Specification


class ClockModelTest extends Specification{

    def "Clock pause and resume"(){
        ClockModel clock = new ClockModel(30) //30 sec clock
        boolean check1, check2;
        int checkTime;

        clock.resume()
        clock.skipTime(10) //simulates the passage of 10 seconds in the clock
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
        clock.skipTime(15)
        check = clock.hasEnded()
        clock.cancel()

        expect:
        check

    }

    def "Clock Cancel"(){
        ClockModel clock = new ClockModel(20)

        clock.resume()
        clock.skipTime(5)
        clock.cancel()

        expect:
        clock.hasEnded()
        clock.getTime() == 15
    }

    def "get remaining time without stopping"(){
        ClockModel clock = new ClockModel(20)

        clock.resume()
        clock.skipTime(5)
        int remaining = clock.getTime();
        clock.cancel();

        expect:
        remaining == 15
    }

    def "remaining time in string"(){
        ClockModel clock = new ClockModel(70)
        clock.resume()
        clock.skipTime(5)
        String remaining = clock.toString();
        clock.cancel();

        expect:
        remaining == "00:01:05"
    }

    def "simulating passage of time"(){
        ClockModel clock = new ClockModel(30)
        clock.resume()
        clock.skipTime(10)
        int remaining = clock.getTime()
        clock.cancel();

        expect:
        remaining == 20
    }

    def "Test for clock with increment"() {
        ClockModel clock = new ClockModel(30, 999999)
        clock.resume()
        clock.pause()

        expect:
        clock.getTime() > 30
    }
}
