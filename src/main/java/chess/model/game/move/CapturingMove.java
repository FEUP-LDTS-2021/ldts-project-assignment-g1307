package chess.model.game.move;

import chess.model.game.pieces.Piece;

import java.util.Set;

public class CapturingMove extends MoveDecorator {
    Piece capturedPiece;
    Set<Piece> inGamePieces;

    public CapturingMove(Piece piece, Move move, Set<Piece> inGamePieces) {
        super(move);
        assert piece != null;
        this.inGamePieces = inGamePieces;
        capturedPiece = piece;
    }

    @Override
    public void execute() {
        super.execute();
        inGamePieces.remove(capturedPiece);
    }
}
