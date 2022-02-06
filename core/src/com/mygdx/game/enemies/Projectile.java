package com.mygdx.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Projectile extends Enemy {

    private static final int DAMAGE = 10;
    private static final int SPEED = 100;

    public Projectile(float x, float y) {
        this.texture = new Texture(Gdx.files.internal("Projectile.png"));
        setHeight(10);
        setWidth(10);
        setX(x);
        setY(y);
    }

    @Override
    public int getDamage() {
        return DAMAGE;
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, getX(), getY(),10, 10);
    }

    @Override
    public void act(Rectangle mainChar) {
       setX(getX() +Gdx.input.getX() * SPEED * Gdx.graphics.getDeltaTime());
        setY(getY() +Gdx.input.getY() * SPEED * Gdx.graphics.getDeltaTime());
    }
}
