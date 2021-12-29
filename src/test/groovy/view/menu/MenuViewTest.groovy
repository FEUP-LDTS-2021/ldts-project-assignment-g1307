package view.menu

import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import model.menu.MenuModel
import spock.lang.Specification

class MenuViewTest extends Specification {
    def "Draw"() {
        given:
        def model = Mock(MenuModel)
        def screen = Mock(Screen)
        def graphics = Mock(TextGraphics)

        def view = new MenuView(model, screen, graphics)
        def spy = Spy(view)
        when:
        spy.draw()
        then:
        1 * spy.drawOption()
        1 * spy.drawTitle()
    }
}
