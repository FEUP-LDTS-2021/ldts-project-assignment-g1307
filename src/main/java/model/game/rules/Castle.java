package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.board.SquareBoard;
import model.game.pieces.King;
import model.game.pieces.Piece;

import java.util.Set;

public class Castle implements Rule{
    GameModel gameModel;
    int colToSearch;
    Castle(GameModel gameModel) throws NotSupportedBoard {
        this.gameModel = gameModel;
        if (!(gameModel.getBoardModel() instanceof SquareBoard squareBoard)) throw new NotSupportedBoard();
        colToSearch = squareBoard.getColumns();
    }
    @Override
    public Set<Position> obyRule(Piece piece) { // TODO: a bit repetitive
        Set<Position> addedMoves = piece.getMoves(gameModel.getBoardModel());

        for (Piece p : gameModel.getPiecesInGame()) {
            if (piece instanceof King king && !piece.isMoved() && !king.inCheck()) {
                if (p.getPosition().equals(new Position(piece.getPosition().getRow(), colToSearch))) {
                    addedMoves.add(new Position(piece.getPosition().getRow(), piece.getPosition().getCol() + 2));
                } else if (p.getPosition().equals(new Position(piece.getPosition().getRow(), 1))) {
                    addedMoves.add(new Position(piece.getPosition().getRow(), piece.getPosition().getCol() - 2));
                }
            }
        }

        return addedMoves;
    }
}
