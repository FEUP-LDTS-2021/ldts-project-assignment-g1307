package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.CapturingMove;
import model.game.move.Move;
import model.game.move.SimpleMove;
import model.game.pieces.Pawn;
import model.game.pieces.Piece;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.Set;

public class PawnsDiagonalCapturing implements Rule{
    GameModel gameModel;
    public PawnsDiagonalCapturing(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p)  {
        if (p instanceof Pawn pawn) {
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();

            Position possiblePosCapture1 = new Position(p.getPosition().getRow() + direction.change, p.getPosition().getCol() -1);
            Position possiblePosCapture2 = new Position(p.getPosition().getRow() + direction.change, p.getPosition().getCol() +1);
            for (Piece piece : gameModel.getPiecesInGame()) {
                if (piece.getColor() != p.getColor()) {
                    Position pPos = piece.getPosition();
                    if (pPos.equals(possiblePosCapture1) || pPos.equals(possiblePosCapture2)) {
                        SimpleMove simpleMove = new SimpleMove(pawn, pPos);
                        movesToFilter.add(new CapturingMove(piece,simpleMove, gameModel.getPiecesInGame()));
                    }
                }
            }
        }
    }
}
