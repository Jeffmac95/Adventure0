package com.engine.project1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Asset {
    public Sprite tableSprite;
    public Sprite bedSprite;
    public int tableX = Main.WIDTH / 2;
    public int tableY = Main.HEIGHT /2;
    public int tableWidth = 128;
    public int tableHeight = 128;
    public int bedX = 0;
    public int bedY = 0;
    public int bedWidth = 64;
    public int bedHeight = 64;

    public Asset(TextureAtlas atlas) {
        tableSprite = new Sprite(atlas.findRegion("table"));
        bedSprite = new Sprite(atlas.findRegion("bed"));
    }

    public void draw(SpriteBatch spriteBatch) {
        tableSprite.draw(spriteBatch);
        bedSprite.draw(spriteBatch);

        tableSprite.setPosition(tableX, tableY);
        bedSprite.setPosition(bedX, bedY);
    }
}
