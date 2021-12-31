package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.pieces.Pawn
import model.game.pieces.Piece
import model.game.pieces.movingBehaviours.TwoAndOneStrategy
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

        def filter = new EnPassant(gameModel)
        when:
        bPawn.moveToPosition(new Position(4,2))

        def r = filter.obyRule(wPawn)
        then:
        r.contains(new Position(3,2))
    }
}
