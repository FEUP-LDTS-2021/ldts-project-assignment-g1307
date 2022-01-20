package chess.model.game.board

import chess.model.game.Position
import chess.model.game.pieces.Piece
import spock.lang.Specification

import java.awt.Color

class BoardCaseTest extends Specification{
    def "boardCase test"(){
        def whiteCaseColor = new Color(238,238,210);
        def boardCase = new BoardCase(whiteCaseColor, new Position(1,1))
        expect:
        boardCase.getPosition() == new Position(1,1)
        boardCase.caseColor() == whiteCaseColor
    }
}
