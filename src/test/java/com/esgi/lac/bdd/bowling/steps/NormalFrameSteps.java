package com.esgi.lac.bdd.bowling.steps;

import com.esgi.lac.bdd.bowling.BowlingGame;
import com.esgi.lac.bdd.bowling.Frame;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.esgi.lac.bdd.bowling.Frame.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameSteps {

    private BowlingGame game;

    private Frame currentFrame;

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
        assertThat(currentFrame.getScore()).isEqualTo(expectedScore);
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
        FrameScore frameScore = currentFrame.getFrameScore();
        assertThat(frameScore.isPending()).isTrue();
        assertThat(frameScore.getScore()).isEqualTo(expectedScore);
    }

}
