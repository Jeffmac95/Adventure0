package com.engine.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player extends InputAdapter {

    public Sprite playerSprite;
    public int heroWidth = 64;
    public int heroHeight = 64;
    public float xPos = 0.0f;
    public float yPos = 0.0f;
    public float speed = 100.0f;
    public boolean isMoving = false;
    public int lastDirection = Input.Keys.DOWN;
    Rectangle playerRectangle;
    Weapon sword;
    public boolean collisionDetected = false;
    public static ArrayList<Weapon> weapons;
    public float animationTimer = 0.0f;
    public float frameDuration = 0.2f;

    public Player(TextureAtlas atlas, Weapon sword) {

        playerSprite = new Sprite(atlas.findRegion("hero"));
        playerSprite.setSize(heroWidth, heroHeight);
        playerRectangle = new Rectangle(xPos, yPos, heroWidth, heroHeight);
        this.sword = sword;

        weapons = new ArrayList<Weapon>();
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

            weapons.add(sword);

            if (xPos + sword.swordSize > sword.xPos && Math.abs(yPos - sword.yPos) < sword.swordSize) {
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
        animationTimer += deltaTime;
        isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yPos += speed * deltaTime;

            if (animationTimer > frameDuration) {
                playerSprite.setRegion(atlas.findRegion("hero_backwards_walking_left"));
            } else {
                playerSprite.setRegion(atlas.findRegion("hero_backwards_walking_right"));
            }

            if (animationTimer > frameDuration * 2) {
                animationTimer = 0.0f;
            }

            isMoving = true;
            lastDirection = Input.Keys.UP;

            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yPos -= speed * deltaTime;

                if (animationTimer > frameDuration) {
                    playerSprite.setRegion(atlas.findRegion("hero_walking_right_foot"));
                } else {
                    playerSprite.setRegion(atlas.findRegion("hero_walking_left_foot"));
                }

                if (animationTimer > frameDuration * 2) {
                    animationTimer = 0.0f;
                }

                isMoving = true;
                lastDirection = Input.Keys.DOWN;
            }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xPos -= speed * deltaTime;

            if (animationTimer > frameDuration) {
                playerSprite.setRegion(atlas.findRegion("hero_facing_left"));
            } else {
                playerSprite.setRegion(atlas.findRegion("hero_walking_left"));
            }

            // resetting the timer if it passes double the frame duration.
            if (animationTimer > frameDuration * 2) {
                animationTimer = 0.0f;
            }

            isMoving = true;
            lastDirection = Input.Keys.LEFT;

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xPos += speed * deltaTime;

            if (animationTimer > frameDuration) {
                playerSprite.setRegion(atlas.findRegion("hero_facing_right"));
            } else {
                playerSprite.setRegion(atlas.findRegion("hero_walking_right"));
            }

            if (animationTimer > frameDuration * 2) {
                animationTimer = 0.0f;
            }

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
                default:
                    playerSprite.setRegion(atlas.findRegion("hero"));
            }
            animationTimer = 0.0f;
        }

        checkSprite(atlas);
        checkCollision();
        playerSprite.setPosition(xPos, yPos);
    }

    public void checkSprite(TextureAtlas atlas) {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i) == sword) {
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

                    if (animationTimer > frameDuration) {
                        playerSprite.setRegion(atlas.findRegion(("hero_backwards_walking_left")));
                    } else {
                        playerSprite.setRegion(atlas.findRegion("hero_backwards_walking_right"));
                    }

                    if (animationTimer > frameDuration * 2) {
                        animationTimer = 0.0f;
                    }

                    isMoving = true;
                    lastDirection = Input.Keys.UP;

                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

                    if (animationTimer > frameDuration) {
                        playerSprite.setRegion(atlas.findRegion("hero_with_sword_walking_left_foot"));
                    } else {
                        playerSprite.setRegion(atlas.findRegion("hero_with_sword_walking_right_foot"));
                    }

                    if (animationTimer > frameDuration * 2) {
                        animationTimer = 0.0f;
                    }

                    isMoving = true;
                    lastDirection = Input.Keys.DOWN;
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                    if (animationTimer > frameDuration) {
                        playerSprite.setRegion(atlas.findRegion("hero_facing_left_with_sword"));
                    } else {
                        playerSprite.setRegion(atlas.findRegion("hero_walking_left_with_sword"));
                    }

                    if (animationTimer > frameDuration * 2) {
                        animationTimer = 0.0f;
                    }

                    isMoving = true;
                    lastDirection = Input.Keys.LEFT;
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

                    if (animationTimer > frameDuration) {
                        playerSprite.setRegion(atlas.findRegion("hero_facing_right_with_sword"));
                    } else {
                        playerSprite.setRegion(atlas.findRegion("hero_walking_right_with_sword"));
                    }

                    if (animationTimer > frameDuration * 2) {
                        animationTimer = 0.0f;
                    }

                    isMoving = true;
                    lastDirection = Input.Keys.RIGHT;
                }

                if (!isMoving) {
                    switch (lastDirection) {
                        case Input.Keys.UP:
                            playerSprite.setRegion(atlas.findRegion("hero_backwards"));
                            break;
                        case Input.Keys.DOWN:
                            playerSprite.setRegion(atlas.findRegion("hero_with_sword"));
                            break;
                        case Input.Keys.LEFT:
                            playerSprite.setRegion(atlas.findRegion("hero_facing_left_with_sword"));
                            break;
                        case Input.Keys.RIGHT:
                            playerSprite.setRegion(atlas.findRegion("hero_facing_right_with_sword"));
                            break;
                        default:
                            playerSprite.setRegion(atlas.findRegion("hero_with_sword"));
                    }
                }
            }
        }
    }
}
