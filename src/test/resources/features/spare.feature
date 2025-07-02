Feature: Score a Spare

  Rule: A spare occurs when all 10 pins are knocked down in exactly two rolls
  Rule: A spare scores 10 points plus the next roll

  Scenario: Player achieves a spare
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    And the player knocks down 4 pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending

  Scenario: Player completes a spare and scores with next roll
    Given a player starts a new frame
    When the player knocks down 4 pins on the first roll
    And the player knocks down 6 pins on the second roll
    And the player rolls again and knocks down 5 pins
    Then the frame should be a spare
    And the frame should score 15 points
