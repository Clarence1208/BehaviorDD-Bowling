# Bowling Game Kata - BDD Implementation

This project implements the [Bowling Game Kata](https://codingdojo.org/kata/Bowling/) using Behavior-Driven Development (BDD) with Cucumber and Java.

## Kata Requirements

The bowling kata requires implementing a scoring system that:
- Produces total score for valid sequence of rolls
- Handles strikes (10 pins in first roll) - score = 10 + next 2 rolls
- Handles spares (10 pins in two rolls) - score = 10 + next 1 roll
- Handles normal frames (less than 10 pins) - score = sum of two rolls
- Special rules for 10th frame (bonus rolls for strikes/spares)

## Quick Start

### Prerequisites
- Java 21+
- Maven 3.9+

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test runner
mvn test -Dtest=BowlingTestRunner

# Clean and run
mvn clean test
```

## Implementation Steps

### Step 1: Core Domain Model
We started with the essential domain classes:

**Frame.java** - Represents a single bowling frame
- Tracks first roll, second roll, and bonus rolls
- Validates pin counts (0-10 per roll, max 10 per frame)
- Determines frame type (strike, spare, normal)
- Calculates complete score including bonuses

**BowlingGame.java** - Manages the complete game
- Maintains list of frames
- Provides total score calculation
- Handles game completion logic

### Step 2: Basic BDD Scenarios
Implemented fundamental scenarios in Gherkin:

**Basic Roll Scoring**
```gherkin
Scenario: Score a roll with some pins knocked down
  Given a player rolls a bowling ball
  When 6 pins are knocked down
  Then the roll should score 6 points
```

**Normal Frame Scoring**
```gherkin
Scenario: Score a frame with two regular rolls
  Given a player starts a new frame
  When the player knocks down 6 pins on the first roll
  And the player knocks down 2 pins on the second roll
  Then the frame should score 8 points
```

### Step 3: Strike and Spare Logic
Added comprehensive strike and spare handling:

**Strike Detection and Scoring**
```gherkin
Scenario: Calculate strike score with next two rolls
  Given a player achieved a strike
  When the player's next two rolls knock down 4 and 3 pins respectively
  Then the strike frame should score 17 points
```

**Spare Detection and Scoring**
```gherkin
Scenario: Calculate spare score with next roll
  Given a player achieved a spare with 6 and 4 pins
  When the player's next roll knocks down 5 pins
  Then the spare frame should score 15 points
```

### Step 4: Tenth Frame Special Rules
Implemented the complex 10th frame logic:

**Tenth Frame Strike with Bonus Rolls**
```gherkin
Scenario: Tenth frame strike
  Given a player reaches the tenth frame with a score of 100
  When the player knocks down 10 pins on the first roll
  And the player knocks down 4 pins on the first bonus roll
  And the player knocks down 3 pins on the second bonus roll
  Then the frame should score 17 points
```

### Step 5: Complete Game Scenarios
Added end-to-end game scenarios:

**Perfect Game (300 points)**
```gherkin
Scenario: Perfect game
  Given a player bowls 12 consecutive strikes
  When the game is scored
  Then the total score should be 300 points
```

**All Gutters (0 points)**
```gherkin
Scenario: All gutters
  Given a player misses all pins in all rolls
  When the game is scored
  Then the total score should be 0 points
```

### Step 6: Error Handling and Validation
Implemented comprehensive error scenarios:

**Invalid Pin Counts**
```gherkin
Scenario: Invalid frame - too many pins knocked down
  Given a player starts a new frame
  When the player knocks down 5 pins on the first roll
  And the player tries to knock down 7 pins on the second roll
  Then an error should be thrown
```

**Frame Completion Validation**
```gherkin
Scenario: Cannot roll in completed frame
  Given a player has completed a frame
  When the player tries to roll again in the same frame
  Then an error should be thrown
```

### Step 7: Data-Driven Testing
Added comprehensive data-driven scenarios:

**All Spare Combinations**
```gherkin
Scenario Outline: Different spare combinations
  Given a player starts a new frame
  When the player knocks down <first> pins on the first roll
  And the player knocks down <second> pins on the second roll
  Then the frame should be a spare

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
```

### Step 8: Progressive Game Scenarios
Implemented complex game patterns using data tables:

**Mixed Game with Various Frame Types**
```gherkin
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
  Then the total score should be 131 points
```

## Architecture

### Domain Model
- **Frame**: Encapsulates frame logic, validation, and scoring
- **BowlingGame**: Orchestrates frames and calculates total score
- **FrameScore**: Value object for pending vs complete scores

### BDD Structure
- **Feature Files**: Gherkin scenarios in `src/test/resources/features/`
- **Step Definitions**: Java implementation in `src/test/java/steps/`
- **Test Runner**: Cucumber integration with JUnit

### Key Design Patterns
- **Domain-Driven Design**: Rich domain model with business logic
- **Value Objects**: FrameScore for immutable score representation
- **Validation**: Comprehensive input validation with meaningful error messages
- **Separation of Concerns**: Clear separation between domain logic and test infrastructure