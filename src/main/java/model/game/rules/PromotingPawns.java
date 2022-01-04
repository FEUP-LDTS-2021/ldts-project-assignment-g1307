package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.board.SquareBoard;
import model.game.move.Move;
import model.game.move.MoveDecorator;
import model.game.move.SimpleMove;
import model.game.pieces.Pawn;
import model.game.pieces.Piece;
import model.game.pieces.Queen;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.*;

public class PromotingPawns implements Rule{
    GameModel gameModel;
    int colToSearchNorth;
    int colToSearchSouth;

    private static class PromotingMove extends MoveDecorator {
        Set<Piece> pieces;
        PromotingMove(Move move, Set<Piece> pieces) {
            super(move);
            this.pieces = pieces;
        }

        @Override
        public void execute() {
            super.execute();
            Queen queen = new Queen(super.getPiece().getColor(),super.getPosition());
            pieces.remove(super.getPiece());
            pieces.add(queen);
        }
    }

    public PromotingPawns(GameModel gameModel) throws NotSupportedBoard {
        this.gameModel = gameModel;
        if (!(gameModel.getBoardModel() instanceof SquareBoard squareBoard)) throw new NotSupportedBoard();
        colToSearchSouth = squareBoard.getColumns();
        colToSearchNorth = 1;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p) {
        if (p instanceof Pawn pawn && pawn.isMoved()) {
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
            int rowToSearch = direction == TwoAndOneStrategy.Direction.NORTH ? 1:colToSearchSouth;
            Set<Move> toDelete = new HashSet<>();
            Set<Move> toAdd = new HashSet<>();
            for (Move move: movesToFilter) {
                if (move.getPosition().getRow() == rowToSearch) {
                    toDelete.add(move);
                    toAdd.add(new PromotingMove(move, gameModel.getPiecesInGame()));
                }
            }
            for (Move move : toDelete) { movesToFilter.remove(move);}
            movesToFilter.addAll(toAdd);
        }
    }
}
