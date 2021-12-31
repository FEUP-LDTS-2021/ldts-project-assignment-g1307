package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.pieces.Piece;

import java.util.Set;

public class NotCapturingSameColor implements Rule{
    GameModel gameModel;
    NotCapturingSameColor(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Position> obyRule(Piece pieceToFilter) { // Not an optimal algorithm...refractor later
        Set<Position> addedMoves = pieceToFilter.getMoves(gameModel.getBoardModel());
        for (Position position : addedMoves) {
            for (Piece p : gameModel.getPiecesInGame()) {
                if (p == pieceToFilter) continue;
                if (p.getPosition().equals(position) && pieceToFilter.getColor() == p.getColor()) {
                    addedMoves.remove(position);
                }
            }
        }
        return addedMoves;
    }
}
