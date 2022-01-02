package model.game.board;

import model.game.Position;

import java.awt.*;

public record BoardCase(Color color, Position position) {

    public Position getPosition() {
        return position;
    }
    public Color caseColor() {
        return color;
    }
}
