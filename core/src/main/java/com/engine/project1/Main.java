package com.engine.project1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    public OrthographicCamera camera;
    public float viewportWidth = 640f;
    public float viewportHeight = 480f;
    public Texture texture;

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


        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        camera.update();
        texture = new Texture("texturetwo.png");
    }


    @Override
    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.54f, 0.54f, 0.54f, 1.0f);

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(texture, 0, 0);

        hero.checkSprite(atlas);
        hero.inputHandling(deltaTime, atlas);
        table.draw(spriteBatch);
        bed.draw(spriteBatch);
        goblinOne.draw(spriteBatch);
        hero.draw(spriteBatch);
        sword.draw(spriteBatch);



        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 1);

        hero.updateRectangle();
        sword.updateRectangle();
        table.updateRectangle();
        goblinOne.updateRectangle();



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
        camera.viewportWidth = viewportWidth;
        camera.viewportHeight = viewportHeight;
        camera.update();
    }
}
