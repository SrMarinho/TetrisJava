package com.tetris.game.entities;

import com.tetris.game.TetrisGame;

import java.util.ArrayList;
import java.util.List;

public class Tetromino implements Renderizable {
    public TetrisGame game;
    public int x;
    public int y;
    public int[][][] shapes;
    public int type;
    public int rotation;

    public Tetromino(TetrisGame game, Integer type) {
        this.game = game;
        this.x = 5;
        this.y = 5;
        this.type = type;
        this.rotation = 0;
        this.shapes = new int[][][]{
                {
                        {0, 0, 0},
                        {1, 1, 1},
                        {0, 1, 0}
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 1}
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 0}
                },
                {
                        {0, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {0, 0, 0},
                        {1, 1, 0},
                        {0, 1, 1}
                },
                {
                        {1, 1},
                        {1, 1},
                },
                {
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0}
                }
        };
    }

    @Override
    public void event() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
        for (int y = 0; y < this.shapes[this.type].length; y++) {
            for (int x = 0; x < this.shapes[this.type][0].length; x++) {
                if (this.shapes[this.type][y][x] != 0) {
                    this.game.scene.map[y + this.y][x] = Integer.toString(0);
                }
            }
        }
    }

    public int[][] rotate() {
        int[][] original_shape = deepCopy(this.shapes[this.type]);

        return original_shape;
    };

    public int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
}
