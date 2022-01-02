package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class NoStepOverPiece implements Rule{
    GameModel gameModel;
    NoStepOverPiece(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Move> obyRule(Piece pieceToFilter) {
        return null;
    }
}
