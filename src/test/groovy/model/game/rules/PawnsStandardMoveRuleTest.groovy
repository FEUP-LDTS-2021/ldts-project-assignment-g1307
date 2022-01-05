package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.Move
import model.game.move.SimpleMove
import model.game.pieces.Pawn
import model.game.pieces.Piece
import model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class PawnsStandardMoveRuleTest extends Specification {
    def "Two pos and then one"() {
        given:
        def piece = Mock(Pawn)

        piece.getPosition() >> new Position(3,3)

        piece.getColor() >> Piece.COLOR.White
        piece.getMovingBehaviour() >> new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)
        Set<Move> set = new HashSet()
        set.add(new SimpleMove(piece ,new Position(2,3)))
        set.add(new SimpleMove(piece ,new Position(1,3)))
        piece.getMoves(_ as BoardModel) >> set

        def s = new HashSet()
        s.add(piece)

        BoardModel boardModel = Mock(SquareBoard)

        boardModel.positionInBoard(_ as Position) >> true

        GameModel gameModel = new GameModel()
        gameModel.setBoardModel(boardModel)
        gameModel.setPiecesInGame(s)
        def nC = new PawnsStandardMoveRule(gameModel)

        when:
        Set<Move> r = piece.getMoves(gameModel.getBoardModel())
        nC.obyRule(r, piece)
        def ls = r.size()
        piece.isMoved() >> true
        nC.obyRule(r, piece)
        def rs = r.size()

        then:
        ls == 2
        rs == 1
    }
}
