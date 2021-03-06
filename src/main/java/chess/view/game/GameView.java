package chess.view.game;

import chess.model.game.pieces.King;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import chess.model.game.GameModel;
import chess.model.game.Position;
import chess.model.game.board.BoardCase;
import chess.model.game.move.Move;
import chess.model.game.pieces.Piece;
import chess.model.game.player.Player;
import chess.view.View;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameView extends View<GameModel> {
    public GameView(GameModel model) {
        super(model,"CHEQ_TT.TTF", 55, 10, 20);
    }

    GameView(GameModel model, Screen screen, TextGraphics textGraphics) {
        super(model,"CHEQ_TT.TTF", screen, textGraphics);
    }

    void drawBoard() {
        for (BoardCase boardCase: model.getBoardModel().getCases()) {
            graphics.setBackgroundColor(TextColor.RGB.Factory.fromString(getBackgroundCaseColor(boardCase)));
            drawPiece(boardCase.position());
        }
    }

    String getBackgroundCaseColor(BoardCase boardCase) {
        if (boardCase.getPosition().equals(model.getCursor().getCurrentPosition()))
            return "#ff0000"; // red

        Set<Position> legalPos = new HashSet<>();

        for (Move move : model.getPieceLegalMoves()) legalPos.add(move.getPosition());

        if (legalPos.contains(boardCase.position()))
            return "#f94d6f"; // a lighter red/rose
        Color caseColor = boardCase.caseColor();
        return String.format("#%02x%02x%02x", caseColor.getRed(), caseColor.getGreen(), caseColor.getBlue());
    }

    void drawPiece(Position position) {
        List<Piece> result = model.getPiecesInGame().stream()
                .filter(item -> item.getPosition().equals(position))
                .collect(Collectors.toList());
        final TerminalPosition terminalPosition = new TerminalPosition(position.getCol(),position.getRow());
        if (!result.isEmpty()) {
            if (result.get(0) instanceof King king && king.inCheck() && !king.getPosition().equals(model.getCursor().getCurrentPosition()))
                graphics.setBackgroundColor(TextColor.RGB.Factory.fromString("#9477cd"));
            graphics.setForegroundColor(TextColor.Factory.fromString(result.get(0).getColor().toString()));
            graphics.setCharacter(terminalPosition, result.get(0).getFigure());
        }else {
            graphics.setCharacter(terminalPosition, ' ');
        }
    }

    void drawClock() {
        Player[] players = model.getGamePlayers();
        TerminalPosition terminalPosition = new TerminalPosition(getHeightCenter() + 5, getWidthCenter() - 2 * players.length +1);

        for (Player player : players) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(player.getColor().toString()));
            graphics.setForegroundColor(TextColor.Factory.fromString("#615e5b"));
            graphics.putString(terminalPosition, player.getClock().toString());
            terminalPosition = new TerminalPosition(terminalPosition.getColumn(),terminalPosition.getRow() - 5);
        }
    }

    @Override
    public void draw() throws IOException {
        clear();

        drawBoard();

        drawClock();

        refresh();
    }

}
