package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.Move
import model.game.move.SimpleMove
import model.game.pieces.Bishop
import model.game.pieces.King
import model.game.pieces.Pawn
import model.game.pieces.Piece
import model.game.pieces.Rook
import model.game.pieces.movingBehaviours.DiagonalStrategy
import model.game.pieces.movingBehaviours.SideStrategyTest
import model.game.player.Player
import spock.lang.Specification

class ProtectKingRuleTest extends Specification {
    def "ProtectingKingRule"() {
        given:
        def king = Mock(King)
        king.getColor() >> Piece.COLOR.BLACK
        def pawn = Mock(Pawn)
        pawn.getColor() >> Piece.COLOR.BLACK

        Set<Move> set = new HashSet()
        def pawnMove = Mock(SimpleMove)

        set.add(pawnMove)
        pawn.getMoves(_ as BoardModel) >> set

        def s = new HashSet()
        s.add(king)
        s.add(pawn)

        def gameModel = Stub(GameModel) {
            setCheck: king.inCheck() >>> [false,true]
            getPiecesInGame() >> s
        }

        def nC = new ProtectKingRule(gameModel)

        when:
        Set<Move> r = pawn.getMoves(gameModel.getBoardModel())
        nC.obyRule(r, pawn)
        def result = r.size()

        then:
        result == 0
    }
}
