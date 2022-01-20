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

    def "advancedTwo"(){
        def pawn = new Pawn(Piece.COLOR.White ,new Position(1,1), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH))
        def pawn2 = new Pawn(Piece.COLOR.White ,new Position(1,2), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH))
        boolean check = pawn.hasAdvancedTwo()
        pawn.moveToPosition(new Position(3, 1))
        pawn2.moveToPosition(new Position(2, 2))
        expect:
        !check
        pawn.hasAdvancedTwo()
        !pawn2.hasAdvancedTwo()
    }
}
