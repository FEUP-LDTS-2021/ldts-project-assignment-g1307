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
    Set<Piece> pieceSet;
    Set<Rule> rules;
    public NoSuicideAllowed(Set<Piece> pieceSet, Set<Rule> rules) {
        this.pieceSet = pieceSet;
        this.rules = rules;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        if (piece instanceof King) {
            Boolean hasMove = piece.isMoved();
            Position originalPos = piece.getPosition();
            Set<Piece> piecesInGame = gameModel.getPiecesInGame();
            List<Piece> pieces = new ArrayList<>(piecesInGame);
            Set<Move> toRemove = new HashSet<>();
            for (Move move : movesToFilter) {
                piecesInGame.removeIf(m -> m.getPosition().equals(move.getPosition()));
                piece.moveToPosition(move.getPosition());
                if (isCaseAttacked(move, piecesInGame))
                    toRemove.add(move);
                piece.moveToPosition(originalPos);
                piece.setHasMove(hasMove);
                piecesInGame.addAll(pieces);
            }
            movesToFilter.removeAll(toRemove);
        }
    }

    private boolean isCaseAttacked(Move move, Set<Piece> piecesInGame) {
        Piece king = move.getPiece();
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
