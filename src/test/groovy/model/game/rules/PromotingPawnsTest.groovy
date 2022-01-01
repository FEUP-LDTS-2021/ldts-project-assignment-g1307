package model.game.rules

import model.game.GameModel
import model.game.Position
import model.game.board.SquareBoard
import model.game.pieces.Pawn
import model.game.pieces.Queen
import model.game.pieces.movingBehaviours.TwoAndOneStrategy
import spock.lang.Specification

class PromotingPawnsTest extends Specification {
    def "A promotion"() {
        given:
        def piece = Mock(Pawn)
        def set = new HashSet()
        set.add(piece)

        piece.getPosition() >> new Position(2,5)
        piece.getMovingBehaviour() >> new TwoAndOneStrategy(TwoAndOneStrategy.Direction.NORTH)

        def g = new GameModel()
        g.setPiecesInGame(set)
        g.setBoardModel(new SquareBoard(8))

        def f = new PromotingPawns(g)
        when:
        def move = f.obyRule(piece)
        assert move!=null
        piece.moveToPosition(move[0])
        then:
        g.getPiecesInGame()[0].class == Queen // we will only do a queen promotion for now

    }
}
