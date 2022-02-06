package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy {

    protected static final int VISION_RANGE = 2000;
    private float x;
    private float y;
    private int width;
    private int height;

    protected Texture texture;
    public abstract int getDamage();
    public abstract int getSpeed();

    public abstract void render(SpriteBatch batch);

    public abstract void act(Rectangle mainChar);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
