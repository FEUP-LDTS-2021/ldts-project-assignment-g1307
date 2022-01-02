package model.game

import model.game.board.BoardCase
import model.game.board.BoardModel
import model.game.board.SquareBoard
import spock.lang.Specification

import java.awt.Color

class SquareBoardTest extends Specification {
    SquareBoard board = new SquareBoard(8, Color.WHITE, Color.BLACK)
    BoardModel boardModel = new SquareBoard(8)

    def "Test color of cases"(){
        when:
        def cases = board.getCases()
        then:
        cases.contains(new BoardCase(Color.BLACK, new Position(1,2)))
        cases.contains(new BoardCase(Color.WHITE, new Position(8,4)))
    }

    def 'PositionInBoard in corner of the board'() {
        given: 'a Position'
        def pos = new Position(1,1)
        when:
        def r = boardModel.positionInBoard(pos)
        then:
        r
    }

    def 'PositionInBoard in limiter of the board'() {
        given: 'a Position'
        def pos = new Position(0,0)
        when:
        def r = boardModel.positionInBoard(pos)
        then:
        !r
    }

    def 'PositionInBoard in center of the board'() {
        given: 'a Position'
        def pos = new Position(4,4)
        when:
        def r = boardModel.positionInBoard(pos)
        then:
        r
    }
}
