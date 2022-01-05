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
        gameCursorA.select()
        gameCursorB.moveUp()
        gameCursorB.select()
        gameCursorC.moveUp()
        gameCursorC.select()
        then:
        gameCursorA.getSelectedPosition().getRow() == 1 && gameCursorA.getSelectedPosition().getCol() == 1
        gameCursorB.getSelectedPosition().getRow() == 7 && gameCursorB.getSelectedPosition().getCol() == 8
        gameCursorC.getSelectedPosition().getRow() == 2 && gameCursorC.getSelectedPosition().getCol() == 3
    }

    def "MoveDown"() {
        when:
        gameCursorA.moveDown()
        gameCursorA.select()
        gameCursorB.moveDown()
        gameCursorB.select()
        gameCursorC.moveDown()
        gameCursorC.select()
        then:
        gameCursorA.getSelectedPosition().getRow() == 2 && gameCursorA.getSelectedPosition().getCol() == 1
        gameCursorB.getSelectedPosition().getRow() == 8 && gameCursorB.getSelectedPosition().getCol() == 8
        gameCursorC.getSelectedPosition().getRow() == 4 && gameCursorC.getSelectedPosition().getCol() == 3
    }

    def "MoveLeft"() {
        when:
        gameCursorA.moveLeft()
        gameCursorA.select()
        gameCursorB.moveLeft()
        gameCursorB.select()
        gameCursorC.moveLeft()
        gameCursorC.select()
        then:
        gameCursorA.getSelectedPosition().getRow() == 1 && gameCursorA.getSelectedPosition().getCol() == 1
        gameCursorB.getSelectedPosition().getRow() == 8 && gameCursorB.getSelectedPosition().getCol() == 7
        gameCursorC.getSelectedPosition().getRow() == 3 && gameCursorC.getSelectedPosition().getCol() == 2
    }

    def "MoveRight"() {
        when:
        gameCursorA.moveRight()
        gameCursorA.select()
        gameCursorB.moveRight()
        gameCursorB.select()
        gameCursorC.moveRight()
        gameCursorC.select()
        then:
        gameCursorA.getSelectedPosition().getRow() == 1 && gameCursorA.getSelectedPosition().getCol() == 2
        gameCursorB.getSelectedPosition().getRow() == 8 && gameCursorB.getSelectedPosition().getCol() == 8
        gameCursorC.getSelectedPosition().getRow() == 3 && gameCursorC.getSelectedPosition().getCol() == 4
    }
}