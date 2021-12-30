package model.game.pieces.movingBehaviours

import model.game.Position

class TwoAndOneStrategySouthTest extends MovingBehaviourTest{
    def setup(){
        def sizeStrategy = new TwoAndOneStrategy(TwoAndOneStrategy.Direction.SOUTH)
        movingStrategy = new SizeMoveTest<TwoAndOneStrategy>(sizeStrategy)

        'number of positions of result of Test d4, a8, a5, a1 -- using moves count'

        resultsA = 4
        resultsB = resultsC = 3
        resultsD = 0
    }

    def "Checking if some positions are in the possible ones"() {
        given:
        def twoAndOne = new TwoAndOneStrategy(TwoAndOneStrategy.Direction.SOUTH)
        def p = new Position(7,2)
        when:
        def r = twoAndOne.getMoves(board, p)
        then:
        'the 3 positions'
        r.contains(new Position(8,1))
        r.contains(new Position(8,3))
        r.contains(new Position(8,2))
    }
}
