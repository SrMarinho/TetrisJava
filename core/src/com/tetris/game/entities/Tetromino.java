package com.tetris.game.entities;

import com.tetris.game.TetrisGame;

import java.util.ArrayList;
import java.util.List;

public class Tetromino implements Renderizable {
    public TetrisGame game;
    public Brick[] bricks;

    public Tetromino(TetrisGame game) {
        this.game = game;
        List<Brick[]> typeList = new ArrayList<>();
        typeList.addLast(new Brick[]{
                new Brick(this.game, 5, 1, 0),
                new Brick(this.game, 4, 0, 0),
                new Brick(this.game, 5, 0, 0),
                new Brick(this.game, 6, 0, 0)
        });
        this.bricks = typeList.get(0);
    }


    @Override
    public void event() {
        for (Brick brick : bricks) {
            brick.event();
        }
    }

    @Override
    public void update() {
        boolean canUpdate = true;
        for (Brick brick : bricks) {
            boolean ableToMove = brick.y < this.game.scene.map.length - 1;
            if (ableToMove) {
                boolean nextPositionValidator = this.game.scene.map[brick.y + 1][brick.x] == 0;
                if (!nextPositionValidator) {
                    canUpdate = false;
                }
            } else {
                canUpdate = false;
            }
        }
        if (canUpdate) {
            for (Brick brick : bricks) {
                brick.update();
            }
        }
    }

    @Override
    public void render() {
        for (Brick brick : bricks) {
            brick.render();
        }
    }
}
