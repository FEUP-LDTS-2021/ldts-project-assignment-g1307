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

        piece.getColor() >> Piece.COLOR.BLACK
        king.getColor() >> Piece.COLOR.White

        Move move = Mock(Move)
        Position position = Mock(Position)
        move.getPosition() >> position
        move.getPiece() >> king

        Move move2 = Mock(Move)
        move2.getPosition() >> position

        Position piecePos = Mock(Position)
        piece.getPosition() >> piecePos
        king.getPosition() >> position
        position.equals(_) >> true
        piecePos.equals(_) >> false

        def sKingMoves = new HashSet()
        sKingMoves.add(move)

        def sPieceMove = new HashSet()
        sPieceMove.add(move2)

        piece.getMoves(_ as BoardModel) >> sPieceMove
        king.getMoves(_ as BoardModel) >> sKingMoves


        def gameModel = Stub(GameModel)

        def piecesGame = new HashSet()
        piecesGame.add(piece)
        piecesGame.add(king)

        gameModel.getPiecesInGame() >> piecesGame

        def rules = new HashSet()
        def rule = new NoSuicideAllowed(gameModel.getPiecesInGame(),gameModel.getRules(),gameModel.getBoardModel())
        def mockedRule = Mock(Castle)
        rules.add(rule)
        rules.add(mockedRule)

        mockedRule.obyRule(_ as Set<Move>, _ as Piece) >> null

        gameModel.getRules() >> rules

        when:

        rule.obyRule(sKingMoves, king)

        then:

        sKingMoves.isEmpty()

    }
}
