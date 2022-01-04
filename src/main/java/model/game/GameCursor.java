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

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void moveUp() {
        Position position = currentPosition.sub(new Position(1,0));
        setPosition(position);
    }

    public void moveDown() {
        Position position = currentPosition.add(new Position(1, 0));
        setPosition(position);
    }

    public void moveLeft() {
        Position position = currentPosition.sub(new Position(0, 1));
        setPosition(position);
    }

    public void moveRight() {
        Position position = currentPosition.add(new Position(0, 1));
        setPosition(position);
    }

    private void setPosition(Position position) {
        if (boardModel.positionInBoard(position))
            currentPosition = position;
    }
}