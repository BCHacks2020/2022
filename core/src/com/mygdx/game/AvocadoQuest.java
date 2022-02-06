package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.*;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Shroom;

import java.util.ArrayList;
import java.util.List;

public class AvocadoQuest extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture MainChar;
	private List<Enemy> enemies;
	private Texture Projectile;
	private Rectangle mainChar;
	
	@Override
	public void create () {
		//enemy test
		enemies = new ArrayList<>();
		enemies.add(new Shroom(20, 20, 25, 25));

		// Basic visual elements
		batch = new SpriteBatch();
		MainChar = new Texture(Gdx.files.internal("MainChar.png"));
		Projectile = new Texture(Gdx.files.internal("Projectile.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 1000);
		mainChar = new Rectangle();
		mainChar.x = 500;
		mainChar.y = 500;
		mainChar.width = 50;
		mainChar.height = 100;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.begin();
		boolean flip = Gdx.input.getX() < mainChar.x;
		batch.draw(MainChar, flip ? mainChar.x + mainChar.width : mainChar.x, mainChar.y, flip ? -mainChar.width : mainChar.width, mainChar.height);
		for (Enemy enemy : enemies) {
			enemy.render(batch);
		}
		batch.end();
		if(Gdx.input.isKeyPressed(Keys.LEFT)) mainChar.x -= 200 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.RIGHT)) mainChar.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.UP)) mainChar.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.DOWN)) mainChar.y -= 200 * Gdx.graphics.getDeltaTime();
		for (Enemy enemy : enemies) {
			enemy.act(mainChar);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		MainChar.dispose();
	}
}
