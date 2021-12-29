package model.game.pieces.movingBehaviours

class TwoAndOneStrategyNorthTest extends MovingBehaviourTest{
    def setup(){
        def sizeStrategy = new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)
        movingStrategy = new SizeMoveTest<SideStrategy>(sizeStrategy)

        'number of positions of result of Test d4, a8, a5, a1 -- using moves count'

        resultsA = 4
        resultsB = 0
        resultsC = resultsD = 3
    }


}
