package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.pieces.Piece;

import java.util.Set;

public class PawnsStandardMoveRule implements Rule{
    GameModel gameModel;
    PawnsStandardMoveRule(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Position> obyRule(Piece pieceToFilter) {
        return null;
    }
}
