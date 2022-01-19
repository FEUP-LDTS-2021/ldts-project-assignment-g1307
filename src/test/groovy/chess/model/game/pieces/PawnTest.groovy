package chess.model.game.pieces

import chess.model.game.Position
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class PawnTest extends Specification {
    def "MoveToPosition"() {
        given:
        def pawn = new Pawn(Piece.COLOR.White ,new Position(1,1), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH))
        when:
        def bool = pawn.isMoved()
        pawn.moveToPosition(new Position(2,3))
        then:
        !bool
        pawn.hasMoved
    }
}
