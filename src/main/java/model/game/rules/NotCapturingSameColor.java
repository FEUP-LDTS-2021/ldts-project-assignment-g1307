package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class NotCapturingSameColor implements Rule{
    GameModel gameModel;
    public NotCapturingSameColor(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece)  {
        for (Move move : movesToFilter) {
            for (Piece p : gameModel.getPiecesInGame()) {
                if (p.getPosition().equals(move.getPosition()) && piece.getColor() == p.getColor()) {
                    movesToFilter.remove(move);
                }
            }
        }
    }
}
