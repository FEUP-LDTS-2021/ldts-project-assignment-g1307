package chess.model.game.rules

import chess.model.game.GameModel
import chess.model.game.Position
import chess.model.game.board.SquareBoard
import chess.model.game.move.Move
import chess.model.game.pieces.Pawn
import chess.model.game.pieces.Piece
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class EnPassantTest extends Specification {
    def "A simple en Passant move"() {
        given:
        Pawn bPawn = new Pawn(Piece.COLOR.BLACK, new Position(2,2), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.SOUTH))
        Pawn wPawn = new Pawn(Piece.COLOR.White, new Position(4,3), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH))
        def gameModel = new GameModel()

        def set = new HashSet()
        set.add(bPawn)
        set.add(wPawn)
        gameModel.setPiecesInGame(set)
        gameModel.setBoardModel(new SquareBoard(8))

        def filter = new EnPassant(gameModel.getPiecesInGame())
        when:
        bPawn.moveToPosition(new Position(4,2))

        Set<Move> r = wPawn.getMoves(gameModel.getBoardModel())
        filter.obyRule(r, wPawn)
        then:
        r.size() == 3
    }
}
