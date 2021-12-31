package model.game.pieces.movingBehaviours

import model.game.Position

class LStrategyTest extends MovingBehaviourTest {
    def setup() {
        def lStrategy = new LStrategy()
        movingStrategy = new DistanceMoveTest<LStrategy>(lStrategy, 2)

        'number of positions of result of Test d4, a8, a5, a1'

        resultsA = resultsB = resultsC = resultsD = true
    }

    def "Checking if some positions are in the possible ones"() {
        given:
        def lStrategy = new LStrategy()
        def p = new Position(1,1)
        when:
        def r = lStrategy.getMoves(board, p)
        then:
        'Only these 2'
        r.contains(new Position(2,3))
        r.contains(new Position(3,2))

        r.size() == 2
    }
}
