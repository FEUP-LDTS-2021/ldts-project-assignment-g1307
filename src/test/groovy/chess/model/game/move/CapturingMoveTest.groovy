package chess.model.game.move

import chess.model.game.Position
import chess.model.game.PositionTest
import chess.model.game.pieces.Pawn
import chess.model.game.pieces.Piece
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy
import org.junit.jupiter.api.DisplayNameGenerator
import spock.lang.Specification

class CapturingMoveTest extends Specification{
    def "execute"(){
        given:
        def piece = new Pawn(Piece.COLOR.White, new Position(0,0), new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH))

        def position = new Position(0, 1)
        def move = new SimpleMove(piece, position)
        def spy = Spy(move)
        Set<Piece> inGamePieces = new HashSet<>()
        inGamePieces.add(piece)
        def capture = new CapturingMove(piece, move, inGamePieces)

        capture.execute()

        expect:
        piece.getPosition() == position
    }
}
