package com.esgi.lac.bdd.bowling;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bowling Game.
 * This class manages the frames of the game, allows rolling pins, and calculates the total score.
 */
public class BowlingGame {

    @Getter
    private final List<Frame> frames = new ArrayList<>();

    public int roll(int pins) {
        return pins;
    }

    public Frame startNewFrame() {
        if (frames.size() >= 10) {
            throw new IllegalStateException("Cannot start more than 10 frames.");
        }

        Frame frame = new Frame();
        frames.add(frame);
        return frame;
    }

    public boolean isComplete() {
        return frames.size() == 10 && frames.get(9).isComplete();
    }

    public int getTotalScore() {
        return frames.stream()
                .mapToInt(f -> f.getFrameScore().score())
                .sum();
    }
}
