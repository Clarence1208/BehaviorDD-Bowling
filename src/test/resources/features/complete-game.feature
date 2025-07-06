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

    Scenario: Mixed game with various frame types
      Given a player starts a new game
      When the player bowls the following sequence:
        | Frame | Roll 1 | Roll 2  | Roll 3  |
        | 1     | 10     | [empty] | [empty] |
        | 2     | 7      | 3       | [empty] |
        | 3     | 9      | 1       | [empty] |
        | 4     | 10     | [empty] | [empty] |
        | 5     | 0      | 8       | [empty] |
        | 6     | 8      | 2       | [empty] |
        | 7     | 10     | [empty] | [empty] |
        | 8     | 6      | 3       | [empty] |
        | 9     | 7      | 2       | [empty] |
        | 10    | 10     | 5       | 3       |
      Then the total score should be 160 points
      And the game should be complete

    Scenario: Strike followed by spare
      Given a player starts a new game
      When the player achieves a strike in the first frame
      And the player achieves a spare in the second frame with 7 and 3 pins
      And the player knocks down 5 pins in the third frame
      Then the first frame should score 20 points
      And the second frame should score 15 points
      And the third frame should score 5 points

    Scenario: Multiple consecutive strikes
      Given a player starts a new game
      When the player achieves three consecutive strikes
      And the player's fourth roll knocks down 5 pins
      Then the first strike frame should score 30 points
      And the second strike frame should score 25 points
      And the third strike frame should score 15 points

    Scenario: Three consecutive strikes
      Given a player starts a new game
      When the player achieves three consecutive strikes
      And the player's fourth roll knocks down 5 pins
      And the player's fifth roll knocks down 3 pins
      Then the first strike frame should score 30 points
      And the second strike frame should score 25 points
      And the third strike frame should score 18 points
