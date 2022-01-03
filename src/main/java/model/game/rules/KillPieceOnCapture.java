package model.game.rules;

import model.game.GameModel;
import model.game.move.CapturingMove;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class KillPieceOnCapture implements Rule{
    private final GameModel gameModel;

    public KillPieceOnCapture(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p)  {
        for (Move move: movesToFilter) {
            for (Piece piece : gameModel.getPiecesInGame()) {
                if (move.getPosition().equals(piece.getPosition()) && piece.getColor() != p.getColor()) {
                    movesToFilter.remove(move);
                    movesToFilter.add(new CapturingMove(piece, move, gameModel.getPiecesInGame()));
                }
            }
        }
    }
}
