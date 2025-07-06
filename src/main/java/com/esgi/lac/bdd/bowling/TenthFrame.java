package com.esgi.lac.bdd.bowling;

public class TenthFrame extends Frame {

    private Integer thirdRoll = null;
    private Integer firstBonusRoll = null;
    private Integer secondBonusRoll = null;


    @Override
    public void roll(int pins) {
        if (isComplete()) {
            throw new IllegalStateException("Tenth frame is complete. Cannot roll more.");
        }

        if (firstRoll == null) {
            firstRoll = pins;
        } else if (secondRoll == null) {
            secondRoll = pins;
        } else {
            thirdRoll = pins;
        }
    }

    @Override
    public boolean isComplete() {
        if (isStrike()) {
            return firstBonusRoll != null && secondBonusRoll != null;
        }
        if (isSpare()) {
            return firstBonusRoll != null;
        }
        return firstRoll != null && secondRoll != null;
    }




    @Override
    public int getCompleteScore() {
        return optional(firstRoll)
                + optional(secondRoll)
                + optional(firstBonusRoll)
                + optional(secondBonusRoll);
    }

    @Override
    public FrameScore getFrameScore() {
        boolean pending = !isComplete();
        int score = getSafe(firstRoll) + getSafe(secondRoll) + getSafe(firstBonusRoll) + getSafe(secondBonusRoll);
        return new FrameScore(score, pending);
    }

    private int optional(Integer value) {
        return value != null ? value : 0;
    }


    private boolean isStrike(Integer roll) {
        return roll != null && roll == 10;
    }

    @Override
    public boolean isSpare() {
        return firstRoll != null && secondRoll != null && firstRoll + secondRoll == 10 && firstRoll != 10;
    }


    private int getSafe(Integer roll) {
        return roll != null ? roll : 0;
    }

    @Override
    public void addBonusRoll(int pins) {
        if (isStrike()) {
            if (firstBonusRoll == null) {
                firstBonusRoll = pins;
            } else if (secondBonusRoll == null) {
                secondBonusRoll = pins;
            } else {
                throw new IllegalStateException("Tenth frame already has two bonus rolls.");
            }
        } else if (isSpare()) {
            if (firstBonusRoll == null) {
                firstBonusRoll = pins;
            } else {
                throw new IllegalStateException("Tenth frame already has one bonus roll.");
            }
        } else {
            throw new IllegalStateException("No bonus rolls allowed in normal tenth frame.");
        }
    }



}
