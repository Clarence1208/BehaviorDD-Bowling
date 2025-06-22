Feature: Spare Scoring
  As a bowling player
  I want spares to be scored as 10 plus the next roll
  So that knocking down all pins in two rolls gives bonus points

  Scenario: Achieve a spare
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    And the player knocks down 4 pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending with 10 points

  Scenario: Calculate spare score with next roll
    Given a player achieved a spare with 6 and 4 pins
    When the player's next roll knocks down 5 pins
    Then the spare frame should score 15 points

  Scenario: Spare with different pin combinations
    Given a player starts a new frame
    When the player knocks down 1 pins on the first roll
    And the player knocks down 9 pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending with 10 points

  Scenario: Spare followed by multiple rolls
    Given a player achieved a spare with 7 and 3 pins
    When the player's next roll knocks down 4 pins
    And the player rolls again knocking down 2 pins
    Then the spare frame should score 14 points
    And the spare frame should be complete