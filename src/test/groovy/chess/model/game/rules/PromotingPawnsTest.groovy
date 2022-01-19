package chess.model.game.rules

import chess.model.game.GameModel
import chess.model.game.Position
import chess.model.game.board.BoardModel
import chess.model.game.board.SquareBoard
import chess.model.game.move.Move
import chess.model.game.move.SimpleMove
import chess.model.game.pieces.Pawn
import chess.model.game.pieces.Queen
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class PromotingPawnsTest extends Specification {
    def "A promotion"() {
        given:
        def piece = Mock(Pawn)
        def set = new HashSet()
        set.add(piece)

        piece.getPosition() >> new Position(2,5)
        piece.getMovingBehaviour() >> new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)
        Set<Move> moves = new HashSet<>()
        moves.add(new SimpleMove(piece, new Position(1,5)))
        piece.getMoves(_ as BoardModel) >> moves
        piece.isMoved() >> true

        def g = new GameModel()
        g.setPiecesInGame(set)
        g.setBoardModel(new SquareBoard(8))

        def f = new PromotingPawns(g.getPiecesInGame(), g.getBoardModel() as SquareBoard)
        when:
        Set<Move> move = piece.getMoves(Mock(BoardModel))
        f.obyRule(move, piece)
        assert move!=null
        move[0].execute()
        then:
        g.getPiecesInGame()[0].class == Queen // we will only do a queen promotion for now

    }
}
