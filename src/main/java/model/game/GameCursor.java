package model.game;

import model.game.board.BoardModel;

public class GameCursor {
    private BoardModel boardModel;
    private Position currentPosition;
    private Position selectedPosition = null;

    public GameCursor(Position initialPosition, BoardModel boardModel){
        this.currentPosition = initialPosition;
        this.boardModel = boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public void select(){
        selectedPosition = currentPosition;
    }

    public Position getSelectedPosition() {
        return selectedPosition;
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