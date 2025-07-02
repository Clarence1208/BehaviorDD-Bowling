Feature: Strike

  Scenario: Player gets a strike
    Given a player starts a new frame
    When the player knocks down 10 pins on the first roll
    Then the frame should be complete
    And the frame should be a strike
    And the frame score should be pending

  Scenario: Player rolls a strike and then knocks down 3 and 4
    Given a player starts a new frame
    When the player knocks down 10 pins on the first roll
    And the player rolls again and knocks down 3 pins
    And the player rolls again and knocks down 4 pins
    Then the frame should be a strike
    And the frame should score 17 points
