package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.SimpleMove
import model.game.pieces.Piece
import spock.lang.Specification

class NoStepOverPieceTest extends Specification {
    def "A Piece that can't step over another"() {
        given: 'A piece that can go to the same position of piece2 and another right after'
        Piece piece1 = Mock(Piece)
        Piece piece2 = Mock(Piece)
        def gameModel = new GameModel()

        def mPiece1 = new HashSet()
        mPiece1.add(new SimpleMove(piece1,new Position(1,2)))
        mPiece1.add(new SimpleMove(piece1,new Position(1,3)))
        piece1.getMoves(_ as BoardModel) >> mPiece1

        piece2.getPosition() >> new Position(1,2)

        def set = new HashSet()
        set.add(piece1)
        set.add(piece2)
        gameModel.setPiecesInGame(set)
        gameModel.setBoardModel(new SquareBoard(8))

        def filter = new NoStepOverPiece(gameModel)

        when:
        def r = filter.obyRule(piece1)

        then:
        r.size() == 1

    }
}
