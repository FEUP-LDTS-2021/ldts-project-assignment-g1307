package model.game.rules;

import model.game.GameModel;
import model.game.move.CapturingMove;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class KillPieceOnCapture implements Rule{
    private final Set<Piece> pieceSet;

    public KillPieceOnCapture(Set<Piece> pieceSet) {
        this.pieceSet = pieceSet;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p)  {
        Set<Move> toRemove = new HashSet<>();
        Set<Move> toAdd = new HashSet<>();
        Set<Piece> piecesInGame = pieceSet;
        for (Move move: movesToFilter) {
            for (Piece piece : piecesInGame) {
                if (move.getPosition().equals(piece.getPosition()) && piece.getColor() != p.getColor()) {
                    toRemove.add(move);
                    toAdd.add(new CapturingMove(piece, move, piecesInGame));
                }
            }
        }
        for (Move move: toRemove) movesToFilter.remove(move);
        movesToFilter.addAll(toAdd);
    }
}
