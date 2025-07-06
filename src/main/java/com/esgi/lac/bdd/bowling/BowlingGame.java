package com.esgi.lac.bdd.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final List<Frame> frames = new ArrayList<>();
    private Frame currentFrame;

    public int roll(int pins) {
        return pins;
    }

    public Frame startNewFrame() {
        if (frames.size() >= 10) {
            throw new IllegalStateException("Cannot start more than 10 frames.");
        }

        Frame frame = (frames.size() == 9) ? new TenthFrame() : new Frame();
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
