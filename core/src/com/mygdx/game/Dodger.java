package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

 class Dodger {
    private Texture texture;
    public Vector2 position;
    private float vy;
    private float force = -0.5f;
    private int timer = 8;
    private int frame = 1;

    public Dodger() {
        texture = new Texture("run1.png");
        position = new Vector2(20, 36);
        vy = 0;
    }

    public void render(SpriteBatch batcher) {
        batcher.draw(texture, position.x, position.y);
    }

    public void update() {
        if (position.y > 50) {
            if (vy > 0) {
                texture = new Texture("jump.png");
            } else texture = new Texture("jump2.png");
        } else {
            if (timer == 0) {
                switch (frame) {
                    case 1:
                        texture = new Texture("run2.png");
                        break;
                    case 2:
                        texture = new Texture("run3.png");
                        break;
                    case 3:
                        texture = new Texture("run4.png");
                        break;
                    case 4:
                        texture = new Texture("run5.png");
                        break;
                    case 5:
                        texture = new Texture("run6.png");
                        break;
                    case 6:
                        texture = new Texture("run1.png");
                        break;
                }

                frame++;
                if (frame == 7) frame = 1;
                timer = 8;
            }
            timer--;
        }



        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.W)) && position.y == 36) {
            vy = 9;
            force = -0.5f;
            vy += force;
            position.y += vy;
        }
        if (position.y <= 36) {
            position.y = 36;
            force = 0;
            vy = 0;
        }
        vy += force;
        position.y += vy;
        force = -0.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.A) && position.x > 0) {
            position.x -= 5.5;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && position.x < 672) {
            position.x += 5.5;
        }


    }

    public void recreate() {
        position = new Vector2(20, 36);
        vy = 0;
    }
}
