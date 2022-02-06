package com.mygdx.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Shroom extends Enemy {

    private static final int DAMAGE = 10;
    private static final int SPEED = 120;

    public Shroom(int x, int y, int width, int height) {
        this.texture = new Texture(Gdx.files.internal("Shroom.png"));
        setHeight(height);
        setWidth(width);
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
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(Rectangle mainChar) {
        if(((Math.pow(mainChar.x, 2) + Math.pow(mainChar.y, 2)) - (Math.pow(getX(), 2) + Math.pow(getY(), 2))) < Math.pow(VISION_RANGE, 2)) {
            int xDir = getX() - mainChar.x > 0 ? -1 : 1;
            int yDir = getY() - mainChar.y > 0 ? -1 : 1;
            setY(getY() + SPEED * Gdx.graphics.getDeltaTime() * yDir);
            setX(getX() + SPEED * Gdx.graphics.getDeltaTime() * xDir);
        }
    }
}
