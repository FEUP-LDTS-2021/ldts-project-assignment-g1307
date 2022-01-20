package chess.model.game.rules;

import chess.model.game.Position;
import chess.model.game.board.BoardModel;
import chess.model.game.move.Move;
import chess.model.game.pieces.King;
import chess.model.game.pieces.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoSuicideAllowed implements Rule{
    Set<Piece> pieceSet;
    Rule[] rules;
    BoardModel boardModel;
    public NoSuicideAllowed(Set<Piece> pieceSet, Rule[] rules, BoardModel boardModel) {
        this.pieceSet = pieceSet;
        this.rules = rules;
        this.boardModel = boardModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        if (piece instanceof King) {
            Boolean hasMove = piece.isMoved();
            Position originalPos = piece.getPosition();
            Set<Piece> piecesInGame = pieceSet;
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
                Set<Move> moves = piece.getMoves(boardModel);
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
        for (Rule rule : rules) {
            if (!(rule instanceof ProtectKingRule)) {
                rule.obyRule(filterMoves,piece);
            }
            else
                break;
        }
    }
}
