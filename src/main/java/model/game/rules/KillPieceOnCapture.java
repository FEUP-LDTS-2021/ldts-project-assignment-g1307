package model.game.rules;

import model.game.GameModel;
import model.game.move.CapturingMove;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class KillPieceOnCapture implements Rule{
    private final GameModel gameModel;

    KillPieceOnCapture(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public Set<Move> obyRule(Piece p) {
        Set<Move> filterMoves = p.getMoves(gameModel.getBoardModel());

        for (Move move: filterMoves) {
            for (Piece piece : gameModel.getPiecesInGame()) {
                if (move.getPosition().equals(piece.getPosition()) && piece.getColor() != p.getColor()) {
                    filterMoves.remove(move);
                    filterMoves.add(new CapturingMove(piece, move, gameModel.getPiecesInGame()));
                }
            }
        }
        return filterMoves;
    }
}
