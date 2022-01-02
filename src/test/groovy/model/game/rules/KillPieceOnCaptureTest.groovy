package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.pieces.Piece
import spock.lang.Specification

class KillPieceOnCaptureTest extends Specification {
    def "Simple capture"() {
        given:
        Piece piece1 = Mock(Piece)
        Piece piece2 = Mock(Piece)
        def gameModel = new GameModel()

        def mPiece1 = new HashSet()
        mPiece1.add(new Position(1,2))
        piece1.getMoves(_ as BoardModel) >> mPiece1

        piece2.getPosition() >> new Position(1,2)

        def set = new HashSet()
        set.add(piece1)
        set.add(piece2)
        gameModel.setPiecesInGame(set)
        gameModel.setBoardModel(new SquareBoard(8))

        def filter = new KillPieceOnCapture(gameModel)
        when:

        def r = filter.obyRule(piece1)

        assert r != null
        r[0].execute()

        then:
        piece2 == null
    }
}
