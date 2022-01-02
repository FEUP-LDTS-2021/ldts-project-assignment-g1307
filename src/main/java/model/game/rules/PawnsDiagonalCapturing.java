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
    PawnsDiagonalCapturing(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Move> obyRule(Piece p) {
        Set<Move> addedMoves = p.getMoves(gameModel.getBoardModel());
        if (p instanceof Pawn pawn) {
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();

            Position possiblePosCapture1 = new Position(p.getPosition().getRow() + direction.change, p.getPosition().getRow() -1);
            Position possiblePosCapture2 = new Position(p.getPosition().getRow() + direction.change, p.getPosition().getRow() +1);
            for (Piece piece : gameModel.getPiecesInGame()) {
                if (piece.getColor() != p.getColor()) {
                    if (piece.getPosition().equals(possiblePosCapture1) || piece.getPosition().equals(possiblePosCapture2)) {
                        SimpleMove simpleMove = new SimpleMove(pawn, piece.getPosition());
                        addedMoves.add(new CapturingMove(piece,simpleMove, gameModel.getPiecesInGame()));
                    }
                }
            }
        }
        return addedMoves;
    }
}
