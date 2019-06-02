package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Obstacles {
    private static Texture texture1;
    public Vector2 pos1;
    private int time = 8;
    private boolean rotation = false;

    public Obstacles() {
        pos1 = new Vector2(800, 50);
        texture1 = new Texture("Suriken1.png");
    }

    public void render(SpriteBatch bathcer) {
        bathcer.draw(texture1, pos1.x, pos1.y);
    }

    private void surikensAction() {
        int speed = 10;
        pos1.x -= speed;

        if (time ==0) {
            if (!rotation) {
                texture1 = new Texture("Suriken3.png");
                rotation = true;
            }
            else {
                texture1 = new Texture("Suriken1.png");
                rotation = false;
            }
            time = 8;
        }
        time--;
    }

    public void update() {
        surikensAction();
    }
}
