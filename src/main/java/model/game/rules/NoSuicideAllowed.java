package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.King;
import model.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        Position originalPos = king.getPosition();
        king.moveToPosition(move.getPosition()); // simulation of move.execute()
        for (Piece piece: gameModel.getPiecesInGame()) {
            if (piece.getColor() != king.getColor() && !(piece instanceof King)) {
                Set<Move> moves = piece.getMoves(gameModel.getBoardModel());
                gameModel.filterMoves(moves, piece);
                for (Move enemyMove : moves) {
                    if (enemyMove.getPosition().equals(move.getPosition())) {
                        king.moveToPosition(originalPos);
                        return true;
                    }
                }
            }
        }
        king.moveToPosition(originalPos);
        return false;
    }
}
