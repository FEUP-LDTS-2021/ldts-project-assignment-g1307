package view.game

import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import model.game.GameModel
import model.game.board.BoardModel
import spock.lang.Specification

class GameViewTest extends Specification {

    def "Testing views"() {
        given:
        def model = Mock(GameModel)
        def screen = Mock(Screen)
        def graphics = Mock(TextGraphics)

        def lst = new LinkedList()
        def b = Mock(BoardModel)
        model.getPiecesInGame() >> lst
        model.getBoardModel() >> b

        b.getCases() >> lst

        def view = new GameView(model, screen, graphics)
        def spy = Spy(view)
        when:
        spy.draw()
        then:
        1 * spy.clear()
        1 * spy.drawBoard()
    }
}
