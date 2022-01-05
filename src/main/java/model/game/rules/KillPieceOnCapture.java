package model.game.rules;

import model.game.GameModel;
import model.game.move.CapturingMove;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class KillPieceOnCapture implements Rule{
    private final GameModel gameModel;

    public KillPieceOnCapture(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p)  {
        Set<Move> toRemove = new HashSet<>();
        Set<Move> toAdd = new HashSet<>();
        for (Move move: movesToFilter) {
            for (Piece piece : gameModel.getPiecesInGame()) {
                if (move.getPosition().equals(piece.getPosition()) && piece.getColor() != p.getColor()) {
                    toRemove.add(move);
                    toAdd.add(new CapturingMove(piece, move, gameModel.getPiecesInGame()));
                }
            }
        }
        for (Move move: toRemove) movesToFilter.remove(move);
        movesToFilter.addAll(toAdd);
    }
}
