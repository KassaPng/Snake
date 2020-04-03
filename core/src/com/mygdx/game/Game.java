package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Queue;

public class Game {

    private int boardSize = 50;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Queue<SnakeBody> bodyQueue = new Queue<>();
    private float mTimer = 0;
    private float speed = 0.04f;
    private Controls controls = new Controls();
    private Food food = new Food(boardSize);
    private int snakeLength = 3;
    private int score = 0;
    private int finalScore;
    private boolean dead = false;


    public Game() {
        bodyQueue.addLast(new SnakeBody(boardSize / 2, boardSize / 2, boardSize));
        bodyQueue.addLast(new SnakeBody(boardSize / 2, (boardSize / 2) - 1, boardSize));
        bodyQueue.addLast(new SnakeBody(boardSize / 2, (boardSize / 2) - 2, boardSize));
    }

    public int getScore() {
        return score;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public boolean getDead() {
        return dead;
    }

    public Controls getControls() {
        return controls;
    }

    public void respawn() {
        controls.pause();
        snakeLength = 3;
        score = 0;
        bodyQueue.clear();
        bodyQueue.addLast(new SnakeBody(boardSize / 2, boardSize / 2, boardSize));
        bodyQueue.addLast(new SnakeBody(boardSize / 2, (boardSize / 2) - 1, boardSize));
        bodyQueue.addLast(new SnakeBody(boardSize / 2, (boardSize / 2) - 2, boardSize));
        controls.respawnDir();
    }

    public void update(float delta) {
        if (!controls.getPause()) {
            mTimer += delta;
            if (mTimer > speed) {
                mTimer = 0;
                advance();
            }
        }
            controls.update();
    }

    private void advance() {
        dead = false;
        int headX = bodyQueue.first().getX();
        int headY = bodyQueue.first().getY();
        switch (controls.getDirection()) {
            case 0: //up
                bodyQueue.addFirst(new SnakeBody(headX, headY + 1, boardSize));
                break;
            case 1: //right
                bodyQueue.addFirst(new SnakeBody(headX + 1, headY, boardSize));
                break;
            case 2: //down
                bodyQueue.addFirst(new SnakeBody(headX, headY - 1, boardSize));
                break;
            case 3: //left
                bodyQueue.addFirst(new SnakeBody(headX - 1, headY, boardSize));
                break;
        }
        if (bodyQueue.first().getX() == food.getX() && bodyQueue.first().getY() == food.getY()) {
            snakeLength++;
            score++;
            food.randomisePos(boardSize);
        }

        for (int i = 1; i < bodyQueue.size; i++) {
            if (bodyQueue.get(i).getX() == bodyQueue.first().getX()
                    && bodyQueue.get(i).getY() == bodyQueue.first().getY()) {
                finalScore = score;
                dead = true;
                respawn();

            }
        }

        while (bodyQueue.size - 1 >= snakeLength) {
            bodyQueue.removeLast();
        }

    }

    public void draw(int width, int height, OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//rectangle drawing happens here
        shapeRenderer.setColor(Color.GREEN);
        float scaleSnake = width / boardSize;
        shapeRenderer.rect(food.getX() * scaleSnake, food.getY() * scaleSnake, scaleSnake, scaleSnake);
        shapeRenderer.setColor(Color.WHITE);
        for (SnakeBody bp : bodyQueue) {
            shapeRenderer.rect(bp.getX() * scaleSnake, bp.getY() * scaleSnake, scaleSnake, scaleSnake);
        }
        shapeRenderer.end();
    }
}
