package chess.model.game.pieces.movingBehaviours

import chess.model.game.Position

class TwoAndOneStrategyNorthTest extends MovingBehaviourTest{
    def setup(){
        def sizeStrategy = new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)
        movingStrategy = new SizeMoveTest<TwoAndOneStrategy>(sizeStrategy)

        'number of positions of result of Test d4, a8, a5, a1 -- using moves count'

        resultsA = resultsC = resultsD = 2
        resultsB = 0
    }

    def "Checking if some positions are in the possible ones"() {
        given:
        def twoAndOne = new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)
        def p = new Position(7,2)
        when:
        def r = twoAndOne.getMoves(board, p)
        then:
        'the 2 positions'
        r.contains(new Position(6,2))
        r.contains(new Position(5,2))
    }
}
