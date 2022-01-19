package chess.model.game.rules

import chess.model.game.Position
import chess.model.game.board.BoardModel
import chess.model.game.move.Move
import chess.model.game.move.SimpleMove
import chess.model.game.pieces.Piece
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


        piece.getColor() >>> [Piece.COLOR.BLACK, Piece.COLOR.BLACK]
        piece2.getColor() >>> [Piece.COLOR.BLACK, Piece.COLOR.White]
        def s = new HashSet()
        s.add(piece)
        s.add(piece2)

        def nC = new NotCapturingSameColor(s)

        when:
        Set<Move> r = piece.getMoves(_ as BoardModel)
        nC.obyRule(r, piece)
        set.add(new SimpleMove(piece,new Position(0,6)))
        Set<Move> r2 = set
        nC.obyRule(r2,piece)
        then:
        !r.size() && r!=null
        r2.size() == 2
    }
}
