package controller

import controller.state.ControllerState
import spock.lang.Specification
import view.menu.MenuView

class ControllerTest extends Specification {
    def "Run"() {
        given:
        def c = new Controller(Mock(MenuView))
        def spy = Spy(c)
        def state = Mock(ControllerState)
        spy.state = state
        state.run() >> null // breaking the loop
        when:
        spy.run()
        then:
        1 * spy.state.run()
    }
}
