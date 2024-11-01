package com.engine.project1;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Asset {
    public Sprite tableSprite;
    public Sprite bedSprite;
    public Animation<TextureAtlas.AtlasRegion> fireAnimation;
    public Sprite fire;
    public float tableX = Main.SCREEN_WIDTH / 2;
    public float tableY = Main.SCREEN_HEIGHT /2;
    public int tableWidth = 128;
    public int tableHeight = 100;
    public Rectangle tableRectangle;
    public int bedX = 0;
    public int bedY = 0;
    public int bedSize = 64;
    public Sprite holeInFloorSprite;
    public Rectangle holeInFloorRectangle;
    public int holeSize = 64;
    public float holeX = Main.SCREEN_WIDTH - holeSize;
    public float holeY = 0;
    public float fireX = Main.SCREEN_WIDTH / 2;
    public float fireY = 0;

    public Asset(TextureAtlas atlas) {
        tableSprite = new Sprite(atlas.findRegion("table"));
        bedSprite = new Sprite(atlas.findRegion("bed"));
        tableRectangle = new Rectangle(tableX, tableY, tableWidth, tableHeight);
        holeInFloorSprite = new Sprite(atlas.findRegion("hole_in_floor"));
        holeInFloorRectangle = new Rectangle(holeX, holeY, holeSize, holeSize);

        fireAnimation = new Animation<>(0.10f, atlas.findRegions("fire"));
        fireAnimation.setPlayMode(Animation.PlayMode.LOOP);
        fire = new Sprite(fireAnimation.getKeyFrame(0));
    }

    public void draw(SpriteBatch spriteBatch) {
        tableSprite.draw(spriteBatch);
        bedSprite.draw(spriteBatch);
        holeInFloorSprite.draw(spriteBatch);
        tableSprite.setPosition(tableX, tableY);
        bedSprite.setPosition(bedX, bedY);
        holeInFloorSprite.setPosition(holeX, holeY);
    }

    public void updateRectangle() {
        tableRectangle.setPosition(tableX, tableY);
        holeInFloorRectangle.setPosition(holeX, holeY);
    }
}
