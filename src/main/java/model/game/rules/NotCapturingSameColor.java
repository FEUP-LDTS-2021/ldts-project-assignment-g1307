package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class NotCapturingSameColor implements Rule{
    GameModel gameModel;
    NotCapturingSameColor(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Move> obyRule(Piece pieceToFilter) { // Not an optimal algorithm...refractor later
        Set<Move> addedMoves = pieceToFilter.getMoves(gameModel.getBoardModel());
        for (Move move : addedMoves) {
            for (Piece p : gameModel.getPiecesInGame()) {
                if (p == pieceToFilter) continue;
                if (p.getPosition().equals(move.getPosition()) && pieceToFilter.getColor() == p.getColor()) {
                    addedMoves.remove(move);
                }
            }
        }
        return addedMoves;
    }
}
