package model.game.rules;

import jdk.jshell.spi.ExecutionControl;
import model.game.GameModel;
import model.game.move.Move;
import model.game.pieces.King;
import model.game.pieces.Piece;
import model.game.player.Player;

import java.util.Set;

public class ProtectKingRule implements Rule{
    private GameModel gameModel;

    public ProtectKingRule(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece) {
        King king = null;

        for(Piece p : gameModel.getPiecesInGame()){
            if (p instanceof King && p.getColor() == piece.getColor()) {
                king = (King) p;
                break;
            }
        }

        gameModel.setCheck();
        if (king.inCheck())
            return;

        gameModel.getPiecesInGame().remove(piece);
        gameModel.setCheck();

        if (king.inCheck()){
            gameModel.getPiecesInGame().add(piece);
            movesToFilter.clear();
        }
    }
}
