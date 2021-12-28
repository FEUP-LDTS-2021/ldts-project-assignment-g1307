package model.game.pieces.movingBehaviours

import model.game.Position

class AdjacentStrategyTest extends MovingBehaviourTest  {
    def setup(){
        'giving a strategy'
        def aStrategy = new AdjacentStrategy()
        movingStrategy = new DistanceMoveTest<AdjacentStrategy>(aStrategy, 1)

        'positions of result of Test d4, a8, a5, a1'

        resultsA = resultsB = resultsC = resultsD = true

    }
}
