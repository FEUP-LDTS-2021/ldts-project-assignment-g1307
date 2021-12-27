package model.game

import spock.lang.Specification

class PositionTest extends Specification {
    Position position;

    void setup() {
        position = new Position(2,3)
    }

    def "GetRow"() {
        expect:
        position.getRow() == 2
    }

    def "GetCol"() {
        expect:
        position.getCol() == 3
    }

    def "ToString"() {
        expect:
        position.toString() == "d3"
    }
}
