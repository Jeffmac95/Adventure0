package com.engine.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Goblin {

    public Sprite goblinSprite;
    public Sprite deadGoblinSprite;
    public int goblinWidth = 64;
    public int goblinHeight = 64;
    public float xPos = Main.SCREEN_WIDTH - goblinWidth;
    public float yPos = Main.SCREEN_HEIGHT / 2 - goblinHeight;
    public float hp = 50.0f;
    public int strength = 5;
    public Rectangle goblinRectangle;
    public Rectangle deadGoblinRectangle;


    public Goblin(TextureAtlas atlas) {
        goblinSprite = new Sprite(atlas.findRegion("goblin"));
        deadGoblinSprite = new Sprite(atlas.findRegion("goblin_dead"));
        goblinRectangle = new Rectangle(xPos, yPos, goblinWidth, goblinHeight);
        deadGoblinRectangle = new Rectangle(xPos, yPos, goblinWidth, goblinHeight);
        goblinSprite.setSize(goblinWidth, goblinHeight);
        deadGoblinSprite.setSize(goblinWidth, goblinHeight);
        goblinSprite.setPosition(xPos, yPos);
        deadGoblinSprite.setPosition(xPos, yPos);
    }

    public void draw(SpriteBatch spriteBatch) {
            goblinSprite.draw(spriteBatch);
    }

    public void drawDeadGoblin(SpriteBatch spriteBatch) {
            deadGoblinSprite.draw(spriteBatch);
    }

    public void updateRectangle() {
            goblinSprite.setPosition(xPos, yPos);
            goblinRectangle.setPosition(xPos, yPos);
            deadGoblinSprite.setPosition(xPos, yPos);
            deadGoblinRectangle.setPosition(xPos, yPos);
    }
}
