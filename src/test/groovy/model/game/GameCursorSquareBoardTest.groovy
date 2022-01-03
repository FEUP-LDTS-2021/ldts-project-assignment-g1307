package model.game

import model.game.board.SquareBoard
import spock.lang.Specification

class GameCursorSquareBoardTest extends Specification {
    GameCursor gameCursorA;
    GameCursor gameCursorB;
    GameCursor gameCursorC;
    Position selectedPositionA;
    Position selectedPositionB;
    Position selectedPositionC;

    void setup() {
        gameCursorA = new GameCursor(new Position(1, 1), new SquareBoard(8))
        gameCursorB = new GameCursor(new Position(8, 8), new SquareBoard(8))
        gameCursorC = new GameCursor(new Position(3, 3), new SquareBoard(8))
        selectedPositionA = null;
        selectedPositionB = null;
        selectedPositionC = null;
    }

    def "MoveUp"() {
        when:
        gameCursorA.moveUp()
        gameCursorA.select(selectedPositionA)
        gameCursorB.moveUp()
        gameCursorB.select(selectedPositionB)
        gameCursorC.moveUp()
        gameCursorC.select(selectedPositionC)
        then:
        selectedPositionA.getRow() == 8 && selectedPositionA.getCol() == 1
        selectedPositionB.getRow() == 7 && selectedPositionB.getCol() == 8
        selectedPositionC.getRow() == 2 && selectedPositionC.getCol() == 3
    }

    def "MoveDown"() {
        when:
        gameCursorA.moveDown()
        gameCursorA.select(selectedPositionA)
        gameCursorB.moveDown()
        gameCursorB.select(selectedPositionB)
        gameCursorC.moveDown()
        gameCursorC.select(selectedPositionC)
        then:
        selectedPositionA.getRow() == 2 && selectedPositionA.getCol() == 1
        selectedPositionB.getRow() == 1 && selectedPositionB.getCol() == 8
        selectedPositionC.getRow() == 4 && selectedPositionC.getCol() == 3
    }

    def "MoveLeft"() {
        when:
        gameCursorA.moveLeft()
        gameCursorA.select(selectedPositionA)
        gameCursorB.moveLeft()
        gameCursorB.select(selectedPositionB)
        gameCursorC.moveLeft()
        gameCursorC.select(selectedPositionC)
        then:
        selectedPositionA.getRow() == 1 && selectedPositionA.getCol() == 8
        selectedPositionB.getRow() == 8 && selectedPositionB.getCol() == 7
        selectedPositionC.getRow() == 3 && selectedPositionC.getCol() == 2
    }

    def "MoveRight"() {
        when:
        gameCursorA.moveRight()
        gameCursorA.select(selectedPositionA)
        gameCursorB.moveRight()
        gameCursorB.select(selectedPositionB)
        gameCursorC.moveRight()
        gameCursorC.select(selectedPositionC)
        then:
        selectedPositionA.getRow() == 1 && selectedPositionA.getCol() == 2
        selectedPositionB.getRow() == 8 && selectedPositionB.getCol() == 1
        selectedPositionC.getRow() == 3 && selectedPositionC.getCol() == 4
    }
}