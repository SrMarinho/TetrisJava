package com.tetris.game;

import com.tetris.game.entities.Background;
import com.tetris.game.entities.Brick;
import com.tetris.game.entities.Renderizable;
import com.tetris.game.entities.Tetromino;

import java.util.*;

public class Scene {
    public int width;
    public int height;
    public int block_size;
    TetrisGame game;
    public List<Brick> objs;
    public String[][] map;
    Background bg;
    public List<Integer> shape_history;
    public List<Integer> color_history;
    public Tetromino current_tetromino;
    public int score;

    public Scene(TetrisGame game) {
        this.game = game;
        this.score = 0;
        this.shape_history = new ArrayList<>();
        this.color_history = new ArrayList<>();

        this.width = 35 * 10;
        this.height = (this.width / 10) * 20;

        this.objs = new ArrayList<>();

        this.initScene();
    }

    public void initScene() {
        Random random = new Random();

        this.bg = new Background(this.game);

//        this.objs.add(new Brick(this.game, 0, 0, 5));
//        this.objs.add(new Brick(this.game, 1, 0, 5));
//        this.objs.add(new Brick(this.game, 2, 0, 5));
//        this.objs.add(new Brick(this.game, 3, 0, 5));
//        this.objs.add(new Brick(this.game, 6, 0, 5));
//        this.objs.add(new Brick(this.game, 7, 0, 5));
//        this.objs.add(new Brick(this.game, 8, 0, 5));
//        this.objs.add(new Brick(this.game, 9, 0, 5));

        this.current_tetromino = new Tetromino(
                this.game,
                random.nextInt(0, 7),
                random.nextInt(0, 7)
        );

    }

    public void event() {
        for (Brick obj : this.objs) {
            obj.event();
        }
        this.current_tetromino.event();
    }

    public void update() {
        this.cleanTetrominoHistory();
        this.cleanRows();
        this.removeEmptyRows();

        this.current_tetromino.update();
        for (Brick obj : this.objs) {
            if (obj.y >= this.game.rows) {
                this.game.gameOver = true;
                break;
            }
        }
    }

    public void render() {
        this.bg.render();

        for (Brick obj : this.objs) {
            obj.render();
        }

        this.current_tetromino.render();
    }

    public void newTetromino() {
        if (this.game.gameOver) return;
        Random random = new Random();

        int[][] cShape = this.current_tetromino.shape.clone();

        for (int y = 0; y < cShape.length; y++) {
            for (int x = 0; x < cShape[0].length; x++) {
                if (cShape[y][x] == 0) continue;
                this.objs.add(
                        new Brick(
                                this.game,
                                this.current_tetromino.x + x - (int) (cShape[0].length / 2),
                                this.current_tetromino.y - y - (int) (cShape.length / 2),
                                this.current_tetromino.color
                        )
                );
            }
        }

        int nextShape = random.nextInt(0, 7);
        if (!this.shape_history.isEmpty()) {
            while (this.shape_history.contains(nextShape)) {
                nextShape = random.nextInt(0, 7);
            }
        }
        this.shape_history.add(nextShape);

        int nextColor = random.nextInt(0, 7);

        if (!this.color_history.isEmpty()) {
            while (this.color_history.contains(nextColor)) {
                nextColor = random.nextInt(0, 7);
            }
        }
        this.color_history.add(nextColor);

        this.current_tetromino = new Tetromino(
                this.game,
                nextShape,
                nextColor
        );
    }

    public void cleanRows() {
        int rowsCleaned = 0;

        for (int y = 0; y < this.game.rows; y++) {
            List<Brick> elementsInRows = new ArrayList<>();

            elementsInRows = new ArrayList<>();
            // Pegando elementos na linha
            for (Brick obj : this.objs) {
                if (obj.y == y) elementsInRows.add(obj);
            }
            // Apagando elementos se e alinha estiver cheia
            if (elementsInRows.size() == this.game.cols) {
                rowsCleaned += 1;
                this.objs.removeAll(elementsInRows);
            }
        }

        switch (rowsCleaned) {
            case 0:
                break;
            case 1:
                this.score += 100;
                break;
            case 2:
                this.score += 300;
                break;
            case 3:
                this.score += 500;
                break;
            default:
                this.score += 800;
                break;
        }
    }

    public void removeEmptyRows() {
        boolean emptyRow = false;
        for (int y = 0; y < this.game.rows; y++) {
            List<Brick> elementsInRows = new ArrayList<>();

            for (Brick obj : this.objs) {
                // Pegando elementos na linha
                if (obj.y == y) {
                    elementsInRows.add(obj);
                }
            }

            // Verificando se a linha esta vazia
            if (elementsInRows.isEmpty()) {
                emptyRow = true;
            }

            //Movendo a linha para baixo
            if (emptyRow) {
                for (Brick element : elementsInRows) {
                    element.y -= 1;
                }
            }
        }
    }

    public void cleanTetrominoHistory() {
        if (this.shape_history.size() == this.current_tetromino.shapeList.length) {
            this.shape_history = new ArrayList<>();
        }
        if (this.color_history.size() == this.game.textureList.size()) {
            this.color_history = new ArrayList<>();
        }
    }
}
