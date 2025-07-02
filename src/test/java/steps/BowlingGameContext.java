package steps;

import com.esgi.lac.bdd.bowling.BowlingGame;

public class BowlingGameContext {

    private BowlingGame game;

    public BowlingGame getGame() {
        return game;
    }

    public void setGame(BowlingGame game) {
        this.game = game;
    }
}
