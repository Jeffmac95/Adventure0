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
    //public Goblin goblinOne;
    public TextureAtlas atlas;
    float deltaTime;

    public ShapeRenderer shapeRenderer;

    public Weapon sword;


    @Override
    public void create() {


        atlas = new TextureAtlas(Gdx.files.internal("atlas/game_atlas_new.atlas"));
        spriteBatch = new SpriteBatch();
        sword = new Weapon(atlas);
        hero = new Player(atlas, sword);
        //goblinOne = new Goblin(atlas);
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
        //goblinOne.draw(spriteBatch);

        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1f, 0f, 0f, 1);

        hero.updateRectangle();
        sword.updateRectangle();

        shapeRenderer.rect(sword.xPos, sword.yPos, sword.swordSize, sword.swordSize);
        shapeRenderer.rect(hero.playerRectangle.x, hero.playerRectangle.y, hero.playerRectangle.width, hero.playerRectangle.height);
        //shapeRenderer.rect(goblinOne.xPos, goblinOne.yPos, goblinOne.goblinWidth, goblinOne.goblinHeight);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        atlas.dispose();
        Player.weapons.clear();
    }

    @Override
    public void resize(int width, int height) {
        spriteBatch.getProjectionMatrix().setToOrtho2D(hero.xPos, hero.yPos, width, height);
    }
}
