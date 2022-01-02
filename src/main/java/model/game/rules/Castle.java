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
    GameModel gameModel;
    int colToSearch;

    private static class CastleMove extends MoveDecorator {
        Rook rook;
        Position rPos;

        CastleMove(Rook rook, Move move) {
            super(move);
            assert rook != null;
            this.rook = rook;
            boolean longCastle = move.getPiece().getPosition().getCol() - rook.getPosition().getCol() > 0; // queen side Castle in chess
            int rSideToBe = longCastle ? 1 : -1;
            rPos = new Position(move.getPosition().getRow(), move.getPosition().getCol() + rSideToBe);
        }

        public void execute() {
            super.execute();
            rook.moveToPosition(rPos);
        }
    }

    Castle(GameModel gameModel) throws NotSupportedBoard {
        this.gameModel = gameModel;
        if (!(gameModel.getBoardModel() instanceof SquareBoard squareBoard)) throw new NotSupportedBoard();
        colToSearch = squareBoard.getColumns();
    }
    @Override
    public Set<Move> obyRule(Piece piece) { // TODO: a bit repetitive ... it has to check if there are pieces in between
        Set<Move> addedMoves = piece.getMoves(gameModel.getBoardModel());
        if (piece instanceof King king && !piece.isMoved() && !king.inCheck()) {
            for (Piece p : gameModel.getPiecesInGame()) {
                if (p instanceof Rook rook) {
                    if (p.getPosition().equals(new Position(piece.getPosition().getRow(), colToSearch))) {
                        SimpleMove move = new SimpleMove(king, new Position(piece.getPosition().getRow(), piece.getPosition().getCol() + 2));
                        addedMoves.add(new CastleMove(rook, move));
                    } else if (p.getPosition().equals(new Position(piece.getPosition().getRow(), 1))) {
                        SimpleMove move = new SimpleMove(king, new Position(piece.getPosition().getRow(), piece.getPosition().getCol() - 2));
                        addedMoves.add(new CastleMove(rook, move));
                    }
                }
            }
        }

        return addedMoves;
    }
}
