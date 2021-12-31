package model.game.board

import model.game.Position
import model.game.board.BoardModel
import model.game.board.SquareBoard
import spock.lang.Specification

class SquareBoardTest extends Specification {
    BoardModel boardModel = new SquareBoard(8,8)
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
