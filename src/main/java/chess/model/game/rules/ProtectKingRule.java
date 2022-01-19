package chess.model.game.rules;

import chess.model.game.GameModel;
import chess.model.game.Position;
import chess.model.game.move.Move;
import chess.model.game.pieces.King;
import chess.model.game.pieces.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProtectKingRule implements Rule{
    private final GameModel gameModel;

    public ProtectKingRule(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        if (!(piece instanceof King)) {
            King king = gameModel.getPlayerKing(piece.getColor());
            king.setInCheck(isInCheck(king));

            simulateMoves(movesToFilter, piece, king);
        }
    }

    private Boolean isInCheck(King king) {
        for (Piece piece : gameModel.getPiecesInGame()) {
            if (piece.getColor() != king.getColor()) {
                Set<Move> filterMoves = piece.getMoves(gameModel.getBoardModel());
                for (Rule rule : gameModel.getRules()) {
                    if (!(rule instanceof ProtectKingRule)) {
                           rule.obyRule(filterMoves,piece);
                    }
                    else break;
                }
                for (Move move: filterMoves)
                    if (move.getPosition().equals(king.getPosition()))
                        return true;
            }
        }
        return false;
    }

    private void simulateMoves(Set<Move> movesToFilter, Piece piece, King king) {
        Position originalPos = piece.getPosition();
        Boolean hasMove = piece.isMoved();
        Set<Piece> inGamePieces = gameModel.getPiecesInGame();
        List<Piece> pieces = new ArrayList<>(inGamePieces);
        Set<Move> toRemove = new HashSet<>();
        for (Move move:movesToFilter) {
            move.execute();
            if (isInCheck(king))
                toRemove.add(move);
            piece.moveToPosition(originalPos);
            piece.setHasMove(hasMove);
            inGamePieces.clear();
            inGamePieces.addAll(pieces);
        }
        movesToFilter.removeAll(toRemove);
    }
}
