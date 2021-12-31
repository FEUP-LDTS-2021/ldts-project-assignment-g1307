package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.board.SquareBoard;
import model.game.pieces.King;
import model.game.pieces.Piece;
import model.game.pieces.Rook;

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
    public Set<Position> obyRule(Piece piece) {
        Set<Position> addedMoves = piece.getMoves(gameModel.getBoardModel());
        if (piece instanceof King king && !piece.isMoved() && !king.inCheck()) { // TODO: a bit repetitive
            if (gameModel.getPiecesInGame().contains(new Rook(piece.getColor(),new Position(piece.getPosition().getRow(), colToSearch)))) {
                addedMoves.add(new Position(piece.getPosition().getRow(),piece.getPosition().getCol() + 2)); // here the position must have an information to switch with Rook ...still not implemented
                // TODO: DO NOT FORGOT TO FINISH THE SWITCH WITH THE ROOK ... TO DISCUSS
            }
            if (gameModel.getPiecesInGame().contains(new Rook(piece.getColor(),new Position(piece.getPosition().getRow(), 1)))) { // rook in first col
                addedMoves.add(new Position(piece.getPosition().getRow(),piece.getPosition().getCol() - 2));
            }
        }
        return addedMoves;
    }
}
