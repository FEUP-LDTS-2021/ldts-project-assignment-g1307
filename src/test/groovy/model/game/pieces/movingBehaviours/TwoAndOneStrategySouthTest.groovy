package model.game.pieces.movingBehaviours

class TwoAndOneStrategySouthTest extends MovingBehaviourTest{
    def setup(){
        def sizeStrategy = new TwoAndOneStrategy(TwoAndOneStrategy.Direction.SOUTH)
        movingStrategy = new SizeMoveTest<SideStrategy>(sizeStrategy)

        'number of positions of result of Test d4, a8, a5, a1 -- using moves count'

        resultsA = 4
        resultsB = resultsC = 3
        resultsD = 0
    }
}
