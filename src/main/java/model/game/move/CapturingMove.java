package model.game.move;

import model.game.pieces.Piece;

import java.util.Set;

public class CapturingMove extends MoveDecorator {
    Piece capturedPiece;
    Set<Piece> inGamePieces;

    public CapturingMove(Piece piece, Move move, Set<Piece> inGamePieces) {
        super(move);
        assert piece != null;
        this.inGamePieces = inGamePieces;
    }

    @Override
    public void execute() {
        super.execute();
        inGamePieces.remove(super.getPiece());
    }
}
