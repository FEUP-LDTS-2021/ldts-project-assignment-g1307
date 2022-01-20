package chess.controller.state

import chess.model.game.GameCursor
import chess.model.game.GameModel
import chess.model.game.Position
import chess.model.game.board.BoardModel
import chess.model.game.move.MoveDecoratorTest
import chess.model.game.rules.Castle
import chess.view.game.GameView
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

import javax.swing.KeyStroke

class GameStateTest extends Specification {
    def "Run"() {
        given:
        def mM = Mock(GameModel)
        def mV = Mock(GameView)
        GameState gameState = new GameState(mM, mV)
        def spy = Spy(gameState)
        def screen = Mock(Screen)

        spy.getKey(_ as Screen) >>> [KeyType.ArrowLeft, KeyType.ArrowRight, KeyType.ArrowUp, KeyType.ArrowDown, KeyType.Enter, KeyType.EOF]
        mV.getScreen() >> screen
        mV.close() >> null

        def gC = new GameCursor(Mock(Position), Mock(BoardModel))
        GameCursor gameCursor = Spy(gC)
        mM.getCursor() >> gameCursor


        when:
        for (int i = 0; i < 6 ; i++)
            spy.run()
        then:
        1 * gameCursor.moveLeft()
        1 * gameCursor.moveRight()
        1 * gameCursor.moveUp()
        1 * gameCursor.moveDown()
        1 * screen.close()
        1 * mM.select()
        6 * mM.gameEnded()
        6 * spy.closeIfMoving(_)
        6 * spy.draw()
    }

    def "run return"(){
        def mM = Mock(GameModel)
        def mV = Mock(GameView)
        GameState gameState = new GameState(mM, mV)
        def screen = Mock(Screen)

        mV.getScreen() >> screen
        mV.close() >> null

        expect:
        gameState.run() != null
    }

//    def "keystroke not null"(){
//        def mM = Mock(GameModel)
//        def mV = Mock(GameView)
//        def gameState = new GameState(mM, mV)
//        def screen = Mock(Screen)
//        def key = Mock(KeyType)
//
//        mV.getScreen() >> screen
//        gameState.getKey(screen) >> key
//        mV.close() >> null
//
//        expect:
//        gameState.getKey(screen) != null
//    }
}
