package com.engine.project1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Weapon {

    public Sprite swordSprite;
    public float swordSize = 64;
    public float xPos = Main.SCREEN_WIDTH / 2;
    public float yPos = Main.SCREEN_HEIGHT / 2 + (swordSize / 2);
    public Rectangle swordRectangle;
    public String sword = "Sword";

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

    @Override
    public String toString() {
        return "Weapon: " + this.sword;
    }

}
