Feature: Perfect Game

  Scenario: Player rolls a perfect game
    Given a player starts a new frame
    When the player rolls 12 strikes
    Then the total score should be 300 points
