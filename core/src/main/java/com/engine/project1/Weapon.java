package com.engine.project1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Weapon {

    public Sprite swordSprite;
    public int swordSize = 64;
    public int startXpos = Main.WIDTH / 2;
    public int startYpos = Main.HEIGHT / 2;

    public Weapon(TextureAtlas atlas) {
        swordSprite = new Sprite(atlas.findRegion("sword"));
        swordSprite.setSize(swordSize, swordSize);
        swordSprite.setPosition(startXpos, startYpos);
    }

    public void draw(SpriteBatch spriteBatch) {
        swordSprite.draw(spriteBatch);
    }
}
