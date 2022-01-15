package model.endGame

import model.game.clock.Clock
import model.game.clock.ClockModel
import model.game.pieces.Piece
import model.game.player.Player
import spock.lang.Specification

import java.lang.management.PlatformLoggingMXBean

class EndGameModelTest extends Specification{
    def "Game tie"(){
        given:
        Player tie = null
        Player winner = Mock(Player)

        def endGameModelTie = new EndGameModel(tie, false)
        def endGameModelWinner = new EndGameModel(winner, true)

        when:
        def r1 = endGameModelTie.tied()
        def r2 = endGameModelWinner.tied()

        then:
        r1
        !r2
    }

    def "Won by checkmate"(){
        given:
        Player winner = Mock(Player)

        def endGameModelCheckmate = new EndGameModel(winner, true)
        def endGameModelTimeEnded = new EndGameModel(winner, false)

        when:
        def r1 = endGameModelCheckmate.wonByCheckmate()
        def r2 = endGameModelTimeEnded.wonByCheckmate()

        then:
        r1
        !r2
    }
}
