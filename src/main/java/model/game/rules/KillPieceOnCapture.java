package model.game.rules;

import model.game.GameModel;
import model.game.board.SquareBoard;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class KillPieceOnCapture implements Rule{
    private final GameModel gameModel;

    KillPieceOnCapture(GameModel gameModel) throws NotSupportedBoard {
        this.gameModel = gameModel;
    }

    @Override
    public Set<Move> obyRule(Piece pieceToFilter) {
        return null;
    }
}
