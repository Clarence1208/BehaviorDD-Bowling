Feature: Basic Roll Scoring
  As a bowling player
  I want each roll to be scored correctly
  So that I can track individual roll performance

  Scenario: Score a roll with some pins knocked down
    Given a player rolls a bowling ball
    When 6 pins are knocked down
    Then the roll should score 6 points

  Scenario: Score a roll with no pins knocked down
    Given a player rolls a bowling ball
    When 0 pins are knocked down
    Then the roll should score 0 points 