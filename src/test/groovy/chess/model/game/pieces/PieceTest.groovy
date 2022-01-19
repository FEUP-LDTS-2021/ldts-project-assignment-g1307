package chess.model.game.pieces

import spock.lang.Specification

class PieceTest extends Specification {
    def "Enum Color To string"() {
        def g = Piece.COLOR.BLACK

        expect:
        g.toString() == "#565352"
    }
}
