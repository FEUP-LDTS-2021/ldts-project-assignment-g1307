package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.move.Move
import model.game.pieces.King
import model.game.pieces.Piece
import spock.lang.Specification

class NoSuicideAllowedTest extends Specification {
    def "Move of King is the same as other Piece"() {
        given:
        Piece piece = Mock(Piece)
        King king = Mock(King)

        Move move = Mock(Move)
        Position position = Mock(Position)
        move.getPosition() >> position

        def sMoves = new HashSet()
        sMoves.add(move)

        piece.getMoves(_ as BoardModel) >> sMoves
        king.getMoves(_ as BoardModel) >> sMoves


        def gameModel = Stub(GameModel) {
            setCheck: king.inCheck()>>true
        }

        def piecesGame = new HashSet()
        piecesGame.add(piece)
        piecesGame.add(king)

        gameModel.setPiecesInGame(piecesGame)

        def rule = new NoSuicideAllowed(gameModel)
        when:

        rule.obyRule(sMoves, king)

        then:

        sMoves.isEmpty()

    }
}
