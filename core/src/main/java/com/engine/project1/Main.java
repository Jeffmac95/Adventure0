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
    public TextureAtlas atlas;
    public Player hero;
    public Goblin goblinOne;
    public Asset table;
    public Asset bed;
    float deltaTime;
    public ShapeRenderer shapeRenderer;
    public Weapon sword;


    @Override
    public void create() {


        atlas = new TextureAtlas(Gdx.files.internal("Atlas/game_atlas_newnew.atlas"));
        spriteBatch = new SpriteBatch();
        sword = new Weapon(atlas);
        hero = new Player(atlas, sword);
        goblinOne = new Goblin(atlas);
        table = new Asset(atlas);
        bed = new Asset(atlas);
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.54f, 0.54f, 0.54f, 1.0f);

        spriteBatch.begin();

        hero.checkSprite(atlas);
        hero.inputHandling(deltaTime, atlas);
        table.draw(spriteBatch);
        bed.draw(spriteBatch);
        goblinOne.draw(spriteBatch);
        hero.draw(spriteBatch);
        sword.draw(spriteBatch);



        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.8f, 0.0f, 0.0f, 1);

        hero.updateRectangle();
        sword.updateRectangle();
        goblinOne.updateRectangle();
        table.updateRectangle();


        shapeRenderer.rect(sword.xPos, sword.yPos, sword.swordSize, sword.swordSize);
        shapeRenderer.rect(hero.playerRectangle.x, hero.playerRectangle.y, hero.playerRectangle.width, hero.playerRectangle.height);
        shapeRenderer.rect(goblinOne.xPos, goblinOne.yPos, goblinOne.goblinWidth, goblinOne.goblinHeight);
        shapeRenderer.rect(table.tableX, table.tableY, table.tableWidth, table.tableHeight);
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
