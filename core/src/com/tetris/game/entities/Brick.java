package com.tetris.game.entities;

import com.tetris.game.TetrisGame;
import com.badlogic.gdx.graphics.Texture;

public class Brick implements Renderizable {
    public TetrisGame game;
    public int x;
    public int y;
    public int color;
    public Texture texture;

    public Brick(TetrisGame game, int x, int y, int color) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.color = color;
        this.texture = this.game.textureList.get(this.color);
    }

    @Override
    public void event() {
        boolean insideMapWidth = this.x < this.game.scene.map[0].length - 1;
        boolean insideMapHeight = this.y < this.game.scene.map.length - 1;
    }

    @Override
    public void update() {
        this.game.scene.map[this.y][this.x] = 0;
        this.y += 1;
        this.game.scene.map[this.y][this.x] = 1;
    }

    @Override
    public void render() {
        this.game.batch.begin();
        this.game.batch.draw(this.texture, this.x * this.game.grid_col_size, this.game.scene.height - ((this.y + 1) * this.game.grid_row_size), this.game.grid_col_size, this.game.grid_row_size);
        this.game.batch.end();
    }
}
