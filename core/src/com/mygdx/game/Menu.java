package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu {
    private Texture menu;
    public static boolean mm = true;
    private int choice = 1;

    public Menu() {
        menu = new Texture("menu1.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(menu, 0, 0);
    }

    public void update() {
        if (mm) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                switch (choice) {
                    case 1: {
                        choice = 2;
                        menu = new Texture("menu2.png");
                        break;
                    }
                    case 2: {
                        choice = 1;
                        menu = new Texture("menu1.png");
                        break;
                    }
                }
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                switch (choice) {
                    case 1: {
                        choice = 2;
                        menu = new Texture("menu2.png");
                        break;
                    }
                    case 2: {
                        choice = 1;
                        menu = new Texture("menu1.png");
                        break;
                    }
                }
            }
        }

        if (mm && Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            switch (choice) {
                case 1: {
                    mm = false;
                    MainDodger.recreate();
                    break;
                }
                case 2: {
                    Gdx.app.exit();
                    break;
                }
            }
        }
    }
}
