package com.tetris.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TetrisGame extends ApplicationAdapter {
	public SpriteBatch batch;
	public List<Texture> textureList;
	public Scene scene;
	public float grid_col_size;
	public float grid_row_size;
	public ShapeRenderer shape;
	public int move_time;
	Instant last_move_time;

	public TetrisGame() {
		this.textureList = new ArrayList<>();
		this.move_time = 200;
		this.last_move_time = Instant.now();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		this.grid_col_size = (float) Gdx.graphics.getWidth() / 10;
		this.grid_row_size = (float) Gdx.graphics.getHeight() / 20;

		this.textureList.add(new Texture("1.png"));
		this.textureList.add(new Texture("2.png"));
		this.textureList.add(new Texture("3.png"));
		this.textureList.add(new Texture("4.png"));
		this.textureList.add(new Texture("5.png"));
		this.textureList.add(new Texture("6.png"));

		this.scene = new Scene(this);

	}

	@Override
	public void render () {
		ScreenUtils.clear(30/255F, 30/255F, 30/255F, 1);

		this.scene.event();
		Instant current_time = Instant.now();
		Duration duration = Duration.between(this.last_move_time, current_time);
		if (duration.toMillis() > this.move_time) {
			this.scene.update();
			this.last_move_time = current_time;
		}
		this.scene.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Texture texture : this.textureList) {
			texture.dispose();
		}
	}
}
