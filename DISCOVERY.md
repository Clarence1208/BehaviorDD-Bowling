# DISCOVERY FOR BOWLING

## Feature: Score a Single Roll

**Rule:** The score of a roll equals the number of pins knocked down

### Scenario: Player knocks down some pins

```
Given a player rolls a bowling ball
When 6 pins are knocked down
Then the roll should score 6 points
```

### Scenario: Player misses all pins

```
Given a player rolls a bowling ball
When 0 pins are knocked down
Then the roll should score 0 points
```

---

## Feature: Score a Normal Frame

**Rule:** A normal frame score is the sum of two rolls when fewer than 10 pins total are knocked down

### Scenario: Player scores in two rolls

```
Given a player starts a new frame
When the player knocks down 6 pins on the first roll
And the player knocks down 2 pins on the second roll
Then the frame should score 8 points
And the frame should be complete
```

---

## Feature: Score a Spare

**Rule:** A spare occurs when all 10 pins are knocked down in exactly two rolls
**Rule:** A spare scores 10 points plus the next roll

### Scenario: Player achieves a spare

```
Given a player starts a new frame
When the player knocks down 6 pins on the first roll
And the player knocks down 4 pins on the second roll
Then the frame should be a spare
And the frame score should be pending
```

### Scenario: Calculate spare score with next roll

```
Given a player achieved a spare scoring 6 and 4 pins
When the player's next roll knocks down 5 pins
Then the spare frame should score 15 points
```

---

## Feature: Score a Strike

**Rule:** A strike occurs when all 10 pins are knocked down on the first roll
**Rule:** A strike scores 10 points plus the next two rolls

### Scenario: Player achieves a strike

```
Given a player starts a new frame
When the player knocks down 10 pins on the first roll
Then the frame should be a strike
And the frame should be complete
And the frame score should be pending
```

### Scenario: Calculate strike score with next two rolls

```
Given a player achieved a strike
When the player's next two rolls knock down 4 and 3 pins respectively
Then the strike frame should score 17 points
```

---

## Feature: Score the Tenth Frame

**Rule:** The tenth frame allows bonus rolls for strikes and spares

### Scenario: Normal tenth frame

```
Given a player reaches the tenth frame with a score of 100
When the player knocks down 6 pins on the first roll
And the player knocks down 2 pins on the second roll
Then the frame should score 8 points
And the game should be complete with a total score of 108 points
```

### Scenario: Tenth frame spare

```
Given a player reaches the tenth frame with a score of 100
When the player achieves a spare
And the player knocks down 5 pins on the bonus roll
Then the tenth frame should score 15 points
And the game should be complete with a total score of 115 points
```

### Scenario: Tenth frame strike

```
Given a player reaches the tenth frame with a score of 100
When the player achieves a strike
And the player knocks down 4 pins on the first bonus roll
And the player knocks down 3 pins on the second bonus roll
Then the tenth frame should score 17 points
And the game should be complete with a total score of 117 points
```

---

## Feature: Score a Complete Game

**Rule:** A game consists of exactly 10 frames

### Scenario: Perfect game

```
Given a player bowls 12 consecutive strikes
When the game is scored
Then the total score should be 300 points
```

### Scenario: All gutters

```
Given a player misses all pins in all rolls
When the game is scored
Then the total score should be 0 points
```

### Scenario: All spares with 5 pins each time

```
Given a player achieves spares in all 10 frames by knocking down 5 pins then 5 pins
And the player knocks down 5 pins on the bonus roll
When the game is scored
Then the total score should be 150 points
```
