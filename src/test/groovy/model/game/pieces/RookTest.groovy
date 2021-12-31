package model.game.pieces

import model.game.Position
import spock.lang.Specification

class RookTest extends Specification {
    def "MoveToPosition"() {
        given:
        def rook = new Rook(Piece.COLOR.White ,new Position(1,1))
        when:
        def bool = rook.isMoved()
        rook.moveToPosition(new Position(2,3))
        then:
        !bool
        rook.hasMoved
    }
}
