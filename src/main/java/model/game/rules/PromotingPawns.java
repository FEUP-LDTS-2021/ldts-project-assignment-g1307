package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.pieces.Piece;

import java.util.Set;

public class PromotingPawns implements Rule{
    GameModel gameModel;
    PromotingPawns(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Position> obyRule(Piece pieceToFilter) {
        return null;
    }
}
