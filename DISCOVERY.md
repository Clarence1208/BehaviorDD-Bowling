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


## Feature : Make a strike
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

