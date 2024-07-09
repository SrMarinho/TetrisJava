package com.tetris.game.entities;

import com.badlogic.gdx.Gdx;
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
        int blockSize = this.game.width / 10;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                this.game.shape.begin(ShapeType.Line);
                this.game.shape.setColor(100/255F, 100/255F, 100/255F, 1);
                this.game.shape.rect(x * blockSize, Gdx.graphics.getHeight() - (blockSize * (y + 1)), blockSize, blockSize);
                this.game.shape.end();
            }
        }
    }
}
