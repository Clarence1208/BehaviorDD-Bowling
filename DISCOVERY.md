# DISCOVERY FOR BOWLING

### 1. Roll

## Feature: Make a normal tour

Rule: The score is equal to the knocked down pins

Scenario: A player knocks 6 pins on a roll

    Given: A player roll a bowling ball and knocked down 6 pins
    When: the score is calculated
    Then: the score of the roll should be 6

Rule: A tour is a sequence of two rolls and the score of a tour is the sum of the two rolls

Scenario: A player plays a normal tour

    Given: A player rolls a first bowling ball and knocks down 6 pins
    AND: the player rolls a second time and knocks down 2 pins
    When: The score is calculated for the tour
    Then: The tour has ended and the score of the tour should be 8

Rule: A tour can be constituted of two missed rolls

Scenario: A player plays a tour with two missed rolls

    Given: A player rolls a first bowling ball and knocks down 0 pins
    AND: the player rolls a second time and knocks down 0 pins
    When: The score is calculated for the tour
    Then: The tour has ended and the score of the tour should be 0

## Feature: Make a strike

Rule: A turn is over when all pins are knocked down

Scenario: A player plays a strike on its first roll

    Given: A player rolls a first bowling ball and knocks down 10 pins
    When: The roll is finished
    Then: The turn is over

Rule: A strike score is addition of the next two rolls plus 10

Scenario: A player plays a strike on its first roll

    Given: A player rolls a first bowling ball and knocks down 10 pins
    And: the player play a second tour and scores 7 pins
    When: The score is calculated for the tour
    Then: The score of the first tour should be 17

## Feature: Make a spare

Rule: A spare is when all pins are knocked down in two rolls

Scenario: A player plays a spare on its second roll

    Given: A player rolls a first bowling ball and knocks down 6 pins
    And: the player rolls a second bowling ball and knocks down 4 pins
    When: The score is calculated for the tour
    Then: The tour has ended and the score is pending the next roll

Rule: A spare score is addition of the next roll plus 10
Scenario: A player plays a spare on its second roll

    Given: A player rolls a first bowling ball and knocks down 6 pins
    And: the player rolls a second bowling ball and knocks down 4 pins
    And: the player rolls a third bowling ball and knocks down 5 pins
    When: The score is calculated for the tour
    Then: The score of the first tour should be 15

## Feature: Full game

Rule: A game is a sequence of 10 tours

Scenario: A game is finished when the 10th tour is finished

    Given: A player who played 9 tours with a score of 8 for each tour
    And: the player finishes is 10th tour with 6 pins down
    When: The score is calculated for the game
    Then: The game is finished and the score should be 78

Rule: When the 10th tour is a strike, the player has 2 additional rolls

Scenario: A player plays a strike on its 10th tour

    Given: A player who played 9 tours with a score of 8 for each tour
    And: the player finishes is 10th tour with a strike
    And: the player rolls a first bowling ball and knocks down 4 pins
    And: the player rolls a second bowling ball and knocks down 2 pins
    When: The score is calculated for the game
    Then: The game is finished and the score should be 16 + 8 * 9 = 88

Rule: When the 10th tour is a strike, the player has 1 additional roll if the first roll is a strike again

Scenario: A player plays a strike on its 10th tour and a strike on the first additional roll

    Given: A player who played 9 tours with a score of 8 for each tour
    And: the player finishes is 10th tour with a strike
    And: the player rolls a first bowling ball and knocks down 10 pins
    And: the player rolls a second bowling ball and knocks down 2 pins
    When: The score is calculated for the game
    Then: The game is finished and the score should be 20 + 8 * 9 = 92

Rule: When the 10th tour is a spare, the player has 1 additional roll

Scenario: A player plays a spare on its 10th tour

    Given: A player who played 9 tours with a score of 8 for each tour
    And: the player finishes is 10th tour with a spare
    And: the player rolls a first bowling ball and knocks down 5 pins
    When: The score is calculated for the game
    Then: The game is finished and the score should be 15 + 8 * 9 = 87


