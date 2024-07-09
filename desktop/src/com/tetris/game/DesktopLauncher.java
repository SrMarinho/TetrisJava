package com.tetris.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tetris.game.TetrisGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		int width = 550;
		int height = 700;
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Tetris");
		config.setWindowedMode(width, height);
		config.setWindowPosition(2400, (738 / 2) - 120);
		new Lwjgl3Application(new TetrisGame(), config);
	}
}
