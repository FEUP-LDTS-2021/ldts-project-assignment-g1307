package model.game.pieces.movingBehaviours;

import model.game.Position;

import java.util.*;

public class LStrategy extends PreDetermined{
    public LStrategy() {
        super(Arrays.asList(new Position(-2,-1), new Position(-2,1), new Position(-1,-2), new Position(-1,2),
                new Position(1,-2), new Position(1,2), new Position(2,-1), new Position(2,1)));
    }
}
