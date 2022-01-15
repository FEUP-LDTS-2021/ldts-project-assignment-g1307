package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.King;
import model.game.pieces.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoSuicideAllowed implements Rule{
    GameModel gameModel;
    public NoSuicideAllowed(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        if (piece instanceof King) {
            Boolean hasMove = piece.isMoved();
            Position originalPos = piece.getPosition();
            List<Piece> pieces = new ArrayList<>(gameModel.getPiecesInGame());
            Set<Move> toRemove = new HashSet<>();
            for (Move move : movesToFilter) {
                move.execute();
                if (isCaseAttacked(move))
                    toRemove.add(move);
                piece.moveToPosition(originalPos);
                piece.setHasMove(hasMove);
                gameModel.getPiecesInGame().addAll(pieces);
            }
            movesToFilter.removeAll(toRemove);
        }
    }

    private boolean isCaseAttacked(Move move) {
        Piece king = move.getPiece();
        Set<Piece> piecesInGame = gameModel.getPiecesInGame();
        for (Piece piece: piecesInGame) {
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
            if (!(rule instanceof ProtectKingRule)) {
                rule.obyRule(filterMoves,piece);
            }
            else
                break;
        }
    }
}
