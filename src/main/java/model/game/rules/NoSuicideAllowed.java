package model.game.rules;

import model.game.GameModel;
import model.game.move.Move;
import model.game.pieces.King;
import model.game.pieces.Piece;

import java.util.Set;

public class NoSuicideAllowed implements Rule{
    GameModel gameModel;
    public NoSuicideAllowed(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        if (piece instanceof King) {
            movesToFilter.removeIf(this::isCaseAttacked);
        }
    }

    private boolean isCaseAttacked(Move move) {
        Piece king = move.getPiece();
        for (Piece piece: gameModel.getPiecesInGame()) {
            if (piece.getColor() != king.getColor()) {
                Set<Move> moves = piece.getMoves(gameModel.getBoardModel());
                filterMovesUntilRule(moves, piece);
                for (Move enemyMove : moves) {
                    if (enemyMove.getPosition().equals(move.getPosition())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void filterMovesUntilRule(Set<Move> filterMoves, Piece piece) {
        for (Rule rule : gameModel.getRules()) {
            if (!(rule instanceof NoSuicideAllowed)) {
                rule.obyRule(filterMoves,piece);
            }
            else
                break;
        }
    }
}
