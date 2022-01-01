package controller.state

import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import model.menu.MenuModel
import spock.lang.Specification
import view.menu.MenuView

class MenuStateTest extends Specification {
    def "Inputting Eof"() {
        given:
        def mM = Mock(MenuModel)
        def mV = Mock(MenuView)
        MenuState menuState = new MenuState(mM, mV)
        def spy = Spy(menuState)
        spy.view = mV
        spy.model = mM
        def screen = Mock(Screen)
        spy.getKey(_ as Screen) >> KeyType.EOF
        mV.getScreen() >> screen
        mV.close() >> null
        when:
        spy.run()
        then:
        1 * spy.draw()
        'In Eof the screen must be closed and the program finished'
        1 * screen.close()
    }
}
