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

import java.util.Set;

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
            // TODO: THIS SHOULD TRIGGER A MESSAGE THAT ASKS TO WHAT PIECE TO PROMOTE
        }
    }

    public PromotingPawns(GameModel gameModel) throws NotSupportedBoard {
        this.gameModel = gameModel;
        if (!(gameModel.getBoardModel() instanceof SquareBoard squareBoard)) throw new NotSupportedBoard();
        colToSearchSouth = squareBoard.getColumns();
        colToSearchNorth = 1;

    }
    @Override
    public Set<Move> obyRule(Piece p) {
        Set<Move> filterMoves = p.getMoves(gameModel.getBoardModel());
        if (p instanceof Pawn pawn && pawn.isMoved()) {
            TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
            int rowToSearch = direction == TwoAndOneStrategy.Direction.NORTH ? 1:colToSearchSouth;
            for (Move move: filterMoves) {
                if (move.getPosition().getRow() == rowToSearch) {
                    Move newMove = new SimpleMove(pawn, new Position(rowToSearch, move.getPosition().getCol()));
                    removeMoveAndAddNew(filterMoves,newMove);
                }
            }
        }
        return filterMoves;
    }

    private void removeMoveAndAddNew(Set<Move> filterMoves, Move move) {
        filterMoves.removeIf(m -> move.getPosition().equals(m.getPosition()) && m.getPiece() == move.getPiece());
        filterMoves.add(new PromotingMove(move, gameModel.getPiecesInGame()));
    }
}
