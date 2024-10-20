package com.engine.project1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.w3c.dom.Text;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public SpriteBatch spriteBatch;
    public Player hero;
    public TextureAtlas atlas;
    float deltaTime;

    public ShapeRenderer shapeRenderer;

    public Weapon sword;


    @Override
    public void create() {


        atlas = new TextureAtlas(Utils.getInternalPath("atlas/game_atlas_new.atlas"));
        spriteBatch = new SpriteBatch();
        sword = new Weapon(atlas);
        hero = new Player(atlas, sword);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        hero.checkSprite(atlas);
        hero.inputHandling(deltaTime, atlas);
        hero.draw(spriteBatch);
        sword.draw(spriteBatch);

        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1f, 0f, 0f, 1);

        sword.updateRectangle();
        hero.updateRectangle();

        shapeRenderer.rect(sword.xPos, sword.yPos, sword.swordSize, sword.swordSize);
        shapeRenderer.rect(hero.playerRectangle.x, hero.playerRectangle.y, hero.playerRectangle.width, hero.playerRectangle.height);
        shapeRenderer.end();
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
