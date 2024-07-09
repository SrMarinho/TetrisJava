package com.tetris.game.entities;

import com.tetris.game.TetrisGame;
import com.badlogic.gdx.graphics.Texture;

public class Brick implements Renderizable {
    public TetrisGame game;
    public int x;
    public int y;
    public int size;
    public int color;
    public Texture texture;

    public Brick(TetrisGame game, int x, int y, int color) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.size = this.game.width / 10;
        this.color = color;
        this.texture = this.game.textureList.get(this.color);
    }

    @Override
    public void event() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
        this.game.batch.begin();
        this.game.batch.draw(
                this.texture,
                this.x * this.size,
                this.y * this.size,
                this.size, this.size
        );
        this.game.batch.end();
    }

    public void move_down() {
        this.y += 1;
    }
}
