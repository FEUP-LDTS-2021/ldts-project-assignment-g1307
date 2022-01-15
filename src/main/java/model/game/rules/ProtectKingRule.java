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

public class ProtectKingRule implements Rule{
    private GameModel gameModel;

    public ProtectKingRule(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        if (!(piece instanceof King)) {
            King king = gameModel.getPlayerKing(piece.getColor());
            king.setInCheck(isInCheck(king));

            simulateMoves(movesToFilter, piece);
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

    private void simulateMoves(Set<Move> movesToFilter, Piece piece) {
        Boolean hasMove = piece.isMoved();
        Position originalPos = piece.getPosition();
        List<Piece> pieces = new ArrayList<>(gameModel.getPiecesInGame());
        Set<Move> toRemove = new HashSet<>();
        for (Move move:movesToFilter) {
            move.execute();
            if (isInCheck(gameModel.getPlayerKing(piece.getColor())))
                toRemove.add(move);
            piece.moveToPosition(originalPos);
            piece.setHasMove(hasMove);
            gameModel.getPiecesInGame().addAll(pieces);
        }
        movesToFilter.removeAll(toRemove);
    }
}
