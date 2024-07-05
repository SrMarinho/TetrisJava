package com.tetris.game;

import com.tetris.game.entities.Background;
import com.tetris.game.entities.Brick;
import com.tetris.game.entities.Renderizable;
import com.tetris.game.entities.Tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Scene {
    public int width;
    public int height;
    TetrisGame game;
    List<Brick> objs;
    Tetromino current_tetromino;
    public String[][] map;
    Background bg;
    public int current_tetromino_type;
    public int current_color;

    public Scene(TetrisGame game) {
        this.game = game;
        this.width = 350;
        this.height = (width / 10) * 20;

        this.objs = new ArrayList<>();

        this.map = new String[][]{
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
        };

        this.bg = new Background(this.game);

        this.current_tetromino = new Tetromino(this.game, 1);
    }

    public void event() {
        for (Brick obj : this.objs) {
            obj.event();
        }
        this.current_tetromino.event();
    }
    ;

    public void update() {
        this.current_tetromino.update();
    }
    ;

    public void render() {
        this.bg.render();
        this.current_tetromino.render();
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[0].length; x++) {
                switch (this.map[y][x]){
                    case "0":
                        new Brick(this.game, x, y, 0).render();
                        break;
                    case "1":
                        new Brick(this.game, x, y, 1).render();
                        break;
                    case "2":
                        new Brick(this.game, x, y, 2).render();
                        break;
                    case "3":
                        new Brick(this.game, x, y, 3).render();
                        break;
                    case "4":
                        new Brick(this.game, x, y, 4).render();
                        break;
                    case "5":
                        new Brick(this.game, x, y, 5).render();
                        break;
                }
            }

        }
    }
    ;
    public boolean insideMap(int x, int y){
        return 0 < x && x < this.map[0].length - 1
                && 0 < y && y < this.map.length - 1;
    }
    ;
    public int getRandomPiece(int start, int end, Integer[] history) {
        Random random = new Random();
        int current = random.nextInt(end - start) + start;
        while (Arrays.asList(history).contains(current)){
            current = random.nextInt(end - start) + start;
        }
        return current;
    };
}
