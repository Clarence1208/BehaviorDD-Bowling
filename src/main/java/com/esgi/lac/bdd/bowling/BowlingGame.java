package com.esgi.lac.bdd.bowling;

public class BowlingGame {

    public int roll(int pins) {
        return pins;
    }

    public Frame startNewFrame() {
        return new Frame();
    }
}
