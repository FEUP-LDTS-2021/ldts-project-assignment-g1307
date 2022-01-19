package chess.model.game.player

import chess.model.game.clock.ClockModel
import chess.model.game.pieces.Piece
import spock.lang.Specification

class PlayerTest extends Specification {
    def "SetTurn"() {
        given:
        def clock = Mock(ClockModel)
        def player = new Player(clock, Piece.COLOR.BLACK)

        when:
        player.setTurn(true)
        then:
        1 * clock.resume()

        when:
        player.setTurn(false)
        then:
        1 * clock.pause()

        expect:
        player.getClock() != null
        player.getColor() != null
    }
}
