package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.board.SquareBoard;
import model.game.move.Move;
import model.game.move.MoveDecorator;
import model.game.move.SimpleMove;
import model.game.pieces.King;
import model.game.pieces.Piece;
import model.game.pieces.Rook;

import java.util.Set;

public class Castle implements Rule{
    final GameModel gameModel;
    int colToSearch;

    private static class CastleMove extends MoveDecorator {
        Rook rook;
        Position rPos;

        CastleMove(Rook rook, Move move) {
            super(move);
            assert rook != null;
            this.rook = rook;
            Position mPos = move.getPosition();
            int col = mPos.getCol(); int row = mPos.getRow();
            boolean longCastle = col - rook.getPosition().getCol() > 0; // queen side Castle in chess
            int rSideToBe = longCastle ? 1 : -1;
            rPos = new Position(row, col + rSideToBe);
        }

        @Override
        public void execute() {
            super.execute();
            rook.moveToPosition(rPos);
        }
    }

    public Castle(GameModel gameModel) throws NotSupportedBoard {
        this.gameModel = gameModel;
        if (!(gameModel.getBoardModel() instanceof SquareBoard squareBoard)) throw new NotSupportedBoard();
        colToSearch = squareBoard.getColumns();
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece)  {
        if (piece instanceof King king && !piece.isMoved() && !king.inCheck()) {
            for (Piece p : gameModel.getPiecesInGame()) {
                if (p instanceof Rook rook) {
                    Position pPos = p.getPosition();
                    Position piecePos = piece.getPosition();
                    if (!noPieceBetween(piecePos, pPos.getCol())) continue;
                    if (pPos.equals(new Position(piecePos.getRow(), colToSearch))) {
                        SimpleMove move = new SimpleMove(king, new Position(piecePos.getRow(), piecePos.getCol() + 2));
                        movesToFilter.add(new CastleMove(rook, move));
                    } else if (pPos.equals(new Position(piecePos.getRow(), 1))) {
                        SimpleMove move = new SimpleMove(king, new Position(piecePos.getRow(), piecePos.getCol() - 2));
                        movesToFilter.add(new CastleMove(rook, move));
                    }
                }
            }
        }
    }

    private boolean noPieceBetween(Position kingPos, int rookCol){
        int kingRow = kingPos.getRow();
        int kingCol = kingPos.getCol();
        for (Piece piece : gameModel.getPiecesInGame()) {
            Position piecePos = piece.getPosition();
            int pieceCol = piecePos.getCol();
            if (piecePos.getRow() == kingRow && pieceCol > rookCol && pieceCol < kingCol )
                return false;
            else if (piecePos.getRow() == kingRow && pieceCol < rookCol && pieceCol > kingCol )
                return false;
        }
        return true;
    }
}
