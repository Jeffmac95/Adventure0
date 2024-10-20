package com.engine.project1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.w3c.dom.Text;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public SpriteBatch spriteBatch;
    public Player hero;
    public TextureAtlas atlas;
    float deltaTime;

    public Weapon sword;


    @Override
    public void create() {


        atlas = new TextureAtlas(Utils.getInternalPath("atlas/game_atlas_new.atlas"));
        spriteBatch = new SpriteBatch();
        hero = new Player(atlas);
        sword = new Weapon(atlas);
    }

    @Override
    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        hero.inputHandling(deltaTime, atlas);
        sword.draw(spriteBatch);
        hero.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

        spriteBatch.dispose();
        atlas.dispose();
    }

    @Override
    public void resize(int width, int height) {

        spriteBatch.getProjectionMatrix().setToOrtho2D(hero.xPos, hero.yPos, width, height);
    }
}
