package com.esgi.lac.bdd.bowling.steps;

import com.esgi.lac.bdd.bowling.BowlingGame;
import com.esgi.lac.bdd.bowling.Frame;

public class TestContext {
    private static TestContext instance;
    
    private BowlingGame game;
    private Frame currentFrame;
    
    private TestContext() {}
    
    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
    
    public BowlingGame getGame() {
        return game;
    }
    
    public void setGame(BowlingGame game) {
        this.game = game;
    }
    
    public Frame getCurrentFrame() {
        return currentFrame;
    }
    
    public void setCurrentFrame(Frame currentFrame) {
        this.currentFrame = currentFrame;
    }
    
    public void reset() {
        this.game = null;
        this.currentFrame = null;
    }
}
