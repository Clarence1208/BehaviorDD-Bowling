package com.esgi.lac.bdd.bowling.steps;

import com.esgi.lac.bdd.bowling.BowlingGame;
import com.esgi.lac.bdd.bowling.Frame;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingSteps {

    private BowlingGame game;
    private int rollScore;
    private Frame currentFrame;
    private Frame nextFrame;
    private Exception lastException;

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

    // Error handling steps
    @And("the player tries to knock down {int} pins on the second roll")
    public void the_player_tries_to_knock_down_pins_on_the_second_roll(int pins) {
        try {
            currentFrame.roll(pins);
        } catch (Exception e) {
            this.lastException = e;
        }
    }

    @Then("an error should be thrown")
    public void an_error_should_be_thrown() {
        assertThat(lastException).isNotNull();
    }

    @And("the error message should indicate invalid pin count")
    public void the_error_message_should_indicate_invalid_pin_count() {
        assertThat(lastException).isInstanceOf(IllegalArgumentException.class);
        assertThat(lastException.getMessage()).contains("Invalid pin count");
    }

    @Then("trying to roll again should throw an error")
    public void trying_to_roll_again_should_throw_an_error() {
        try {
            currentFrame.roll(1);
            assertThat(false).as("Expected an exception to be thrown").isTrue();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
        }
    }

    @Given("a player starts a new game")
    public void a_player_starts_a_new_game() {
        game = new BowlingGame();
    }

    @When("the player bowls the following sequence:")
    public void the_player_bowls_the_following_sequence(DataTable dataTable) {
        var frames = dataTable.asMaps(String.class, String.class);
        
        for (var frameData : frames) {
            int frameNumber = Integer.parseInt(frameData.get("Frame"));
            String roll1 = frameData.get("Roll 1");
            String roll2 = frameData.get("Roll 2");
            String roll3 = frameData.get("Roll 3");
            
            Frame frame = game.startNewFrame();
            
            if (roll1 != null && !roll1.equals("[empty]") && !roll1.isEmpty()) {
                int pins1 = Integer.parseInt(roll1);
                frame.roll(pins1);
            }

            final boolean bonusState = roll3 != null && !roll3.equals("[empty]") && !roll3.isEmpty();
            if (frameNumber == 10) {
                if (roll2 != null && !roll2.equals("[empty]") && !roll2.isEmpty()) {
                    int pins2 = Integer.parseInt(roll2);
                    if (frame.isStrike()) {
                        frame.addBonusRoll(pins2);
                    } else {
                        frame.roll(pins2);
                    }
                }
                
                if (bonusState) {
                    int pins3 = Integer.parseInt(roll3);
                    frame.addBonusRoll(pins3);
                }
            } else {
                if (roll2 != null && !roll2.equals("[empty]") && !roll2.isEmpty() && !frame.isStrike()) {
                    int pins2 = Integer.parseInt(roll2);
                    frame.roll(pins2);
                }
                
                if (bonusState) {
                    int pins3 = Integer.parseInt(roll3);
                    frame.addBonusRoll(pins3);
                }
            }
        }
        
        addBonusRollsForDataTableFrames();
    }

    private void addBonusRollsForDataTableFrames() {
        var frames = game.getFrames();
        for (int i = 0; i < Math.min(frames.size() - 1, 9); i++) {
            Frame currentFrame = frames.get(i);
            Frame nextFrame = frames.get(i + 1);
            
            if (currentFrame.isStrike()) {
                if (currentFrame.getBonusRoll() == null) {
                    currentFrame.addBonusRoll(nextFrame.getFirstRoll());
                }
                if (currentFrame.getSecondBonusRoll() == null) {
                    if (nextFrame.isStrike() && i + 2 < frames.size()) {
                        currentFrame.addBonusRoll(frames.get(i + 2).getFirstRoll());
                    } else if (nextFrame.getSecondRoll() != null) {
                        currentFrame.addBonusRoll(nextFrame.getSecondRoll());
                    }
                }
            } else if (currentFrame.isSpare()) {
                if (currentFrame.getBonusRoll() == null) {
                    currentFrame.addBonusRoll(nextFrame.getFirstRoll());
                }
            }
        }
    }

    @Then("the game should be complete")
    public void the_game_should_be_complete() {
        assertThat(game.isComplete()).isTrue();
    }

    @When("the player achieves a strike in the first frame")
    public void the_player_achieves_a_strike_in_the_first_frame() {
        Frame frame1 = game.startNewFrame();
        frame1.roll(10);
        assertThat(frame1.isStrike()).isTrue();
    }

    @When("the player achieves a spare in the second frame with {int} and {int} pins")
    public void the_player_achieves_a_spare_in_the_second_frame_with_and_pins(int first, int second) {
        Frame frame2 = game.startNewFrame();
        frame2.roll(first);
        frame2.roll(second);
        assertThat(frame2.isSpare()).isTrue();
    }

    @When("the player knocks down {int} pins in the third frame")
    public void the_player_knocks_down_pins_in_the_third_frame(int pins) {
        Frame frame3 = game.startNewFrame();
        frame3.roll(pins);
        
        if (game.getFrames().size() >= 2) {
            Frame frame1 = game.getFrames().get(0);
            Frame frame2 = game.getFrames().get(1);
            
            if (frame1.isStrike()) {
                frame1.addBonusRoll(frame2.getFirstRoll());
                frame1.addBonusRoll(frame2.getSecondRoll());
            }
            
            if (frame2.isSpare()) {
                frame2.addBonusRoll(frame3.getFirstRoll());
            }
        }
    }

    @Then("the first frame should score {int} points")
    public void the_first_frame_should_score_points(int expectedScore) {
        Frame firstFrame = game.getFrames().getFirst();
        assertThat(firstFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @Then("the second frame should score {int} points")
    public void the_second_frame_should_score_points(int expectedScore) {
        Frame secondFrame = game.getFrames().get(1);
        assertThat(secondFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @Then("the third frame should score {int} points")
    public void the_third_frame_should_score_points(int expectedScore) {
        Frame thirdFrame = game.getFrames().get(2);
        assertThat(thirdFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @When("the player achieves three consecutive strikes")
    public void the_player_achieves_three_consecutive_strikes() {
        Frame frame1 = game.startNewFrame();
        frame1.roll(10);
        
        Frame frame2 = game.startNewFrame();
        frame2.roll(10);
        
        Frame frame3 = game.startNewFrame();
        frame3.roll(10);
        
        assertThat(frame1.isStrike()).isTrue();
        assertThat(frame2.isStrike()).isTrue();
        assertThat(frame3.isStrike()).isTrue();
    }

    @When("the player's fourth roll knocks down {int} pins")
    public void the_player_s_fourth_roll_knocks_down_pins(int pins) {
        Frame frame4 = game.startNewFrame();
        frame4.roll(pins);
        
        if (game.getFrames().size() >= 3) {
            Frame frame1 = game.getFrames().get(0);
            Frame frame2 = game.getFrames().get(1);
            Frame frame3 = game.getFrames().get(2);
            
            frame1.addBonusRoll(10);
            frame1.addBonusRoll(10);
            
            frame2.addBonusRoll(10);
            frame2.addBonusRoll(pins);
            
            frame3.addBonusRoll(pins);
        }
    }

    @Then("the first strike frame should score {int} points")
    public void the_first_strike_frame_should_score_points(int expectedScore) {
        Frame firstFrame = game.getFrames().getFirst();
        assertThat(firstFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @Then("the second strike frame should score {int} points")
    public void the_second_strike_frame_should_score_points(int expectedScore) {
        Frame secondFrame = game.getFrames().get(1);
        assertThat(secondFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @Then("the third strike frame should score {int} points")
    public void the_third_strike_frame_should_score_points(int expectedScore) {
        Frame thirdFrame = game.getFrames().get(2);
        assertThat(thirdFrame.getCompleteScore()).isEqualTo(expectedScore);
    }

    @When("the player's fifth roll knocks down {int} pins")
    public void the_player_s_fifth_roll_knocks_down_pins(int pins) {
        Frame frame4 = game.getFrames().get(3);
        frame4.roll(pins);
        
        Frame frame3 = game.getFrames().get(2);
        frame3.addBonusRoll(pins);
    }
}
