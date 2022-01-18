package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.Move
import model.game.pieces.Pawn
import model.game.pieces.Piece
import model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class PawnsDiagonalCapturingTest extends Specification {
    def "A simple Pawn Capture"() {
        given:
        def piece = Mock(Pawn)
        def piece2 = Mock(Pawn)

        piece2.getPosition() >> new Position(2,2)
        piece.getPosition() >> new Position(3,3)

        piece.getColor() >> Piece.COLOR.White
        piece.getMovingBehaviour() >> new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)
        Set<Move> set = new HashSet()
        piece.getMoves(_ as BoardModel) >> set
        piece2.getColor() >> Piece.COLOR.BLACK

        def s = new HashSet()
        s.add(piece)
        s.add(piece2)

        BoardModel boardModel = Mock(SquareBoard)

        boardModel.positionInBoard(_ as Position) >> true

        GameModel gameModel = new GameModel()
        gameModel.setBoardModel(boardModel)
        gameModel.setPiecesInGame(s)
        def nC = new PawnsDiagonalCapturing(gameModel.getPiecesInGame())

        when:
        Set<Move> r = piece.getMoves(gameModel.getBoardModel())
        nC.obyRule(r, piece)
        then:
        r.size() == 1
    }
}
