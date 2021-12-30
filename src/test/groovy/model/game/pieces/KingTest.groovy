package model.game.pieces

import model.game.Position
import spock.lang.Specification

class KingTest extends Specification {
    def "MoveToPosition"() {
        given:
        def king = new King(Piece.COLOR.White ,new Position(1,1))
        when:
        def bool = king.isMoved()
        king.moveToPosition(new Position(2,3))
        then:
        !bool
        king.hasMoved
    }
}
