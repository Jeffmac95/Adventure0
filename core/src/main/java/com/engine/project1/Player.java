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
    public boolean isMoving;
    public int lastDirection;

    public Player(TextureAtlas atlas) {
        heroWidth = 64;
        heroHeight = 64;
        xPos = 0;
        yPos = 0;
        speed = 100.0f;
        isMoving = false;
        lastDirection = Input.Keys.DOWN;
        playerSprite = new Sprite(atlas.findRegion("hero"));
        playerSprite.setSize(heroHeight, heroWidth);
    }

    public void draw(SpriteBatch spriteBatch) {
        playerSprite.draw(spriteBatch);
    }

    public void inputHandling(float deltaTime, TextureAtlas atlas) {
        isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yPos += speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero_backwards"));
            if (yPos > Main.HEIGHT - heroHeight) {
                yPos = Main.HEIGHT - heroHeight;
            }

            isMoving = true;
            lastDirection = Input.Keys.UP;

            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yPos -= speed * deltaTime;
                playerSprite = new Sprite(atlas.findRegion("hero"));
                if (yPos < 0) {
                    yPos = 0;
                }

                isMoving = true;
                lastDirection = Input.Keys.DOWN;
            }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xPos -= speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero_walking_left"));
            if (xPos < 0) {
                xPos = 0;
            }

            isMoving = true;
            lastDirection = Input.Keys.LEFT;

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xPos += speed * deltaTime;
            playerSprite = new Sprite(atlas.findRegion("hero_walking_right"));
            if (xPos > Main.WIDTH - heroWidth) {
                xPos = Main.WIDTH - heroWidth;
            }

            isMoving = true;
            lastDirection = Input.Keys.RIGHT;
        }

        if (!isMoving) {
            switch (lastDirection) {
                case Input.Keys.UP:
                    playerSprite = new Sprite(atlas.findRegion("hero_backwards"));
                    break;
                case Input.Keys.DOWN:
                    playerSprite = new Sprite(atlas.findRegion("hero"));
                    break;
                case Input.Keys.LEFT:
                    playerSprite = new Sprite(atlas.findRegion("hero_facing_left"));
                    break;
                case Input.Keys.RIGHT:
                    playerSprite = new Sprite(atlas.findRegion("hero_facing_right"));
                    break;
            }
        }

        playerSprite.setPosition(xPos, yPos);
    }
}
