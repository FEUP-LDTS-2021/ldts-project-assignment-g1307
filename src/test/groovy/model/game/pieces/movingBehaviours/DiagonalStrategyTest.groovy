package model.game.pieces.movingBehaviours

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
}
