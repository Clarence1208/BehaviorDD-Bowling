package com.esgi.lac.bdd.bowling.steps;

import com.esgi.lac.bdd.bowling.BowlingGame;
import com.esgi.lac.bdd.bowling.Frame;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingSteps {

    private BowlingGame game;
    private int rollScore;
    private Frame currentFrame;
    private Frame nextFrame;

    /// basic roll steps

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

    /// normal frame steps

    @Given("a player starts a new frame")
    public void a_player_starts_a_new_frame() {
        game = new BowlingGame();
        currentFrame = game.startNewFrame();
    }

    @When("the player knocks down {int} pins on the first roll")
    public void the_player_knocks_down_pins_on_the_first_roll(int pins) {
        currentFrame.roll(pins);
    }

    @And("the player knocks down {int} pins on the second roll")
    public void the_player_knocks_down_pins_on_the_second_roll(int pins) {
        currentFrame.roll(pins);
    }

    @Then("the frame should score {int} points")
    public void the_frame_should_score_points(int expectedScore) {
        assertThat(currentFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @And("the frame should be complete")
    public void the_frame_should_be_complete() {
        assertThat(currentFrame.isComplete()).isTrue();
    }

    @Then("the frame should not be complete")
    public void the_frame_should_not_be_complete() {
        assertThat(currentFrame.isComplete()).isFalse();
    }

    @And("the frame score should be pending with {int} points")
    public void the_frame_score_should_be_pending(int expectedScore) {
        Frame.FrameScore frameScore = currentFrame.getFrameScore();
        assertThat(frameScore.pending()).isTrue();
        assertThat(frameScore.score()).isEqualTo(expectedScore);
    }

    /// spare frame steps

    @Then("the frame should be a spare")
    public void the_frame_should_be_a_spare() {
        assertThat(currentFrame.isSpare()).isTrue();
    }

    @Given("a player achieved a spare with {int} and {int} pins")
    public void a_player_achieved_a_spare_with_and_pins(int firstRoll, int secondRoll) {
        game = new BowlingGame();
        currentFrame = game.startNewFrame();
        currentFrame.roll(firstRoll);
        currentFrame.roll(secondRoll);

        assertThat(currentFrame.isSpare()).isTrue();
    }

    @When("the player's next roll knocks down {int} pins")
    public void the_players_next_roll_knocks_down_pins(int pins) {
        nextFrame = game.startNewFrame();
        nextFrame.roll(pins);
        currentFrame.addBonusRoll(pins);
    }

    @Then("the spare frame should score {int} points")
    public void the_spare_frame_should_score_points(int expectedScore) {
        assertThat(currentFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @And("the player rolls again knocking down {int} pins")
    public void the_player_rolls_again_knocking_down_pins(int pins) {
        nextFrame.roll(pins);
    }

    @And("the spare frame should be complete")
    public void the_spare_frame_should_be_complete() {
        assertThat(currentFrame.isComplete()).isTrue();
        assertThat(currentFrame.getFrameScore().pending()).isFalse();
    }
}
