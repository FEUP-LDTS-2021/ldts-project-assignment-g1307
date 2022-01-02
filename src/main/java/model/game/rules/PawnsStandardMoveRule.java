package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.move.SimpleMove;
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
    public Set<Move> obyRule(Piece p) { // TODO: REMEMBER TO SET THE MOVE TWO FLAG
        Set<Move> filterMoves = p.getMoves(gameModel.getBoardModel());
        if (p instanceof Pawn pawn && pawn.isMoved()) {
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
            Position posToRemove = new Position(p.getPosition().getRow() + direction.change * 2, p.getPosition().getRow());
            Move move = new SimpleMove(p,posToRemove);
            filterMoves.removeIf(m -> move.getPosition().equals(m.getPosition()) && m.getPiece() == move.getPiece());
        }
        return filterMoves;
    }
}
