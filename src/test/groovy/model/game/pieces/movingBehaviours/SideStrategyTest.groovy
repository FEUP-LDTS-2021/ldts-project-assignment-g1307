package model.game.pieces.movingBehaviours

class SideStrategyTest extends MovingBehaviourTest{
    def setup(){
        def sizeStrategy = new SideStrategy()
        movingStrategy = new SizeMoveTest<SideStrategy>(sizeStrategy)

        'number of positions of result of Test d4, a8, a5, a1 -- using moves count'

        resultsA = resultsB = resultsC = resultsD = 14

    }
}
