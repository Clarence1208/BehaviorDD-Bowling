package com.esgi.lac.bdd.bowling;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class Frame {

    protected Integer firstRoll = null;
    protected Integer secondRoll = null;
    protected Integer bonusRoll = null;
    protected Integer secondBonusRoll = null;

    public void roll(int pins) {
        if (isComplete()) {
            throw new IllegalStateException("Frame is already complete. Cannot roll more.");
        }

        if (firstRoll == null) {
            firstRoll = pins;
            return;
        }
        secondRoll = pins;
    }

    public void addBonusRoll(int pins) {
        if (isStrike()) {
            if (bonusRoll == null) {
                bonusRoll = pins;
            } else if (secondBonusRoll == null) {
                secondBonusRoll = pins;
            } else {
                throw new IllegalStateException("Both bonus rolls already exist for strike.");
            }
        } else if (isSpare()) {
            if (bonusRoll == null) {
                bonusRoll = pins;
            } else {
                throw new IllegalStateException("Bonus roll already exists for spare.");
            }
        } else {
            throw new IllegalStateException("Bonus roll can only be added to a spare or strike frame.");
        }
    }

    public int getCompleteScore() {
        return Optional.ofNullable(firstRoll).orElse(0) +
            Optional.ofNullable(secondRoll).orElse(0) +
            Optional.ofNullable(bonusRoll).orElse(0) +
            Optional.ofNullable(secondBonusRoll).orElse(0);
    }

    public boolean isComplete() {
        if (isStrike()) {
            return true;
        }
        if (isSpare()) {
            return true;
        }
        return firstRoll != null && secondRoll != null;
    }

    public boolean isSpare() {
        return firstRoll != null
            && secondRoll != null
            && (firstRoll + secondRoll == 10);
    }

    public FrameScore getFrameScore() {
        return new FrameScore(getCompleteScore(), isScorePending());
    }

    private boolean isScorePending() {
        if (isStrike()) {
            return bonusRoll == null || secondBonusRoll == null;
        }
        if (isSpare()) {
            return bonusRoll == null;
        }
        return firstRoll == null || secondRoll == null;
    }

    public record FrameScore(int score, boolean pending) {
    }

    public boolean isStrike() {
        return firstRoll != null && firstRoll == 10;
    }
}
