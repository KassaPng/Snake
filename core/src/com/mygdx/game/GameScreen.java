package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    private Game game = new Game();
    private SnakeMain main;
    private final int width = 800;
    private final int height = 800;
    private OrthographicCamera camera = new OrthographicCamera(width, height);
    private Viewport viewport;

    public GameScreen(SnakeMain main) {
        this.main = main;
        camera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height, camera);
        viewport.apply();

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.update(delta);
        camera.update();
        viewport.apply();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        main.batch.begin();
        main.font.draw(main.batch, "SCORE: " + game.getScore(), 10, 30);
        if (game.getControls().getPause() && game.getDead() == false) {
            main.font.draw(main.batch, "PAUSED. Press any key to resume",
                    Gdx.graphics.getWidth()/2-130, Gdx.graphics.getHeight()/2);
        }
        if (game.getDead()) {
            main.font.setColor(Color.RED);
            main.font.draw(main.batch, "       FINAL SCORE: " + game.getFinalScore() + "\nPress any key to play again",
                    Gdx.graphics.getWidth()/2-90, Gdx.graphics.getHeight()/2+70);
            main.font.setColor(Color.WHITE);
        }
        main.batch.end();
        game.draw(width, height, camera);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
