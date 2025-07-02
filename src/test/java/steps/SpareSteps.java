package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;

public class SpareSteps {

    private final BowlingGameContext context;

    public SpareSteps(BowlingGameContext context) {
        this.context = context;
    }

    @Then("the frame should be a spare")
    public void the_frame_should_be_a_spare() {
        Assert.assertTrue(context.getGame().isSpare());
    }
}
