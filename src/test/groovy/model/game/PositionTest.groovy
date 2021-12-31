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

    def "Add"() {
        given:
        def pos = new Position(1,2)
        def toAdd = new Position(1,3)
        when:
        def added = pos.add(toAdd)
        then:
        added == new Position(2,5)
    }

    def "mull"() {
        given:
        def pos = new Position(1,2)
        def toAdd = new Position(1,3)
        when:
        def added = pos.mull(toAdd)
        then:
        added == new Position(1,6)
    }

}
