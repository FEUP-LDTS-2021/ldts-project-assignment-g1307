package model.game.pieces.movingBehaviours

class LStrategyTest extends MovingBehaviourTest {
    def setup() {
        def lStrategy = new LStrategy()
        movingStrategy = new DistanceMoveTest<LStrategy>(lStrategy, 2)

        'number of positions of result of Test d4, a8, a5, a1'

        resultsA = resultsB = resultsC = resultsD = true

    }
}
