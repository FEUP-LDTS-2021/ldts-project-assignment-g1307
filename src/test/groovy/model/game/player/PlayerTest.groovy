package model.game.player

import model.game.clock.ClockModel
import model.game.pieces.Piece
import spock.lang.Specification

class PlayerTest extends Specification {
    def "SetTurn"() {
        def clock = Mock(ClockModel)
        def player = new Player(clock, Piece.COLOR.BLACK)

        when:
        player.setTurn(true)
        player.setTurn(false)
        then:
        1 * clock.resume()
        1 * clock.pause()
    }
}
