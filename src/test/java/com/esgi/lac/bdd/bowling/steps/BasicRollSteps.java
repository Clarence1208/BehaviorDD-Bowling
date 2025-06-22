package com.esgi.lac.bdd.bowling.steps;

import com.esgi.lac.bdd.bowling.BowlingGame;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicRollSteps {

    private BowlingGame game;
    private int rollScore;

    @Given("a player rolls a bowling ball")
    public void a_player_rolls_a_bowling_ball() {
        game = new BowlingGame();
    }

    @When("{int} pins are knocked down")
    public void pins_are_knocked_down(int pins) {
        rollScore = game.roll(pins);
    }

    @Then("the roll should score {int} points")
    public void the_roll_should_score_points(int expectedScore) {
        assertThat(rollScore).isEqualTo(expectedScore);
    }
}