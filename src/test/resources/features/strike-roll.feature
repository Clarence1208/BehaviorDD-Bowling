Feature: Score a Strike

  Rule: A strike occurs when all 10 pins are knocked down on the first roll
  Rule: A strike scores 10 points plus the next two rolls

  Scenario: Player achieves a strike
    Given a player starts a new frame
    When the player knocks down 10 pins on the first roll
    Then the frame should be a strike
    And the frame should be complete
    And the frame score should be pending

  Scenario: Calculate strike score with next two rolls
    Given a player achieved a strike
    When the player's next two rolls knock down 4 and 3 pins respectively
    Then the strike frame should score 17 points
