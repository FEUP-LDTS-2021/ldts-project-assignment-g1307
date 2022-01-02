package model.game.move;

import model.game.Position;
import model.game.pieces.Piece;

public class MoveDecorator implements Move{
    Move moveWrappee;

    public MoveDecorator(Move move) {
        moveWrappee = move;
    }

    @Override
    public void execute() {
        moveWrappee.execute();
    }

    @Override
    public Position getPosition() {
        return moveWrappee.getPosition();
    }

    @Override
    public Piece getPiece() {
        return moveWrappee.getPiece();
    }
}
