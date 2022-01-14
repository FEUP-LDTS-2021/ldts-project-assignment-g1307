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
import spock.lang.Specification

class ProtectKingRuleTest extends Specification {
    def "ProtectingKingRule"() {
        given:
        def king = Mock(King)
        king.getColor() >> Piece.COLOR.BLACK
        def pawn = Mock(Pawn)
        pawn.getColor() >> Piece.COLOR.BLACK
        def enemyRook = Mock(Rook)
        enemyRook.getColor() >> Piece.COLOR.White

        king.getPosition() >> new Position(3,1)
        pawn.getPosition() >> new Position(3,2)
        enemyRook.getPosition() >> new Position(3,8)

        Set<Move> set = new HashSet()
        def rookMove = Mock(SimpleMove)
        def pawnMove = Mock(SimpleMove)

        rookMove.getPosition() >> king.getPosition()
        set.add(rookMove)
        enemyRook.getMoves(_ as BoardModel) >> set
        set.clear()
        pawnMove.getPosition() >> new Position(4,2)
        set.add(pawnMove)
        pawn.getMoves(_ as BoardModel) >> set

        def s = new HashSet()
        s.add(King)
        s.add(pawn)
        s.add(enemyRook)

        BoardModel boardModel = Mock(SquareBoard)

        boardModel.positionInBoard(_ as Position) >> true

        GameModel gameModel = new GameModel()
        gameModel.setBoardModel(boardModel)
        gameModel.setPiecesInGame(s)
        def nC = new ProtectKingRule(gameModel)

        when:
        Set<Move> r = pawn.getMoves(gameModel.getBoardModel())
        nC.obyRule(r, pawn)

        then:
        r.size() == 0
    }
}
