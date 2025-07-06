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

    // strike frame steps

    @Then("the frame should be a strike")
    public void the_frame_should_be_a_strike() {
        assertThat(currentFrame.isStrike()).isTrue();
    }

    @Given("a player achieved a strike")
    public void a_player_achieved_a_strike() {
        game = new BowlingGame();
        currentFrame = game.startNewFrame();
        currentFrame.roll(10);
        assertThat(currentFrame.isStrike()).isTrue();
    }

    @When("the player's next two rolls knock down {int} and {int} pins respectively")
    public void the_players_next_two_rolls_knock_down_and_pins_respectively(int first, int second) {
        nextFrame = game.startNewFrame();
        nextFrame.roll(first);
        nextFrame.roll(second);
        currentFrame.addBonusRoll(first);
        currentFrame.addBonusRoll(second);
    }

    @Then("the strike frame should score {int} points")
    public void the_strike_frame_should_score_points(int expectedScore) {
        assertThat(currentFrame.getCompleteScore()).isEqualTo(expectedScore);
    }
    @And("the frame score should be pending")
    public void the_frame_score_should_be_pending() {
        Frame.FrameScore frameScore = currentFrame.getFrameScore();
        assertThat(frameScore.pending()).isTrue();
    }
    //tenth frame steps
    @Given("a player reaches the tenth frame with a score of {int}")
    public void a_player_reaches_the_tenth_frame_with_a_score_of(int expectedScore) {
        game = new BowlingGame();

        Frame f1 = game.startNewFrame();
        f1.roll(5);
        f1.roll(5);
        f1.addBonusRoll(6);

        Frame f2 = game.startNewFrame();
        f2.roll(5);
        f2.roll(4);

        Frame f3 = game.startNewFrame();
        f3.roll(6);
        f3.roll(3);

        Frame f4 = game.startNewFrame();
        f4.roll(7);
        f4.roll(2);

        Frame f5 = game.startNewFrame();
        f5.roll(10);
        f5.addBonusRoll(3);
        f5.addBonusRoll(3);

        Frame f6 = game.startNewFrame();
        f6.roll(5);
        f6.roll(4);

        Frame f7 = game.startNewFrame();
        f7.roll(4);
        f7.roll(6);
        f7.addBonusRoll(1);

        Frame f8 = game.startNewFrame();
        f8.roll(5);
        f8.roll(5);
        f8.addBonusRoll(2);

        Frame f9 = game.startNewFrame();
        f9.roll(6);
        f9.roll(3);


        assertThat(game.getTotalScore()).isEqualTo(expectedScore);

        // Start the 10th frame
        currentFrame = game.startNewFrame();
    }


        @And("the player knocks down {int} pins on the bonus roll")
        public void the_player_knocks_down_pins_on_the_bonus_roll(int pins) {
            currentFrame.addBonusRoll(pins);
        }

        @And("the player knocks down {int} pins on the first bonus roll")
        public void the_player_knocks_down_pins_on_the_first_bonus_roll(int pins) {
            currentFrame.addBonusRoll(pins);
        }

        @And("the player knocks down {int} pins on the second bonus roll")
        public void the_player_knocks_down_pins_on_the_second_bonus_roll(int pins) {
            currentFrame.addBonusRoll(pins);
        }

        @And("the game should be complete with a total score of {int} points")
        public void the_game_should_be_complete_with_a_total_score_of(int expectedTotalScore) {
            assertThat(game.isComplete()).isTrue();
            assertThat(game.getTotalScore()).isEqualTo(expectedTotalScore);
        }

        //complete game steps
        @Given("a player bowls 12 consecutive strikes")
        public void a_player_bowls_12_consecutive_strikes() {
            game = new BowlingGame();
            for (int i = 0; i < 9; i++) {
                Frame frame = game.startNewFrame();
                frame.roll(10);
                frame.addBonusRoll(10);
                frame.addBonusRoll(10);
            }

            // Tenth frame
            Frame tenth = game.startNewFrame();
            tenth.roll(10); // first roll
            tenth.addBonusRoll(10); // first bonus
            tenth.addBonusRoll(10); // second bonus
        }

    @Given("a player misses all pins in all rolls")
    public void a_player_misses_all_pins_in_all_rolls() {
        game = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            Frame frame = game.startNewFrame();
            frame.roll(0);
            frame.roll(0);
        }
    }

    @Given("a player achieves spares in all 10 frames by knocking down 5 pins then 5 pins")
    public void a_player_achieves_all_spares() {
        game = new BowlingGame();
        for (int i = 0; i < 9; i++) {
            Frame frame = game.startNewFrame();
            frame.roll(5);
            frame.roll(5);
            frame.addBonusRoll(5);
        }

        // 10th frame
        currentFrame = game.startNewFrame();
        currentFrame.roll(5);
        currentFrame.roll(5);
    }

    @When("the game is scored")
    public void the_game_is_scored() {
    }

    @Then("the total score should be {int} points")
    public void the_total_score_should_be_points(int expected) {
        assertThat(game.isComplete()).isTrue();
        assertThat(game.getTotalScore()).isEqualTo(expected);
    }

}
