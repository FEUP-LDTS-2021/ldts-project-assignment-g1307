package model.game.rules;

import model.game.Position;
import model.game.move.Move;
import model.game.pieces.Pawn;
import model.game.pieces.Piece;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.Set;

public class PawnsStandardMoveRule implements Rule{
    Set<Piece> pieceSet;
    public PawnsStandardMoveRule(Set<Piece> pieceSet) {
        this.pieceSet = pieceSet;
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p) {
        if (p instanceof Pawn pawn && pawn.isMoved()) {
            Position pawnPos = p.getPosition();
            int pRow = pawnPos.getRow(); int pCol = pawnPos.getCol();
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
            Position posToRemove = new Position(pRow + direction.change * 2, pCol);
            movesToFilter.removeIf(m -> posToRemove.equals(m.getPosition()) && m.getPiece() == p);
        }

        if (p instanceof Pawn pawn) {
            Position pawnPos = p.getPosition();
            int pRow = pawnPos.getRow(); int pCol = pawnPos.getCol();
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
            pieceInTheWay(movesToFilter ,new Position(pRow + direction.change, pCol));
            pieceInTheWay(movesToFilter, new Position(pRow + direction.change * 2, pCol));
        }
    }

    private void pieceInTheWay(Set<Move> movesToFilter, Position pieceInFront) {
        for (Piece piece : pieceSet) {
            if (piece.getPosition().equals(pieceInFront))
                movesToFilter.removeIf(move -> move.getPosition().equals(pieceInFront));
        }
    }
}
