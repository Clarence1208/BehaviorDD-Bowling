package com.esgi.lac.bdd.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < 10 && rollIndex < rolls.size(); frame++) {
            if (isStrike(rollIndex)) {
                score += 10 + strikeBonus(rollIndex);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += sumOfBallsInFrame(rollIndex);
                rollIndex += 2;
            }
        }

        return score;
    }

    public int scoreForFrame(int frameIndex) {
        int score = 0;
        int rollIndex = 0;
        int currentFrame = 0;

        while (currentFrame <= frameIndex && rollIndex < rolls.size()) {
            if (isStrike(rollIndex)) {
                if (rollIndex + 2 >= rolls.size()) return -1;
                score += 10 + strikeBonus(rollIndex);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {
                if (rollIndex + 2 > rolls.size()) return -1;
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += sumOfBallsInFrame(rollIndex);
                rollIndex += 2;
            }
            currentFrame++;
        }

        return score;
    }


    public boolean isSpare() {
        return rolls.size() >= 2 && (rolls.get(0) + rolls.get(1) == 10) && !isStrike(0);
    }

    public boolean isStrike() {
        return !rolls.isEmpty() && rolls.get(0) == 10;
    }

    public boolean isFrameComplete() {
        if (rolls.isEmpty()) return false;
        if (isStrike()) return true;
        return rolls.size() >= 2;
    }

    public boolean isScorePending() {
        if (isSpare() && rolls.size() < 3) return true;
        if (isStrike() && rolls.size() < 3) return true;
        return false;
    }

    // Internal helper methods

    private boolean isSpare(int rollIndex) {
        return rollIndex + 1 < rolls.size()
                && rolls.get(rollIndex) + rolls.get(rollIndex + 1) == 10
                && !isStrike(rollIndex);
    }

    private boolean isStrike(int rollIndex) {
        return rollIndex < rolls.size() && rolls.get(rollIndex) == 10;
    }

    private int strikeBonus(int rollIndex) {
        if (rollIndex + 2 >= rolls.size()) return 0;
        return rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
    }

    private int spareBonus(int rollIndex) {
        if (rollIndex + 1 >= rolls.size()) return 0;
        return rolls.get(rollIndex + 1);
    }

    private int sumOfBallsInFrame(int rollIndex) {
        int sum = 0;
        if (rollIndex < rolls.size()) sum += rolls.get(rollIndex);
        if (rollIndex + 1 < rolls.size()) sum += rolls.get(rollIndex + 1);
        return sum;
    }
}
