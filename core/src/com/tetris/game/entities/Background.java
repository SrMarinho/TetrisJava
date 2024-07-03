package com.tetris.game.entities;

import com.tetris.game.TetrisGame;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Background implements Renderizable {
    TetrisGame game;
    public Background(TetrisGame game) {
        this.game = game;
    }

    @Override
    public void event() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        for (int y = 0; y < this.game.scene.map.length; y++) {
            for (int x = 0; x < this.game.scene.map[0].length; x++) {
                this.game.shape.begin(ShapeType.Filled);
                this.game.shape.setColor(100/255F, 100/255F, 100/255F, 1);
                this.game.shape.circle((x * this.game.grid_col_size) + (this.game.grid_col_size / 2), (y * this.game.grid_row_size) + (this.game.grid_row_size / 2), 1);
                this.game.shape.end();
            }
        }
    }
}
