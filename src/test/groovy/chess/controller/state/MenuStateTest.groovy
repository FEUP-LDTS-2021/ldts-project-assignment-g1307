package chess.controller.state

import chess.model.game.GameModel
import chess.view.game.GameView
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import chess.model.menu.MenuModel
import spock.lang.Specification
import chess.view.menu.MenuView

class MenuStateTest extends Specification {

    def "Inputting Eof after left and right inputs"() {
        given:
        def mM = Mock(MenuModel)
        def mV = Mock(MenuView)
        MenuState menuState = new MenuState(mV)
        def spy = Spy(menuState)
        spy.view = mV
        spy.model = mM
        def screen = Mock(Screen)
        spy.getKey(_ as Screen) >>> [KeyType.ArrowLeft, KeyType.ArrowRight, KeyType.EOF]
        mV.getScreen() >> screen
        mV.close() >> null
        when:
        spy.run()
        spy.run()
        spy.run()
        then:
        3 * spy.draw()
        1 * mM.setNextOption()
        1 * mM.setPreviousOption()
        'In Eof the screen must be closed and the program finished so the last key is not read'
        1 * screen.close()
    }

    def "space input"() {
        given:
        def mM = Mock(MenuModel)
        def mV = Mock(MenuView)
        MenuState menuState = new MenuState(mV)
        def spy = Spy(menuState)
        spy.view = mV
        spy.model = mM
        mM.getCurrentOption() >> MenuModel.Option.EXIT
        def screen = Mock(Screen)
        spy.getKey(_ as Screen) >> KeyType.Enter
        mV.getScreen() >> screen
        mV.close() >> null
        when:
        spy.run()
        then:
        1 * spy.draw()
        1 * mV.close()
    }

    def "run return"(){
        def mM = Mock(MenuModel)
        def mV = Mock(MenuView)
        MenuState menuState = new MenuState(mV)
        def screen = Mock(Screen)

        mV.getScreen() >> screen
        mV.close() >> null

        menuState.view = mV
        menuState.model = mM

        expect:
        menuState.run() != null
    }
}
