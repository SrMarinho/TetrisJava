package com.tetris.game;

import com.tetris.game.entities.Background;
import com.tetris.game.entities.Brick;
import com.tetris.game.entities.Renderizable;
import com.tetris.game.entities.Tetromino;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    public int width;
    public int height;
    TetrisGame game;
    List<Renderizable> objs;
    public int[][] map;
    Background bg;

    public Scene(TetrisGame game) {
        this.game = game;
        this.width = 350;
        this.height = (width / 12) * 22;

        this.objs = new ArrayList<>();

        this.map = new int[20][10];
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };


        bg = new Background(this.game);
        this.objs.add(bg);

        this.objs.add(new Tetromino(this.game));
    }

    public void event(){
        for (Renderizable obj : this.objs) {
            obj.event();
        }
    };
    public void update(){
        for (Renderizable obj : this.objs) {
            obj.update();
        }
    };
    public void render(){
        for (Renderizable obj : this.objs) {
            obj.render();
        }
    };
}
