Feature: Spare Scoring
  As a bowling player
  I want spares to be scored as 10 plus the next roll
  So that knocking down all pins in two rolls gives bonus points

  Scenario: Achieve a spare
    Given a player starts a new frame
    When the player knocks down 6 pins on the first roll
    And the player knocks down 4 pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending with 10 points

  Scenario: Calculate spare score with next roll
    Given a player achieved a spare with 6 and 4 pins
    When the player's next roll knocks down 5 pins
    Then the spare frame should score 15 points

  Scenario: Spare with different pin combinations
    Given a player starts a new frame
    When the player knocks down 1 pins on the first roll
    And the player knocks down 9 pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending with 10 points

  Scenario: Spare with 5 and 5 pins
    Given a player starts a new frame
    When the player knocks down 5 pins on the first roll
    And the player knocks down 5 pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending with 10 points

  Scenario: Spare followed by multiple rolls
    Given a player achieved a spare with 7 and 3 pins
    When the player's next roll knocks down 4 pins
    And the player rolls again knocking down 2 pins
    Then the spare frame should score 14 points
    And the spare frame should be complete


# Added only for experimentation with the framework
  Scenario Outline: Different spare combinations
    Given a player starts a new frame
    When the player knocks down <first> pins on the first roll
    And the player knocks down <second> pins on the second roll
    Then the frame should be a spare
    And the frame score should be pending with 10 points

    Examples:
      | first | second |
      | 1     | 9      |
      | 2     | 8      |
      | 3     | 7      |
      | 4     | 6      |
      | 5     | 5      |
      | 6     | 4      |
      | 7     | 3      |
      | 8     | 2      |
      | 9     | 1      |

  Scenario Outline: Spare scoring with next roll
    Given a player achieved a spare with <first> and <second> pins
    When the player's next roll knocks down <next> pins
    Then the spare frame should score <total> points

    Examples:
      | first | second | next | total |
      | 1     | 9      | 5    | 15    |
      | 5     | 5      | 3    | 13    |
      | 7     | 3      | 10   | 20    |
      | 9     | 1      | 0    | 10    |