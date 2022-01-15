package model.gameover


import model.game.player.Player
import spock.lang.Specification

class EndGameModelTest extends Specification{
    def "Game tie"(){
        given:
        Player tie = null
        Player winner = Mock(Player)

        def endGameModelTie = new GameOverModel(tie, false)
        def endGameModelWinner = new GameOverModel(winner, true)

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

        def endGameModelCheckmate = new GameOverModel(winner, true)
        def endGameModelTimeEnded = new GameOverModel(winner, false)

        when:
        def r1 = endGameModelCheckmate.wonByCheckmate()
        def r2 = endGameModelTimeEnded.wonByCheckmate()

        then:
        r1
        !r2
    }
}
