package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
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
        Set<Position> set = new HashSet()
        set.add(new Position(4,3))
        set.add(new Position(5,3))
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
        def r = nC.obyRule(piece)
        piece.moveToPosition(new Position(1,4))
        def r2 = nC.obyRule(piece)
        then:
        r.size() == 2
        r2.size() == 1
    }
}
