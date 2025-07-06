Feature: Score a Complete Game

  Rule: A game consists of exactly 10 frames

  Scenario: Perfect game
    Given a player bowls 12 consecutive strikes
    When the game is scored
    Then the total score should be 300 points

  Scenario: All gutters
    Given a player misses all pins in all rolls
    When the game is scored
    Then the total score should be 0 points

  Scenario: All spares with 5 pins each time
    Given a player achieves spares in all 10 frames by knocking down 5 pins then 5 pins
    And the player knocks down 5 pins on the bonus roll
    When the game is scored
    Then the total score should be 150 points

  Scenario: Cannot roll in completed frame
    Given a player has completed a frame
    When the player tries to roll again in the same frame
    Then an error should be thrown

  Scenario: Cannot add bonus roll to normal frame
    Given a player has a normal frame (not strike or spare)
    When the player tries to add a bonus roll
    Then an error should be thrown