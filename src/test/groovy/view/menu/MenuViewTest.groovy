package view.menu

import model.menu.MenuModel
import spock.lang.Specification

class MenuViewTest extends Specification {
    def "Draw"() {
        given:
        def model = Mock(MenuModel)
        def view = new MenuView(model)
        def spy = Spy(view)
        when:
        view.draw()
        then:
        1 * spy.drawOption()
        1 * spy.drawTitle()
    }
}
