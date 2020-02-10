package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Food {
    private int x;
    private int y;

    public Food(int boardSize) {
        randomisePos(boardSize);
    }

    public void randomisePos(int boardSize) {
        x = MathUtils.random(boardSize-1);
        y = MathUtils.random(boardSize-1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
