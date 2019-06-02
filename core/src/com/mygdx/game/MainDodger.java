package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainDodger extends ApplicationAdapter {
	private SpriteBatch batch;
	private com.mygdx.game.Background bg;
	private static Dodger ch;
	private BitmapFont font1;
	private BitmapFont font2;

    private int timer1 = 0;
    private int timer2 = 0;
    private static List<Obstacles> surikens;
    private static List<Meteors> meteors;
    private static boolean gameOver = false;
    private Texture restart;
    private Menu menu;
    private int timer = 0;
    private int score = 0;
    private int result;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font1 = new BitmapFont();
		font2 = new BitmapFont();
		bg = new Background();
		ch = new Dodger();
		menu = new Menu();

		surikens = new ArrayList<>();
		surikens.add(new Obstacles());

		meteors = new ArrayList<>();
		meteors.add(new Meteors());

		gameOver = false;
		restart = new Texture("restart.png");
	}

	@Override
	public void render () {
	    update();
		font1.setColor(1f, 1f, 1f, 1f);
		font1.getData().setScale(3);
		font2.setColor(1f, 1f, 1f, 1f);
		font2.getData().setScale(2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (Menu.mm) {
			menu.render(batch);
		} else {
			bg.render(batch);

			if (!gameOver) {
				ch.render(batch);

				for (Meteors meteor: meteors) {
					if (meteor.pos.y > 36) {
						meteor.render(batch);
					}
				}

				for (Obstacles suriken: surikens) {
					suriken.render(batch);
				}
			} else {
				FileHandle file = Gdx.files.internal("hs.txt");
				String text = file.readString();

				batch.draw(restart,160, 140);
				font2.draw(batch, "Your score: " + result, 170, 290);
				font2.draw(batch, "High score: " + text, 170, 250);
			}
		}

		if (!Menu.mm && !gameOver) font1.draw(batch, "score: " + String.valueOf(score), 535, 450);

		batch.end();
	}

	private void surCreating() {
		if (timer1 == 0) {
			timer1 = new Random().nextInt(70 - 35) + 1 + 35;
			surikens.add(new Obstacles());
		}
		timer1--;
	}

	private void surCollision() {
		for (Obstacles sur: surikens) {
			if (ch.position.x > sur.pos1.x - 42 && ch.position.x < sur.pos1.x + 39) {
				if (ch.position.y <= sur.pos1.y + 39) {
					gameOver = true;
				}
			}
			sur.update();
		}

		result = score;
		if (result > new Integer(Gdx.files.internal("hs.txt").readString())) {
			FileHandle file = Gdx.files.local("hs.txt");
			file.writeString(String.valueOf(result), false);
		}
	}

	private void metCreating() {
    	if (timer2 == 0) {
    		timer2 = new Random().nextInt(50 - 30) + 1 + 30;
    		meteors.add(new Meteors());
		}
		timer2--;
	}

	private void metCollision() {
    	for (Meteors meteor: meteors) {
			if (ch.position.x > meteor.pos.x - 42 && ch.position.x < meteor.pos.x + 36) {
				if (ch.position.y <= meteor.pos.y + 36 && (double) ch.position.y >= meteor.pos.y - 60 ) {
						gameOver = true;
				}
			}
			meteor.update();
		}

		result = score;
    	if (result > new Integer(Gdx.files.internal("hs.txt").readString())) {
			FileHandle file = Gdx.files.local("hs.txt");
			file.writeString(String.valueOf(result), false);
		}
	}
	private void update() {
		surCreating();
    	metCreating();

        bg.update();
        ch.update();
        menu.update();

        surCollision();
        metCollision();

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && gameOver) {
			timer = 0;
			score = 0;
			recreate();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && gameOver) {
			Menu.mm = true;
		}

		if (!gameOver && !Menu.mm) {
			timer++;
			if (timer % 60 == 0) {
				score++;
			}
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

	static void recreate() {
    	ch.recreate();
    	surikens = new ArrayList<>();
    	meteors = new ArrayList<>();
		gameOver = false;
		Menu.mm = false;
	}
}
