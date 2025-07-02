Feature: Score a Normal Frame

  Rule: A normal frame score is the sum of two rolls when fewer than 10 pins total are knocked down

  Scenario: Player scores in two rolls
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    And the player knocks down 2 pins on the second roll
    Then the frame should score 8 points
    And the frame should be complete
