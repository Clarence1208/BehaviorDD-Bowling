package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;

public class StrikeSteps {

    private final BowlingGameContext context;

    public StrikeSteps(BowlingGameContext context) {
        this.context = context;
    }

    @Then("the frame should be a strike")
    public void the_frame_should_be_a_strike() {
        Assert.assertTrue(context.getGame().isStrike());
    }
}
