package com.esgi.lac.bdd.bowling;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Frame {

    private Integer firstRoll = null;
    private Integer secondRoll = null;

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

    public int getScore() {
        return firstRoll + secondRoll;
    }

    public boolean isComplete() {
        return firstRoll != null && secondRoll != null;
    }

    public FrameScore getFrameScore() {
        if (isComplete()) {
            return new FrameScore(getScore(), false);
        } else {
            return new FrameScore(firstRoll, true);
        }
    }

@Getter
public static class FrameScore {
        private final int score;
        private final boolean pending;

        public FrameScore(int score, boolean pending) {
            this.score = score;
            this.pending = pending;
        }

}
}
