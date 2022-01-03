package model.game

import model.game.board.SquareBoard
import spock.lang.Specification

class GameCursorSquareBoardTest extends Specification {
    GameCursor gameCursorA;
    GameCursor gameCursorB;
    GameCursor gameCursorC;

    void setup() {
        gameCursorA = new GameCursor(new Position(1, 1), new SquareBoard(8))
        gameCursorB = new GameCursor(new Position(8, 8), new SquareBoard(8))
        gameCursorC = new GameCursor(new Position(3, 3), new SquareBoard(8))
    }

    def "MoveUp"() {
        when:
        gameCursorA.moveUp()
        gameCursorB.moveUp()
        gameCursorC.moveUp()
        then:
        gameCursorA.getCurrentPosition().getRow() == 8 && gameCursorA.getCurrentPosition().getCol() == 1
        gameCursorB.getCurrentPosition().getRow() == 7 && gameCursorA.getCurrentPosition().getCol() == 8
        gameCursorC.getCurrentPosition().getRow() == 2 && gameCursorA.getCurrentPosition().getCol() == 3
    }

    def "MoveDown"() {
        when:
        gameCursorA.moveDown()
        gameCursorB.moveDown()
        gameCursorC.moveDown()
        then:
        gameCursorA.getCurrentPosition().getRow() == 2 && gameCursorA.getCurrentPosition().getCol() == 1
        gameCursorB.getCurrentPosition().getRow() == 1 && gameCursorA.getCurrentPosition().getCol() == 8
        gameCursorC.getCurrentPosition().getRow() == 4 && gameCursorA.getCurrentPosition().getCol() == 3
    }

    def "MoveLeft"() {
        when:
        gameCursorA.moveLeft()
        gameCursorB.moveLeft()
        gameCursorC.moveLeft()
        then:
        gameCursorA.getCurrentPosition().getRow() == 1 && gameCursorA.getCurrentPosition().getCol() == 8
        gameCursorB.getCurrentPosition().getRow() == 8 && gameCursorA.getCurrentPosition().getCol() == 7
        gameCursorC.getCurrentPosition().getRow() == 3 && gameCursorA.getCurrentPosition().getCol() == 2
    }

    def "MoveRight"() {
        when:
        gameCursorA.moveRight()
        gameCursorB.moveRight()
        gameCursorC.moveRight()
        then:
        gameCursorA.getCurrentPosition().getRow() == 1 && gameCursorA.getCurrentPosition().getCol() == 2
        gameCursorB.getCurrentPosition().getRow() == 8 && gameCursorA.getCurrentPosition().getCol() == 1
        gameCursorC.getCurrentPosition().getRow() == 3 && gameCursorA.getCurrentPosition().getCol() == 4
    }
}