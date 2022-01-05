package model.game;

import model.Model;
import model.game.board.BoardModel;
import model.game.move.Move;
import model.game.pieces.Piece;
import model.game.player.Player;
import model.game.rules.Rule;

import java.util.HashSet;
import java.util.Set;

public class GameModel implements Model {
    private Set<Piece> piecesInGame;
    private Player[] gamePlayers;
    private Rule[] rules;
    private BoardModel boardModel;
    private GameCursor cursor;
    private int turn = 0;

    public GameModel(){
        piecesInGame = new HashSet<>();
    }

    public Set<Piece> getPiecesInGame() {
        return piecesInGame;
    }

    public void setPiecesInGame(Set<Piece> piecesInGame) {
        this.piecesInGame = piecesInGame;
    }

    public Rule[] getRules() {
        return rules;
    }

    public void setRules(Rule[] rules) {
        this.rules = rules;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public void setCursor(GameCursor gameCursor) {
        this.cursor = gameCursor;
    }

    public GameCursor getCursor() {
        return cursor;
    }

    public void setGamePlayers(Player[] gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Set<Move> getPieceLegalMoves() {

        Set<Move> moves = new HashSet<>();

        if (cursor.getSelectedPosition() == null)
            return moves;

        Piece piece = findSelectedPiece();
        if (piece == null) return moves;
        moves = piece.getMoves(boardModel);

        filterMoves(moves, piece);

        return moves;
    }

    public Piece findSelectedPiece() {
        Position selPos = cursor.getSelectedPosition();

        for (Piece p : piecesInGame) {
            if (selPos.equals(p.getPosition()) && p.getColor() == gamePlayers[turn].getColor()) {
                return p;
            }
        }
        return null;
    }

    public void filterMoves(Set<Move> moves, Piece piece) {
        for (Rule rule: rules) {
            rule.obyRule(moves,piece);
        }
    }

    public void notifyPlayers() {
        int i = 0;
        turn = ( turn + 1 ) % gamePlayers.length;
        for (Player player: gamePlayers){
            player.setTurn(turn == i);
        }
    }

    public void select() {
        if (cursor.getSelectedPosition() == null) {
            cursor.select();
            return;
        }

        Set<Move> moves = getPieceLegalMoves();
        for (Move move : moves) {
            if (move.getPosition().equals(cursor.getCurrentPosition())) {
                move.execute();
                notifyPlayers();
                break;
            }
        }
        cursor.select();
    }
}
