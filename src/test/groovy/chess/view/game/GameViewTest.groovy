package chess.view.game

import chess.model.game.GameCursor
import chess.model.game.Position
import chess.model.game.board.BoardCase
import chess.model.game.move.Move
import chess.model.game.pieces.Piece
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import chess.model.game.GameModel
import chess.model.game.board.BoardModel
import spock.lang.Specification

import java.awt.Color

class GameViewTest extends Specification {

    def "Testing views"() {
        given:
        def model = Mock(GameModel)
        def screen = Mock(Screen)
        def graphics = Mock(TextGraphics)
        def cursor = Mock(GameCursor)
        def piece = Mock(Piece)

        cursor.getCurrentPosition() >> new Position(0,2)
        model.getGamePlayers() >> new HashSet()
        def lst = new LinkedList()
        lst.add(new BoardCase(Color.PINK,new Position(1,2)))
        def b = Mock(BoardModel)
        b.getCases() >> lst

        def lst2 = new LinkedList()
        lst2.add(piece)
        b.getCases() >> lst2

        piece.getPosition() >> new Position(9,9)

        model.getPiecesInGame() >> lst2
        model.getBoardModel() >> b
        model.getCursor() >> cursor
        model.getPieceLegalMoves() >> new HashSet<Move>()


        def view = new GameView(model, screen, graphics)
        def spy = Spy(view)

        def cColor = Color.PINK
        when:
        spy.draw()
        then:
        1 * spy.clear()
        1 * spy.drawBoard()
        1 * spy.drawClock()
        1 * screen.clear()
        1 * screen.refresh()
        (1.._) * graphics.setBackgroundColor( new TextColor.RGB(cColor.getRed(), cColor.getGreen(), cColor.getBlue()))
        (1.._) * spy.drawPiece(_)
        spy.getModel() == model

    }
}
