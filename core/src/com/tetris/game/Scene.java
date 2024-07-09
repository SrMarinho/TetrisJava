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

    public Scene(TetrisGame game) {
        this.game = game;

        this.shape_history = new ArrayList<>();
        this.color_history = new ArrayList<>();

        this.objs = new ArrayList<>();

        this.initScene();
    }

    public void initScene() {
        Random random = new Random();

        this.bg = new Background(this.game);
        this.objs.add(new Brick(this.game, 5, 0, 0));
        this.current_tetromino = new Tetromino(
                this.game,
                random.nextInt(0,7),
                random.nextInt(0,7)
        );

    }

    public void event() {
        for (Brick obj : this.objs) {
            obj.event();
        }
        this.current_tetromino.event();
    }

    public void update() {
        System.out.println(this.shape_history.size());
        if (this.shape_history.size() == this.current_tetromino.shapeList.length) {
            this.shape_history = new ArrayList<>();
        }
        if (this.color_history.size() == this.game.textureList.size()) {
            this.color_history = new ArrayList<>();
        }
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
                        this.current_tetromino.x + x - (int)(cShape[0].length / 2),
                        this.current_tetromino.y - y - (int)(cShape.length / 2),
                        this.current_tetromino.color
                    )
                );
            }
        }

        int nextShape = random.nextInt(0,7);
        if (!this.shape_history.isEmpty()) {
            while (this.shape_history.contains(nextShape)) {
                nextShape = random.nextInt(0,7);
            }
        }
        this.shape_history.add(nextShape);

        int nextColor = random.nextInt(0,7);

        if (!this.color_history.isEmpty()) {
            while (this.color_history.contains(nextColor)) {
                nextColor = random.nextInt(0,7);
            }
        }
        this.color_history.add(nextColor);

        this.current_tetromino = new Tetromino(
                this.game,
                nextShape,
                nextColor
        );
    }
}
