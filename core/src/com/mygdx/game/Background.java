package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private Texture texture;
    private Vector2 position;
    public static int speed = 5;


    public Background() {
        texture = new Texture("bg3.png");
        position = new Vector2(0, 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        position.x -= speed;
        if (position.x <= -720) position.x = 0;
    }
}