package com.esgi.lac.bdd.bowling;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class Frame {

    private Integer firstRoll = null;
    private Integer secondRoll = null;
    private Integer bonusRoll = null;
    private Integer secondBonusRoll = null;

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
        if (isStrike()) {
            return 10 +
                    Optional.ofNullable(bonusRoll).orElse(0) +
                    Optional.ofNullable(secondBonusRoll).orElse(0);
        }

        return Optional.ofNullable(firstRoll).orElse(0) +
                Optional.ofNullable(secondRoll).orElse(0) +
                Optional.ofNullable(bonusRoll).orElse(0);
    }

    public boolean isComplete() {
        if (isStrike()) {
            return true;
        }
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
        boolean pending = false;

        if (isStrike()) {
            pending = bonusRoll == null || secondBonusRoll == null;
        } else if (isSpare()) {
            pending = bonusRoll == null;
        } else {
            pending = secondRoll == null;
        }

        int score = Optional.ofNullable(firstRoll).orElse(0)
                + Optional.ofNullable(secondRoll).orElse(0)
                + Optional.ofNullable(bonusRoll).orElse(0)
                + Optional.ofNullable(secondBonusRoll).orElse(0);

        return new FrameScore(score, pending);
    }

    public record FrameScore(int score, boolean pending) {
    }

    public boolean isStrike() {
        return firstRoll != null && firstRoll == 10;
    }
}
