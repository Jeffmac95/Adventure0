package com.engine.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Player extends InputAdapter {

    public Sprite playerSprite;
    public int heroWidth;
    public int heroHeight;
    public float xPos;
    public float yPos;
    public float speed;
    public boolean isMoving;
    public int lastDirection;
    Rectangle playerRectangle;
    Weapon sword;
    public boolean collisionDetected = false;

    public Player(TextureAtlas atlas, Weapon sword) {
        heroWidth = 64;
        heroHeight = 64;
        xPos = 0;
        yPos = 0;
        speed = 100.0f;
        isMoving = false;
        lastDirection = Input.Keys.DOWN;
        playerSprite = new Sprite(atlas.findRegion("hero"));
        playerSprite.setSize(heroHeight, heroWidth);
        playerRectangle = new Rectangle(xPos, yPos, heroWidth, heroHeight);
        this.sword = sword;

    }

    public void draw(SpriteBatch spriteBatch) {
        playerSprite.draw(spriteBatch);
    }

    public void updateRectangle() {
        playerRectangle.setPosition(xPos, yPos);
    }

    public void checkCollision() {

        updateRectangle();
        sword.updateRectangle();

        if (yPos > Main.HEIGHT - heroHeight) {
            yPos = Main.HEIGHT - heroHeight;
        } else if (yPos < 0) {
            yPos = 0;
        }
        if (xPos < 0) {
            xPos = 0;
        } else if (xPos > Main.WIDTH - heroWidth) {
            xPos = Main.WIDTH - heroWidth;
        }

        if (!collisionDetected && playerRectangle.overlaps(sword.swordRectangle)) {
            System.out.println("collision detected");
            sword.swordSprite.setAlpha(0);

            if (xPos > sword.xPos && Math.abs(yPos - sword.yPos) < 3.0f) {
                switch (lastDirection) {
                    case Input.Keys.UP:
                        yPos = sword.yPos + heroHeight;
                        break;
                    case Input.Keys.DOWN:
                        yPos = sword.yPos - heroHeight;
                        break;
                    case Input.Keys.LEFT:
                        xPos = sword.xPos - heroWidth;
                        break;
                    case Input.Keys.RIGHT:
                        xPos = sword.xPos + heroWidth;
                        break;
                }
            }
        }
    }

    public void inputHandling(float deltaTime, TextureAtlas atlas) {
        isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yPos += speed * deltaTime;
            playerSprite.setRegion(atlas.findRegion("hero_backwards"));

            isMoving = true;
            lastDirection = Input.Keys.UP;

            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yPos -= speed * deltaTime;
                playerSprite.setRegion(atlas.findRegion("hero"));

                isMoving = true;
                lastDirection = Input.Keys.DOWN;
            }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xPos -= speed * deltaTime;
            playerSprite.setRegion(atlas.findRegion("hero_walking_left"));

            isMoving = true;
            lastDirection = Input.Keys.LEFT;

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xPos += speed * deltaTime;
            playerSprite.setRegion(atlas.findRegion("hero_walking_right"));

            isMoving = true;
            lastDirection = Input.Keys.RIGHT;
        }

        if (!isMoving) {
            switch (lastDirection) {
                case Input.Keys.UP:
                    playerSprite.setRegion(atlas.findRegion("hero_backwards"));
                    break;
                case Input.Keys.DOWN:
                    playerSprite.setRegion(atlas.findRegion("hero"));
                    break;
                case Input.Keys.LEFT:
                    playerSprite.setRegion(atlas.findRegion("hero_facing_left"));
                    break;
                case Input.Keys.RIGHT:
                    playerSprite.setRegion(atlas.findRegion("hero_facing_right"));
                    break;
            }
        }

        checkCollision();
        playerSprite.setPosition(xPos, yPos);
    }
}
