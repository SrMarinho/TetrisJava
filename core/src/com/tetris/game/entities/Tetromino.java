package com.tetris.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tetris.game.TetrisGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tetromino implements Renderizable {
    TetrisGame game;
    public int x;
    public int y;
    public int shapeIndex;
    public int color;
    public int[][][] shapeList;
    public int[][] shape;
    public int rotation;
    public int speedY;

    public Tetromino(TetrisGame game, int shapeIndex, int color) {
        this.game = game;
        this.x = 5;
        this.y = 22;
        this.shapeIndex = shapeIndex;
        this.color = color;
        this.speedY = 1;

        this.shapeList = new int[][][]{
                {
                        {1, 1},
                        {1, 1},
                },
                {
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                },
                {
                        {1, 1, 1},
                        {0, 1, 0},
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 0},
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 1},
                },
                {
                        {0, 1, 1},
                        {1, 1, 0},
                },
                {
                        {1, 1, 0},
                        {0, 1, 1},
                }
        };

        this.shape = this.shapeList[shapeIndex];
    }

    @Override
    public void event() {
        int[][] currentShape = this.shape.clone();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            boolean canMoveDown = true;

            // Verificando cada parte do tetromino
            outerLoop:
            for (int y = 0; y < currentShape.length; y++) {
                for (int x = 0; x < currentShape[0].length; x++) {
                    if (currentShape[y][x] == 0) continue;

                    int nextPosX = this.x + x - (int) (currentShape[0].length / 2) + 1;
                    int nextPosY = this.y - y - (int) (currentShape.length / 2);

                    for (int i = 0; i < this.game.scene.objs.size(); i++) {

                        boolean sameX = nextPosX == this.game.scene.objs.get(i).x;
                        boolean sameY = nextPosY == this.game.scene.objs.get(i).y;

                        if (sameX && sameY) {
                            canMoveDown = false;
                            break outerLoop;
                        }
                    }
                    if (nextPosX >= this.game.cols) {
                        canMoveDown = false;
                        break outerLoop;
                    }
                }
            }
            if (canMoveDown) this.x += 1;
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            boolean canMoveDown = true;

            // Verificando cada parte do tetromino
            outerLoop:
            for (int y = 0; y < currentShape.length; y++) {
                for (int x = 0; x < currentShape[0].length; x++) {
                    if (currentShape[y][x] == 0) continue;

                    int nextPosX = this.x + x - (int) (currentShape[0].length / 2) - 1;
                    int nextPosY = this.y - y - (int) (currentShape.length / 2);

                    for (int i = 0; i < this.game.scene.objs.size(); i++) {
                        boolean sameX = nextPosX == this.game.scene.objs.get(i).x;
                        boolean sameY = nextPosY == this.game.scene.objs.get(i).y;
                        if (sameX && sameY) {
                            canMoveDown = false;
                            break outerLoop;
                        }
                    }
                    if (nextPosX < 0) {
                        canMoveDown = false;
                        break outerLoop;
                    }
                }
            }
            if (canMoveDown) this.x -= 1;
        }
    }

    @Override
    public void update() {
        int[][] currentShape = this.shape.clone();

        boolean canMoveDown = true;
        // Verificando cada parte do tetromino
        outerLoop:
        for (int y = 0; y < currentShape.length; y++) {
            for (int x = 0; x < currentShape[0].length; x++) {
                if (currentShape[y][x] == 0) continue;

                for (int i = 0; i < this.game.scene.objs.size(); i++) {
                    int nextPosX = this.x + x - (int) (currentShape[0].length / 2);
                    int nextPosY = this.y - y - (int) (currentShape.length / 2) - 1;

                    boolean sameX = nextPosX == this.game.scene.objs.get(i).x;
                    boolean sameY = nextPosY == this.game.scene.objs.get(i).y;

                    // Validando se tem algum elemento abaixo de alguma parte do atual tetromino
                    if (sameX && sameY) {
                        canMoveDown = false;
                        break outerLoop;
                    }
                    // Validando se não encostou no chão
                    if (nextPosY < 0) {
                        canMoveDown = false;
                        break outerLoop;
                    }
                }
            }
        }
//        System.out.println(canMoveDown);
        if (canMoveDown) this.y -= speedY;
        if (!canMoveDown) this.game.scene.newTetromino();
    }

    @Override
    public void render() {
        int[][] currentShape = this.shape.clone();
        for (int y = 0; y < currentShape.length; y++) {
            for (int x = 0; x < currentShape[0].length; x++) {
                if (currentShape[y][x] == 0) continue;
                new Brick(
                        this.game,
                        this.x + x - (int)(currentShape[0].length / 2),
                        this.y - y - (int)(currentShape.length / 2),
                        this.color)
                        .render();
            }
        }
    }

    public int[][] rotation() {
        int[][] currentShape = this.shape.clone();
//        for (int[] brick : currentShape) {
//            brick.x = Integer.getInteger(String.valueOf(Math.round(brick.x * Math.cos(Math.toRadians(90)) - brick.y * Math.sin(Math.toRadians(90)))));
//            brick.y = Integer.getInteger(String.valueOf(Math.round(brick.x * Math.sin(Math.toRadians(90)) + brick.y * Math.cos(Math.toRadians(90)))));
//        }
        return currentShape;
    }

    public void addToMap() {

    }
}
