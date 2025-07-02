Feature: Score a Single Roll

  Rule: The score of a roll equals the number of pins knocked down

  Scenario: Player knocks down some pins
    Given a player rolls a bowling ball
    When 6 pins are knocked down
    Then the roll should score 6 points

   Scenario: Player misses all pins
    Given a player rolls a bowling ball
    When 0 pins are knocked down
    Then the roll should score 0 points
