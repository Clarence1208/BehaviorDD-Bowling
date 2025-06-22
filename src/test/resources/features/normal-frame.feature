Feature: Normal Frame Scoring
  As a bowling player
  I want normal frames to be scored as the sum of two rolls
  So that frames with less than 10 total pins are scored correctly

  Scenario: Score a frame with two regular rolls
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    And the player knocks down 2 pins on the second roll
    Then the frame should score 8 points
    And the frame should be complete

  Scenario: Score a frame with first roll miss
    Given a player starts a new frame
    When the player knocks down 0 pins on the first roll
    And the player knocks down 7 pins on the second roll
    Then the frame should score 7 points
    And the frame should be complete

  Scenario: Score a frame with both rolls missing
    Given a player starts a new frame
    When the player knocks down 0 pins on the first roll
    And the player knocks down 0 pins on the second roll
    Then the frame should score 0 points
    And the frame should be complete

  Scenario: Cannot score an incomplete frame
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    Then the frame should not be complete
    And the frame score should be pending with 6 points