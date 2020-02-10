package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controls {

    private int currentDirection; //0,1,2,3 U,R,D,L
    private int nextDirection;
    private boolean pause = false;

    public int getDirection() {
        currentDirection = nextDirection;
        return nextDirection;
    }

    public boolean getPause() {
        return pause;
    }

    public void pause() {
        pause = true;
    }

    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && currentDirection != 2) nextDirection = 0;

        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && currentDirection != 3) nextDirection = 1;

        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && currentDirection != 0) nextDirection = 2;

        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && currentDirection != 1) nextDirection =3;

        else if (Gdx.input.isKeyPressed(Input.Keys.P)) pause = true;

        else if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) && pause == true) pause = false;
    }

    public void respawnDir(){
        nextDirection = 0;
    }
}
