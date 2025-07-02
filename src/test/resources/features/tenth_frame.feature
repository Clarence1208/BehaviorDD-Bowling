Feature: Tenth Frame

  Scenario: Player completes tenth frame with no bonus
    Given a player rolls 18 times knocking down 0 pins
    And the player knocks down 3 pins on the first roll of the tenth frame
    And the player knocks down 4 pins on the second roll of the tenth frame
    Then the total score should be 7 points

  Scenario: Player gets a spare in the tenth frame
    Given a player rolls 18 times knocking down 0 pins
    And the player knocks down 5 pins on the first roll of the tenth frame
    And the player knocks down 5 pins on the second roll of the tenth frame
    And the player rolls 7 pins as bonus
    Then the total score should be 17 points

  Scenario: Player gets a strike in the tenth frame
    Given a player rolls 18 times knocking down 0 pins
    And the player knocks down 10 pins on the first roll of the tenth frame
    And the player rolls 7 pins as bonus
    And the player rolls 2 pins as bonus
    Then the total score should be 19 points
