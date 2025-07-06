Feature: Error Handling
  As a bowling system
  I want to handle invalid inputs gracefully
  So that the game maintains integrity

  Scenario: Invalid frame - too many pins knocked down
    Given a player starts a new frame
    When the player knocks down 5 pins on the first roll
    And the player tries to knock down 7 pins on the second roll
    Then an error should be thrown
    And the error message should indicate invalid pin count

  Scenario: Cannot roll in completed normal frame
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    And the player knocks down 2 pins on the second roll
    And the frame should be complete
    Then trying to roll again should throw an error

  Scenario: Cannot roll in completed strike frame
    Given a player starts a new frame
    When the player knocks down 10 pins on the first roll
    And the frame should be complete
    Then trying to roll again should throw an error 