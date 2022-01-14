package model.game.rules;

import model.game.GameModel;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class ProtectKingRule implements Rule{
    private GameModel gameModel;

    public ProtectKingRule(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {

    }
}
