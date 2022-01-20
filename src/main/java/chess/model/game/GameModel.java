package chess.model.game;

import chess.model.Model;
import chess.model.game.board.BoardModel;
import chess.model.game.move.Move;
import chess.model.game.pieces.King;
import chess.model.game.pieces.Piece;
import chess.model.game.player.Player;
import chess.model.game.rules.Rule;

import java.util.*;

public class GameModel implements Model {
    private Set<Piece> piecesInGame;
    private Player[] gamePlayers;
    private Rule[] rules;
    private BoardModel boardModel;
    private GameCursor cursor;
    private int turn = 0;
    private Sound sound;

    public GameModel(){
        piecesInGame = new HashSet<>();
        this.sound = new Sound(this);
    }

    public void setSound(Sound sound) {
        this.sound = sound;
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

    public Player[] getGamePlayers() {
        return gamePlayers;
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
            i+=1;
        }
    }

    public void select() {

        if (cursor.getSelectedPosition() != null) {
            Set<Move> moves = getPieceLegalMoves();
            for (Move move : moves) {
                if (move.getPosition().equals(cursor.getCurrentPosition())) {
                    move.execute();
                    notifyPlayers();
                    sound.playSound(move);
                    break;
                }
            }
        }
        cursor.select();
    }

    public boolean checkStalemate(Player player) {
        List<Piece> listPiecesInGame = new ArrayList<>(piecesInGame);
        for (Piece p : listPiecesInGame) {
            Set<Move> possibleMvs = p.getMoves(boardModel);
            filterMoves(possibleMvs,p);
            if (p.getColor() == player.getColor() && !possibleMvs.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMate() {
        return checkStalemate(gamePlayers[turn]) && getPlayerKing(gamePlayers[turn].getColor()).inCheck();
    }

    public King getPlayerKing(Piece.COLOR color) {
        for (Piece piece : piecesInGame) {
            if (piece instanceof King && piece.getColor() == color)
                return (King) piece;
        }
        return null;
    }

    public boolean timeEnded() {
        for (Player player: gamePlayers) {
            if (player.getClock().hasEnded())
                return true;
        }
        return false;
    }

    public boolean gameEnded() {
        return checkMate() || checkStalemate(gamePlayers[turn]) || timeEnded();
    }

    public Player winner() {
        if (checkMate() || timeEnded())
            return gamePlayers[(turn - 1 + gamePlayers.length) % gamePlayers.length];
        return null;
    }
}