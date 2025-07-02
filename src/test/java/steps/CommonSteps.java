package steps;

import com.esgi.lac.bdd.bowling.BowlingGame;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class CommonSteps {

    private final BowlingGameContext context;

    public CommonSteps(BowlingGameContext context) {
        this.context = context;
    }

    // Givens
    @Given("a player starts a new frame")
    public void a_player_starts_a_new_frame() {
        context.setGame(new BowlingGame());
    }

    @Given("a player rolls a bowling ball")
    public void a_player_rolls_a_bowling_ball() {
        context.setGame(new BowlingGame());
    }

    @Given("a player rolls {int} times knocking down {int} pins")
    public void a_player_rolls_times_knocking_down_pins(Integer times, Integer pins) {
        if (context.getGame() == null) {
            context.setGame(new BowlingGame());
        }
        for (int i = 0; i < times; i++) {
            context.getGame().roll(pins);
        }
    }

    @Given("a player bowls {int} consecutive strikes")
    public void a_player_bowls_consecutive_strikes(Integer count) {
        context.setGame(new BowlingGame());
        for (int i = 0; i < count; i++) {
            context.getGame().roll(10);
        }
    }

    @Given("a player misses all pins in all rolls")
    public void a_player_misses_all_pins_in_all_rolls() {
        context.setGame(new BowlingGame());
        for (int i = 0; i < 20; i++) {
            context.getGame().roll(0);
        }
    }

    @Given("a player achieves spares in all 10 frames by knocking down {int} pins then {int} pins")
    public void a_player_achieves_spares_in_all_frames(Integer firstRoll, Integer secondRoll) {
        context.setGame(new BowlingGame());
        for (int i = 0; i < 10; i++) {
            context.getGame().roll(firstRoll);
            context.getGame().roll(secondRoll);
        }
    }

    @Given("the player knocks down {int} pins on the bonus roll")
    public void the_player_knocks_down_pins_on_the_bonus_roll(Integer pins) {
        context.getGame().roll(pins);
    }

    // Whens
    @When("{int} pins are knocked down")
    public void pins_are_knocked_down(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the player knocks down {int} pins on the first roll")
    public void the_player_knocks_down_pins_on_the_first_roll(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the player knocks down {int} pins on the second roll")
    public void the_player_knocks_down_pins_on_the_second_roll(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the player rolls again and knocks down {int} pins")
    public void the_player_rolls_again_and_knocks_down_pins(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the player rolls {int} strikes")
    public void the_player_rolls_strikes(Integer count) {
        for (int i = 0; i < count; i++) {
            context.getGame().roll(10);
        }
    }

    @When("the player knocks down {int} pins on the first roll of the tenth frame")
    public void the_player_knocks_down_pins_on_the_first_roll_of_the_tenth_frame(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the player knocks down {int} pins on the second roll of the tenth frame")
    public void the_player_knocks_down_pins_on_the_second_roll_of_the_tenth_frame(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the player rolls {int} pins as bonus")
    public void the_player_rolls_pins_as_bonus(Integer pins) {
        context.getGame().roll(pins);
    }

    @When("the game is scored")
    public void the_game_is_scored() {
        // Pas besoin de code ici, le score est calculé dans le Then
    }

    // Thens
    @Then("the frame should score {int} points")
    public void the_frame_should_score_points(Integer expected) {
        Assert.assertEquals((int) expected, context.getGame().scoreForFrame(0));
    }

    @Then("the frame should be complete")
    public void the_frame_should_be_complete() {
        Assert.assertTrue(context.getGame().isFrameComplete());
    }

    @Then("the frame score should be pending")
    public void the_frame_score_should_be_pending() {
        Assert.assertTrue(context.getGame().isScorePending());
    }

    @Then("the roll should score {int} points")
    public void the_roll_should_score_points(Integer expectedScore) {
        Assert.assertEquals(expectedScore.intValue(), context.getGame().score());
    }

    @Then("the total score should be {int} points")
    public void the_total_score_should_be(Integer expected) {
        Assert.assertEquals((int) expected, context.getGame().score());
    }
}
