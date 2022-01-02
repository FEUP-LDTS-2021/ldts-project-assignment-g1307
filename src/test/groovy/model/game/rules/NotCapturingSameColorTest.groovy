package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.Move
import model.game.move.SimpleMove
import model.game.pieces.Piece
import spock.lang.Specification

class NotCapturingSameColorTest extends Specification {
    def "A positional Move in the same square as other piece of same color"() {
        given:
        def piece = Mock(Piece)
        def piece2 = Mock(Piece)

        Set<Move> set = new HashSet()
        set.add(new SimpleMove(piece,new Position(0,0)))
        piece.getMoves(_ as BoardModel) >>> [set.clone(), set.clone()]
        piece2.getPosition() >> new Position(0,0)
        piece.getPosition() >> new Position(2,2)
        'colors are both null'
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
        piece.getColor() >> Piece.COLOR.BLACK
        piece2.getColor() >> Piece.COLOR.White
        def r2 = nC.obyRule(piece)
        then:
        !r.size() && r!=null
        r2.size() == 1
    }
}
