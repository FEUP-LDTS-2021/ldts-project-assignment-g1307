package model.game.sound

import model.game.GameModel
import model.game.move.CapturingMove
import model.game.move.SimpleMove
import spock.lang.Specification

class SoundTest extends Specification{
    def "load simple move sound test"(){
        given:
        def gameModel = Mock(GameModel)
        gameModel.gameEnded() >> false
        def simpleMove = Mock(SimpleMove)
        def sound = new Sound(gameModel)
        def spy = Spy(sound)
        when:
        spy.selectSound(simpleMove)
        then:
        1 * spy.loadSound("src/main/resources/move.wav");
    }

    def "load capturing move sound test"(){
        given:
        def gameModel = Mock(GameModel)
        gameModel.gameEnded() >> false
        def capturingMove = Mock(CapturingMove)
        def sound = new Sound(gameModel)
        def spy = Spy(sound)
        when:
        spy.selectSound(capturingMove)
        then:
        1 * spy.loadSound("src/main/resources/capture.wav");
    }

    def "load  end game sound test"(){
        given:
        def gameModel = Mock(GameModel)
        gameModel.gameEnded() >> true
        def simpleMove = Mock(SimpleMove)
        def sound = new Sound(gameModel)
        def spy = Spy(sound)
        when:
        spy.selectSound(simpleMove)
        then:
        1 * spy.loadSound("src/main/resources/gameover.wav");
    }

    def "loadSound test"(){
        given:
        def gameModel = Mock(GameModel)
        def sound = new Sound(gameModel)
        when:
        def result = sound.loadSound("src/main/resources/gameover.wav") != null
        then:
        result
    }
}
