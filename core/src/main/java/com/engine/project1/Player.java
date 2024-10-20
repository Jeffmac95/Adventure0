package com.engine.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player extends InputAdapter {

    public Sprite playerSprite;
    public int heroWidth;
    public int heroHeight;
    public float xPos;
    public float yPos;
    public float speed;

    public Player(TextureAtlas atlas) {
        heroWidth = 64;
        heroHeight = 64;
        xPos = 0;
        yPos = 0;
        speed = 100.0f;
        playerSprite = new Sprite(atlas.findRegion("hero"));
        playerSprite.setSize(heroHeight, heroWidth);
    }

    public void draw(SpriteBatch spriteBatch) {
        playerSprite.draw(spriteBatch);
    }

    public void inputHandling(float deltaTime, TextureAtlas atlas) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yPos += speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero_backwards"));
            if (yPos > Main.HEIGHT - heroHeight) {
                yPos = Main.HEIGHT - heroHeight;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yPos -= speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero"));
            if (yPos < 0) {
                yPos = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xPos -= speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero_facing_left"));
            if (xPos < 0) {
                xPos = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xPos += speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero_facing_right"));
            if (xPos > Main.WIDTH - heroWidth) {
                xPos = Main.WIDTH - heroWidth;
            }
        }

        playerSprite.setPosition(xPos, yPos);
    }
}
