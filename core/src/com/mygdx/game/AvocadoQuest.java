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
import com.mygdx.game.enemies.Chippy;
import com.mygdx.game.enemies.Projectile;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class AvocadoQuest extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture MainChar;
	private List<Enemy> enemies;
	private List<Enemy> projectiles;
	private Rectangle mainChar;
	
	@Override
	public void create () {
		//enemy test
		enemies = new ArrayList<>();
		projectiles = new ArrayList<>();
		for(int i=0; i<5; i++){
			int enemytype = (int)(Math.random() * 10 + 1);
			if(enemytype%2==0)
				enemies.add(new Shroom((int)(Math.random() * 1000 + 1), (int)(Math.random() * 1000 + 1), 25, 25));
			else
				enemies.add(new Chippy((int)(Math.random() * 1000 + 1), (int)(Math.random() * 1000 + 1), 25, 25));
		}
		
		// Basic visual elements
		batch = new SpriteBatch();
		MainChar = new Texture(Gdx.files.internal("MainChar.png"));
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
		if(Gdx.input.isTouched()){
			projectiles.add(new Projectile(mainChar.x, mainChar.y));
		}
		batch.begin();
		boolean flip = Gdx.input.getX() < mainChar.x;
		batch.draw(MainChar, flip ? mainChar.x + mainChar.width : mainChar.x, mainChar.y, flip ? -mainChar.width : mainChar.width, mainChar.height);
		for (Enemy enemy : enemies) {
			enemy.render(batch);
		}
		for (Enemy bullet : projectiles) {
			bullet.render(batch);
		}
		batch.end();
		if(Gdx.input.isKeyPressed(Keys.A)) mainChar.x -= 200 * Gdx.graphics.getDeltaTime();
   		if(Gdx.input.isKeyPressed(Keys.D)) mainChar.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.W)) mainChar.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.S)) mainChar.y -= 200 * Gdx.graphics.getDeltaTime();
		for (Enemy enemy : enemies) {
			enemy.act(mainChar);
		}
		for (Enemy bullet : projectiles) {
			bullet.act(mainChar);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		MainChar.dispose();
	}
}
