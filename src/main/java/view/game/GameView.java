package view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.game.GameModel;
import model.game.Position;
import model.game.board.BoardCase;
import model.game.board.SquareBoard;
import model.game.builder.GameBuilder;
import model.game.builder.StandardChessGame;
import model.game.pieces.Piece;
import model.menu.MenuModel;
import view.View;
import view.menu.MenuView;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameView extends View<GameModel> {
    public GameView(GameModel model) {
        super(model,"CHEQ_TT.TTF", 55, 10, 20);
    }

    GameView(GameModel model, Screen screen, TextGraphics textGraphics) {
        super(model,"CHEQ_TT.TTF", screen, textGraphics);
    }

    public static void main(String[] args) {
        GameModel gameModel;
        GameBuilder gameBuilder = new StandardChessGame();
        gameModel = gameBuilder.buildPieces().buildRules().getResults();
        View<?> gameView = new GameView(gameModel);
        try {
            gameView.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void drawBoard() {
        for (BoardCase boardCase: model.getBoardModel().getCases()) {
            Color caseColor = boardCase.caseColor();
            String hex = String.format("#%02x%02x%02x", caseColor.getRed(), caseColor.getGreen(), caseColor.getBlue());
            graphics.setBackgroundColor(TextColor.RGB.Factory.fromString(hex));
            drawPiece(boardCase.position());
        }
    }

    void drawPiece(Position position) {
        List<Piece> result = model.getPiecesInGame().stream()
                .filter(item -> item.getPosition().equals(position))
                .collect(Collectors.toList());
        final TerminalPosition terminalPosition = new TerminalPosition(position.getCol(),position.getRow());
        if (!result.isEmpty()) {
            graphics.setForegroundColor(TextColor.Factory.fromString(result.get(0).getColor().toString()));
            graphics.setCharacter(terminalPosition, result.get(0).getFigure());
        }else {
            graphics.setCharacter(terminalPosition, ' ');
        }
    }

    @Override
    public void draw() throws IOException {
        clear();

        drawBoard();

        refresh();
    }

    @Override
    protected void clear() {
        super.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#312e2b"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(getWidth(),getHeight()),' ');
    }
}
