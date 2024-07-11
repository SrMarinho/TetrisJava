package com.tetris.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.tetris.game.TetrisGame;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

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
    Instant last_move_time;
    public int tickTimer;

    public Tetromino(TetrisGame game, int shapeIndex, int color) {
        this.game = game;
        this.last_move_time = Instant.now();
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
                        {0, 0, 0},
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
                        {0, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {0, 0, 0},
                        {1, 1, 0},
                        {0, 1, 1}
                }
        };

        this.shapeIndex = shapeIndex;
        this.shape = this.shapeList[shapeIndex];

        this.x = 5;
        this.y = 20 + this.shape.length;
        this.color = color;
        this.speedY = 1;

        tickTimer = this.game.move_time;

        initializeInputProcessor();
    }

    @Override
    public void event() {
    }

    private void initializeInputProcessor() {
        Map<Integer, Runnable> keyMapping = new HashMap<>();
        keyMapping.put(Input.Keys.LEFT, this::moveLeft);
        keyMapping.put(Input.Keys.RIGHT, this::moveRight);
        keyMapping.put(Input.Keys.DOWN, this::fastDrop);
        keyMapping.put(Input.Keys.SPACE, this::rotation);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                Runnable action = keyMapping.get(keycode);
                if (action != null) {
                    action.run();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void update() {
        boolean canFall = this.somethingBellow();

        if (!canFall) this.game.scene.newTetromino();

        Instant current_time = Instant.now();
        Duration duration = Duration.between(this.last_move_time, current_time);

        if (duration.toMillis() > this.tickTimer) {
            if (canFall) this.y -= speedY;
            this.last_move_time = current_time;
        }
    }

    @Override
    public void render() {
        int[][] currentShape = this.shape.clone();
        for (int y = 0; y < currentShape.length; y++) {
            for (int x = 0; x < currentShape[0].length; x++) {
                if (currentShape[y][x] == 0) continue;
                new Brick(
                        this.game,
                        this.x + x - (int) (currentShape[0].length / 2),
                        this.y - y - (int) (currentShape.length / 2),
                        this.color)
                        .render();
            }
        }
    }

    public void rotation() {
        int nextRotation = this.rotation + 1;

        int rows = this.shape.length;
        int cols = this.shape[0].length;

        int[][] currentShape = this.shape.clone();

        int[][] rotatedShape = new int[cols][rows];

        for (int y = 0; y < rotatedShape.length; y++) {
            for (int x = 0; x < rotatedShape[0].length; x++) {
                rotatedShape[y][x] = currentShape[x][cols - y - 1];
            }
        }

        if(!colliding(rotatedShape)) {
            this.shape = rotatedShape;
        }
    }

    public void moveLeft() {
        int[][] currentShape = this.shape.clone();

        boolean canMoveDown = true;

        // Verificando cada parte do tetromino
        outerLoop:
        for (int y = 0; y < currentShape.length; y++) {
            for (int x = 0; x < currentShape[0].length; x++) {
                if (currentShape[y][x] == 0) continue;

                int nextPosX = this.x + x - (currentShape[0].length / 2) - 1;
                int nextPosY = this.y - y - (currentShape.length / 2);

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

    public void moveRight() {
        int[][] currentShape = this.shape.clone();

        boolean canMoveDown = true;

        // Verificando cada parte do tetromino
        outerLoop:
        for (int y = 0; y < currentShape.length; y++) {
            for (int x = 0; x < currentShape[0].length; x++) {
                if (currentShape[y][x] == 0) continue;

                int nextPosX = this.x + x - (currentShape[0].length / 2) + 1;
                int nextPosY = this.y - y - (currentShape.length / 2);

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
    }

    private boolean colliding(int[][] shape) {

        // Percorrendo cada parte do shape
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[0].length; x++) {
                // Comparando com cada objeto em cena
                for (Brick obj : this.game.scene.objs) {
                    // x e y na posição real
                    int PosX = this.x + x - (shape[0].length / 2);
                    int PosY = this.y - y - (shape.length / 2);

                    // Verificando se alguma parte atual está ocupando o mesmo espaço de outra
                    for (int i = 0; i < this.game.scene.objs.size(); i++) {

                        boolean sameX = PosX == this.game.scene.objs.get(i).x;
                        boolean sameY = PosY == this.game.scene.objs.get(i).y;

                        if (sameX && sameY) {
                            return true;
                        }
                    }

                    // Verificando se alguna peça esta fora do mapa
                    if (PosX < 0 || PosX >= this.game.cols - 1) {
                        return true;
                    }
                    if (PosY <= 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean somethingBellow() {
        int[][] currentShape = this.shape.clone();

        boolean canMoveDown = true;
        // Verificando cada parte do tetromino
        outerLoop:
        for (int y = 0; y < currentShape.length; y++) {
            for (int x = 0; x < currentShape[0].length; x++) {
                if (currentShape[y][x] == 0) continue;

                int nextPosX = this.x + x - (currentShape[0].length / 2);
                int nextPosY = this.y - y - (currentShape.length / 2) - this.speedY;

                for (int i = 0; i < this.game.scene.objs.size(); i++) {

                    boolean sameX = nextPosX == this.game.scene.objs.get(i).x;
                    boolean sameY = nextPosY == this.game.scene.objs.get(i).y;

                    // Validando se tem algum elemento abaixo de alguma parte do atual tetromino
                    if (sameX && sameY) {
                        canMoveDown = false;
                        break outerLoop;
                    }
                }

                // Validando se não encostou no chão
                if (nextPosY < 0) {
                    canMoveDown = false;
                    break outerLoop;
                }
            }
        }
        return canMoveDown;
    }

    private void fastDrop() {
        this.tickTimer = 0;
    }
}
