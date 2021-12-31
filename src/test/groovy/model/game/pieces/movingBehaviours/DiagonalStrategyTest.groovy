package model.game.pieces.movingBehaviours

import model.game.Position

class DiagonalStrategyTest extends MovingBehaviourTest {
    def setup(){
        def diagonalStrategy = new DiagonalStrategy()
        movingStrategy = new SizeMoveTest<DiagonalStrategy>(diagonalStrategy)

        'number of positions of result of Test d4 -- using moves count'

        resultsA = 13

        'number of positions of result of Test a8 -- moves count'

        resultsB = 7

        'number of positions of result of Test a5 -- moves count'

        resultsC = 7

        'number of positions of result of Test a1 -- moves count'

        resultsD = 7

    }

    def "Checking if some positions are in the possible ones"() {
        given:
        def diagonalStrategy = new DiagonalStrategy()
        def p = new Position(1,1)
        when:
        def r = diagonalStrategy.getMoves(board, p)
        then:
        r.contains(new Position(8,8))
        !r.contains(new Position(1,2))
    }
}
