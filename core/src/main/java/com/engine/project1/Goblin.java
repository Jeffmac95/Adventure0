package com.engine.project1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Goblin {

    public Sprite goblinSprite;
    public int goblinWidth = 64;
    public int goblinHeight = 64;
    public int xPos = Main.WIDTH - goblinWidth;
    public int yPos = Main.HEIGHT - goblinHeight;
    public Rectangle goblinRectangle;

    public Goblin(TextureAtlas atlas) {
        goblinSprite = new Sprite(atlas.findRegion("goblin"));
        goblinRectangle = new Rectangle(xPos, yPos, goblinWidth, goblinHeight);
        goblinSprite.setSize(goblinWidth, goblinHeight);
        goblinSprite.setPosition(xPos, yPos);
    }

    public void draw(SpriteBatch spriteBatch) {
        goblinSprite.draw(spriteBatch);
    }

    public void updateRectangle() {
        goblinSprite.setPosition(xPos, yPos);
    }
}
