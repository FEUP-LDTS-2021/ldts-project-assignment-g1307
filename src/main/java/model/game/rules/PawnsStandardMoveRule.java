package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.pieces.Pawn;
import model.game.pieces.Piece;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.Set;

public class PawnsStandardMoveRule implements Rule{
    GameModel gameModel;
    PawnsStandardMoveRule(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Position> obyRule(Piece p) {
        Set<Position> filterMoves = p.getMoves(gameModel.getBoardModel());
        if (p instanceof Pawn pawn && pawn.isMoved()) {
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
            filterMoves.remove(new Position(p.getPosition().getRow() + direction.change * 2, p.getPosition().getRow()));
        }
        return filterMoves;
    }
}
