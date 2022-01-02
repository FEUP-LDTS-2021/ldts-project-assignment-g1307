package model.game.move;

import model.game.pieces.Piece;

public class CapturingMove extends MoveDecorator {
    Piece capturedPiece;

    public CapturingMove(Piece piece, Move move) {
        super(move);
        assert piece != null;
    }

    @Override
    public void execute() {
        super.execute();
        capturedPiece = null;
    }
}
