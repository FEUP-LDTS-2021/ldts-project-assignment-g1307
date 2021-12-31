package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.pieces.Piece
import spock.lang.Specification

class NotCapturingSameColorTest extends Specification {
    def "A positional Move in the same square as other piece"() {
        given:
        def piece = Mock(Piece)
        def piece2 = Mock(Piece)

        piece.getMoves(_ as BoardModel) >> new Position(0,0)
        piece2.getPosition() >> new Position(0,0)
        piece.getPosition() >> new Position(2,2)

        def s = new HashSet()
        s.add(piece)
        s.add(piece2)

        BoardModel boardModel = Mock(SquareBoard)

        boardModel.positionInBoard(_ as Position) >> true

        GameModel gameModel = new GameModel()
        gameModel.setBoardModel(boardModel)
        gameModel.setPiecesInGame(s)
        def nC = new NotCapturingSameColor(gameModel)

        when:
        def r = nC.obyRule(piece)

        then:
        !r && r!=null
    }
}
