package com.engine.project1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Weapon {

    public Sprite swordSprite;
    public int swordSize = 64;
    public int xPos = Main.SCREEN_WIDTH / 2;
    public int yPos = Main.SCREEN_HEIGHT / 2;
    public Rectangle swordRectangle;

    public Weapon(TextureAtlas atlas) {
        swordSprite = new Sprite(atlas.findRegion("sword"));
        swordRectangle = new Rectangle(xPos, yPos, swordSize, swordSize);
        swordSprite.setSize(swordSize, swordSize);
        swordSprite.setPosition(xPos, yPos);
    }

    public void draw(SpriteBatch spriteBatch) {
        swordSprite.draw(spriteBatch);
    }

    public void updateRectangle() {
        swordRectangle.setPosition(xPos, yPos);
    }
}
