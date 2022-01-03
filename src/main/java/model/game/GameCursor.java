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
        currentPosition = currentPosition.sub(new Position(1,0));
        if (!boardModel.positionInBoard(currentPosition))
            currentPosition = currentPosition.add(new Position(boardModel.getRows(),0));
    }

    public void moveDown() {
        currentPosition = currentPosition.add(new Position(1, 0));
        if (!boardModel.positionInBoard(currentPosition))
            currentPosition = currentPosition.sub(new Position(boardModel.getRows(),0));
    }

    public void moveLeft() {
        currentPosition = currentPosition.sub(new Position(0, 1));
        if (!boardModel.positionInBoard(currentPosition))
            currentPosition = currentPosition.add(new Position(0,boardModel.getColumns()));
    }

    public void moveRight() {
        currentPosition = currentPosition.add(new Position(0, 1));
        if (!boardModel.positionInBoard(currentPosition))
            currentPosition = currentPosition.sub(new Position(0,boardModel.getColumns()));
    }
}