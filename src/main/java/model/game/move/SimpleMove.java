package model.game.move;

import model.game.Position;
import model.game.pieces.Piece;

public class SimpleMove implements Move{
    Piece piece;
    Position position;

    public SimpleMove(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    @Override
    public void execute() {
        piece.moveToPosition(position);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

}
