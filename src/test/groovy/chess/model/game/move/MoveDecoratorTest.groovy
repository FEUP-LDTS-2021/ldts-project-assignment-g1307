package chess.model.game.move

import chess.model.game.Position
import spock.lang.Specification

class MoveDecoratorTest extends Specification {
    def "Execute"() {
        given:
        def s = Mock(Move)
        s.getPosition() >> new Position(0,0)
        def decor = new MoveDecorator(s)
        def spy = Spy(decor)
        spy.moveWrappee = s
        when:
        spy.execute()
        then:
        1 * spy.moveWrappee.execute()
        expect:
        decor.getPosition() != null
    }
}
