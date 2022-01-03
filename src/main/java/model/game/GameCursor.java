package model.game;

import model.game.board.BoardModel;

public class GameCursor {
    private BoardModel boardModel;
    private Position currentPosition;
    private Position initialPosition;

    public GameCursor(Position initialPosition, BoardModel boardModel){
        this.initialPosition = initialPosition;
        this.currentPosition = initialPosition;
        this.boardModel = boardModel;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void moveUp() {

    }

    public void moveDown() {

    }

    public void moveLeft() {

    }

    public void moveRight() {

    }
}
