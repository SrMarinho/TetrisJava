package com.tetris.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tetris.game.TetrisGame;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Block implements Renderizable {
    public TetrisGame game;
    public int x;
    public int y;
    public int color;
    public Texture texture;

    public Block(TetrisGame game, int x, int y, int color) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.color = color;
        this.texture = this.game.textureList.get(this.color);
    }

    @Override
    public void event() {

    }

    @Override
    public void update() {
        this.y -= 1;
    }

    @Override
    public void render() {
        this.game.batch.begin();
        this.game.batch.draw(this.texture, this.x * this.game.grid_col_size, this.y * this.game.grid_row_size, this.game.grid_col_size, this.game.grid_row_size);
        this.game.batch.end();
    }
}
