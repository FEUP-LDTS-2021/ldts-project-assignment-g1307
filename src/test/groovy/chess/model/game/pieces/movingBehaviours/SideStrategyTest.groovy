package chess.model.game.pieces.movingBehaviours

import chess.model.game.Position

class SideStrategyTest extends MovingBehaviourTest{
    def setup(){
        def sizeStrategy = new SideStrategy()
        movingStrategy = new SizeMoveTest<SideStrategy>(sizeStrategy)

        'number of positions of result of Test d4, a8, a5, a1 -- using moves count'

        resultsA = resultsB = resultsC = resultsD = 14

    }

    def "Checking if some positions are in the possible ones"() {
        given:
        def sideStrategy = new SideStrategy()
        def p = new Position(1,1)
        when:
        def r = sideStrategy.getMoves(board, p)
        then:
        !r.contains(new Position(2,2))
        r.contains(new Position(1,8))
        r.contains(new Position(8,1))
    }
}
