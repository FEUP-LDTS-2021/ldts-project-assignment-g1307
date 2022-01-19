package chess.view.gameover

import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import chess.model.gameover.GameOverModel
import spock.lang.Specification


class GameOverViewTest extends Specification{
    def "Draw"() {
        given:
        def model = Mock(GameOverModel)
        model.gameOverMessage() >> "It's a draw"
        def screen = Mock(Screen)
        def graphics = Mock(TextGraphics)

        def view = new GameOverView(model, screen, graphics)
        def spy = Spy(view)
        when:
        spy.draw()
        then:
        1 * spy.clear()
        1 * spy.drawGameOverMessage()
    }
}
