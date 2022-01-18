package model.game.rules;

import model.game.GameModel;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class NotCapturingSameColor implements Rule{
    Set<Piece> gameModel;
    public NotCapturingSameColor(Set<Piece> gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece)  {
        Set<Move> toRemove = new HashSet<>();
        for (Move move : movesToFilter) {
            for (Piece p : gameModel.getPiecesInGame()) {
                if (p.getPosition().equals(move.getPosition()) && piece.getColor() == p.getColor()) {
                    toRemove.add(move);
                }
            }
        }
        for (Move move: toRemove) movesToFilter.remove(move);
    }
}
