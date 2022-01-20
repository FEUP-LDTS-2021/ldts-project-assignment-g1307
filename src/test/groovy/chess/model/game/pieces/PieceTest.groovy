package chess.model.game.pieces

import chess.model.game.Position
import chess.model.game.board.BoardCase
import chess.model.game.board.BoardModel
import chess.model.game.board.SquareBoard
import chess.model.game.pieces.movingBehaviours.MovingBehaviour
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class PieceTest extends Specification {
    def "Enum Color To string"() {
        def g = Piece.COLOR.BLACK

        expect:
        g.toString() == "#565352"
    }

    def "piece test"(){
        BoardModel boardModel = new SquareBoard(8)
        def pawn = new Pawn(Piece.COLOR.White ,new Position(1,1), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH))
        Set<Position> moves = new HashSet<>()
        moves = pawn.getMovesPositions(boardModel)
        boolean check = pawn.isMoved()
        pawn.moveToPosition(new Position(2,1))
        expect:
//        moves != Collections.EMPTY_SET
        !check
        pawn.isMoved()
        pawn.getFigure() == 'o'
    }
}
