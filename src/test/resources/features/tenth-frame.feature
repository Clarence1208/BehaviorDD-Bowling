Feature: Score the Tenth Frame

  Rule: The tenth frame allows bonus rolls for strikes and spares
  Rule: The game is complete after the tenth frame

  Scenario: Normal tenth frame
    Given a player reaches the tenth frame with a score of 100
    When the player knocks down 6 pins on the first roll
    And the player knocks down 2 pins on the second roll
    Then the frame should score 8 points
    And the game should be complete with a total score of 108 points

  Scenario: Tenth frame spare
    Given a player reaches the tenth frame with a score of 100
    When the player knocks down 5 pins on the first roll
    And the player knocks down 5 pins on the second roll
    And the player knocks down 5 pins on the bonus roll
    Then the frame should score 15 points
    And the game should be complete with a total score of 115 points

  Scenario: Tenth frame strike
    Given a player reaches the tenth frame with a score of 100
    When the player knocks down 10 pins on the first roll
    And the player knocks down 4 pins on the first bonus roll
    And the player knocks down 3 pins on the second bonus roll
    Then the frame should score 17 points
    And the game should be complete with a total score of 117 points
