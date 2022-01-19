package chess.model.game.pieces.movingBehaviours

import chess.model.game.Position

class AdjacentStrategyTest extends MovingBehaviourTest  {
    def setup(){
        'giving a strategy'
        def aStrategy = new AdjacentStrategy()
        movingStrategy = new DistanceMoveTest<AdjacentStrategy>(aStrategy, 1)

        'positions of result of Test d4, a8, a5, a1'

        resultsA = resultsB = resultsC = resultsD = true
    }

    def "Checking if some positions are in the possible ones"() {
        given:
        def adjacentStrategy = new AdjacentStrategy()
        def p = new Position(1,1)
        when:
        def r = adjacentStrategy.getMoves(board, p)
        then:
        !r.contains(new Position(8,8))
        r.contains(new Position(1,2))
    }
}
