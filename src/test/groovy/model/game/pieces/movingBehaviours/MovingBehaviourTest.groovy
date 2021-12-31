package model.game.pieces.movingBehaviours

import model.game.board.SquareBoard
import model.game.Position
import spock.lang.Specification

abstract class MovingBehaviourTest extends Specification{
    def movingStrategy
    def resultsA;
    def resultsB;
    def resultsC;
    def resultsD;
    def board = new SquareBoard(8)


    def "Position of object is d4"() {
        given: 'Chess position equivalent to d4 in chess notation'
        def position = new Position(5,4)
        expect:
        movingStrategy.getMoves(board, position) == resultsA
    }
    def "Position of object is a8"() {
        given: 'Chess position equivalent to a8 in chess notation'
        def position = new Position(1,1)
        expect:
        movingStrategy.getMoves(board, position) == resultsB
    }
    def "Position of object is a5"() {
        given: 'Chess position equivalent to a5 in chess notation'
        def position = new Position(4,1)
        expect:
        movingStrategy.getMoves(board, position) == resultsC
    }

    def "Position of object is a1"() {
        given: 'Chess position equivalent to a1 in chess notation'
        def position = new Position(8,1)
        expect:
        movingStrategy.getMoves(board, position) == resultsD
    }
}