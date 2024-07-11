package com.tetris.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TetrisGame extends ApplicationAdapter {
    public SpriteBatch batch;
    public List<Texture> textureList;
    public Scene scene;
    public SidePanel sidePanel;
    public ShapeRenderer shape;
    public int move_time;
    public int width;
    public int height;
    public int cols;
    public int rows;
    public boolean gameOver;

    public TetrisGame() {
        this.textureList = new ArrayList<>();
        this.move_time = 150;
        this.cols = 10;
        this.rows = 20;
        this.width = 350;
        this.height = (width / this.cols) * this.rows;
        this.gameOver = false;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();

        this.textureList.add(new Texture("1.png"));
        this.textureList.add(new Texture("2.png"));
        this.textureList.add(new Texture("3.png"));
        this.textureList.add(new Texture("4.png"));
        this.textureList.add(new Texture("5.png"));
        this.textureList.add(new Texture("6.png"));
        this.textureList.add(new Texture("7.png"));

        this.scene = new Scene(this);
        this.sidePanel = new SidePanel(
                this,
                this.scene.width,
                0,
                Gdx.graphics.getWidth() - this.scene.width,
                Gdx.graphics.getHeight()
        );
    }

    @Override
    public void render() {
        ScreenUtils.clear(40 / 255F, 60 / 255F, 80 / 255F, 1);

        this.restart();

        this.scene.event();
        this.sidePanel.event();

        this.scene.update();
        this.sidePanel.update();

        this.scene.render();
        this.sidePanel.render();
    }

    public void restart() {
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            this.scene = new Scene(this);
            this.gameOver = false;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture texture : this.textureList) {
            texture.dispose();
        }
    }
}
