package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Meteors {
    private Texture texture;
    public Vector2 pos;
    private float vy = 0;
    private float g = -0.05f;

    public Meteors() {
        texture = new Texture("meteor.png");
        int xPos = new Random().nextInt(700 - 20) + 1 + 20;
        pos = new Vector2(xPos, 720);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }

    private void meteorsAction() {
        vy += g;
        pos.y += vy;
    }

    public void update() {
        meteorsAction();
    }
}
