package com.esgi.lac.bdd.bowling;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class Frame {

    private Integer firstRoll = null;
    private Integer secondRoll = null;
    private Integer bonusRoll = null;

    public void roll(int pins) {
        if (!isComplete()) {
            if (firstRoll == null) {
                firstRoll = pins;
                return;
            }
            secondRoll = pins;
        } else {
            throw new IllegalStateException("Frame is already complete. Cannot roll more.");
        }
    }

    public void addBonusRoll(int pins) {
        if (bonusRoll != null) {
            throw new IllegalStateException("Bonus roll already exists. Cannot add another bonus roll.");
        }

        if (!isSpare()) {
            throw new IllegalStateException("Bonus roll can only be added to a spare frame.");
        }

        bonusRoll = pins;
    }

    public int getCompleteScore() {
        return firstRoll + secondRoll + Optional.ofNullable(bonusRoll).orElse(0);
    }

    public boolean isComplete() {
        if (isSpare()) {
            return bonusRoll != null;
        }

        return firstRoll != null && secondRoll != null;
    }

    public boolean isSpare() {
        return firstRoll != null
            && secondRoll != null
            && (firstRoll + secondRoll == 10);
    }

    public FrameScore getFrameScore() {
        if (isComplete()) {
            return new FrameScore(getCompleteScore(), false);
        } else {
            return new FrameScore(
                Optional.ofNullable(firstRoll).orElse(0) +
                    Optional.ofNullable(secondRoll).orElse(0) +
                    Optional.ofNullable(bonusRoll).orElse(0),
                true
            );
        }
    }

    public record FrameScore(int score, boolean pending) {
    }
}
