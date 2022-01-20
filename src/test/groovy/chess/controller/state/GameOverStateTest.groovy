package chess.controller.state

import chess.model.game.GameModel
import chess.view.game.GameView
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import chess.model.gameover.GameOverModel
import spock.lang.Specification
import chess.view.gameover.GameOverView

class GameOverStateTest extends Specification{
    def "Close game over window"(){
        given:
        def gameOverView = Mock(GameOverView)
        def gameOverModel = Mock(GameOverModel)
        GameOverState gameOverState = new GameOverState(gameOverView)
        def spy = Spy(gameOverState)
        spy.view = gameOverView
        spy.model = gameOverModel
        def screen = Mock(Screen)
        spy.getKey(_ as Screen) >>> [KeyType.Unknown, KeyType.EOF]
        gameOverView.getScreen() >> screen
        gameOverView.close() >> null
        when:
        spy.run()
        spy.run()
        then:
        2 * spy.draw()
        1 * screen.close()
    }
    def "run return"(){
        def gameOverView = Mock(GameOverView)
        def gameOverModel = Mock(GameOverModel)
        GameOverState gameOverState = new GameOverState(gameOverView)
        def screen = Mock(Screen)
        gameOverState.view = gameOverView
        gameOverState.model = gameOverModel
        gameOverView.getScreen() >> screen

        expect:
        gameOverState.run() != null

    }
}
