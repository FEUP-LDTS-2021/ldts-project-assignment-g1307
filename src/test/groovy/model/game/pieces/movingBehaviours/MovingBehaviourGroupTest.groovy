package model.game.pieces.movingBehaviours

import model.game.Position

import java.util.stream.Collectors
import java.util.stream.IntStream

class MovingBehaviourGroupTest extends MovingBehaviourTest {
    def setup(){
        'Giving a Strategy that corresponds to the queen type of movement'
        Set<MovingBehaviour> set = new HashSet<>()
        def concreteStrategy = new MovingBehaviourGroup(set)

        'Mocking the side Behaviour'

        SideStrategy mockedSideStrategy = Mock(SideStrategy)

        'The list resulted from the operation must not overlap any Diagonal strategy pos and should be 14 int length'

        List<Integer> range = IntStream.rangeClosed(1, 7)
                .boxed().collect(Collectors.toList())
        Set<Position> sidePositions= new HashSet<>()

        for (Integer i : range) {
            sidePositions.add(new Position(i,-1))
        }
        for (Integer i : range) {
            sidePositions.add(new Position(-1,i))
        }

        mockedSideStrategy.getMoves(board, _ as Position) >> sidePositions

        'Mocking the Diagonal Strategy'

        DiagonalStrategy mockedDiagonalStrategy = Mock(DiagonalStrategy)

        List<Integer> range2 = IntStream.rangeClosed(1, 6)
                .boxed().collect(Collectors.toList())
        Set<Position> sidePositionsCenterD4= new HashSet<>()
        Set<Position> sidePositionsBorderAndCorner= new HashSet<>()

        for (Integer i : range) {
            sidePositionsCenterD4.add(new Position(i,-2))
            sidePositionsBorderAndCorner.add(new  Position(i, -2))
        }
        for (Integer i : range2) {
            sidePositionsCenterD4.add(new Position(-2,i))
        }

        'It should get 13 position from the d4 square and 7 from the other tests'
        mockedDiagonalStrategy.getMoves(board, new Position(5,4)) >> sidePositionsCenterD4
        mockedDiagonalStrategy.getMoves(board, _ as Position) >> sidePositionsBorderAndCorner

        concreteStrategy.add(mockedSideStrategy)
        concreteStrategy.add(mockedDiagonalStrategy)
        movingStrategy = new SizeMoveTest<MovingBehaviourGroup>(concreteStrategy)

        'number of positions of result of Test d4 -- using moves count'

        resultsA = 27

        'number of positions of result of Test a8, a5, a1 -- moves count'

        resultsB = resultsC = resultsD = 21
    }
}
