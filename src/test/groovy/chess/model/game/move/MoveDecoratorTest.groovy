package chess.model.game.move

import spock.lang.Specification

class MoveDecoratorTest extends Specification {
    def "Execute"() {
        given:
        def s = Mock(Move)
        def spy = Spy(MoveDecorator)
        spy.moveWrappee = s
        when:
        spy.execute()
        then:
        1 * spy.moveWrappee.execute()
    }
}
