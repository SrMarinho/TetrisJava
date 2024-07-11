package com.tetris.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tetris.game.entities.Renderizable;

public class SidePanel implements Renderizable {
    public TetrisGame game;
    public int x;
    public int y;
    public int width;
    public int height;
    public BitmapFont font;

    public SidePanel(TetrisGame game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.font = new BitmapFont();
    }

    @Override
    public void event() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        this.game.shape.begin(ShapeType.Filled);
        this.game.shape.setColor(30 / 255F, 30 / 255F, 30 / 255F, 1);
        this.game.shape.rect(this.x, this.y, this.width, this.height);
        this.game.shape.end();

        this.game.batch.begin();
        this.font.draw(
                this.game.batch,
                "Score: " + this.game.scene.score,
                this.x + (float) this.width / 2 - 20,
                this.y + (float) this.height / 2
        );
        this.game.batch.end();
    }
}
