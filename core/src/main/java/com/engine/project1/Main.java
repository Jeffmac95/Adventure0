package com.engine.project1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    public static final float SCREEN_WIDTH = 640;
    public static final float SCREEN_HEIGHT = 480;
    public SpriteBatch spriteBatch;
    public TextureAtlas atlas;
    public Player hero;
    public Goblin goblinOne;
    public Asset table;
    public Asset bed;
    float deltaTime;
    public MyTable myTable;
    public ShapeRenderer shapeRenderer;
    public Weapon sword;
    public OrthographicCamera camera;
    public float viewportWidth = 640f;
    public float viewportHeight = 480f;
    public Texture texture;
    public BitmapFont font;


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
        font = new BitmapFont(Gdx.files.internal("Fonts/my_font.fnt"));
        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        camera.update();
        texture = new Texture("texturetwo.png");
        myTable = new MyTable(atlas);
    }


    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);

        deltaTime = Gdx.graphics.getDeltaTime();


        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(texture, 0, 0); //background

        hero.checkSprite(atlas);
        hero.inputHandling(deltaTime, atlas);
        table.draw(spriteBatch);
        bed.draw(spriteBatch);
        goblinOne.draw(spriteBatch);
        hero.draw(spriteBatch);
        sword.draw(spriteBatch);
        hero.checkCollision(deltaTime, atlas);
        //font.draw(spriteBatch, "TEST", 0, Gdx.graphics.getHeight());

        spriteBatch.end();

        hero.updateRectangle();
        sword.updateRectangle();
        table.updateRectangle();
        goblinOne.updateRectangle();

        myTable.stage.act(deltaTime);
        myTable.stage.draw();



        if (myTable.isDrawing) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 1);
            shapeRenderer.rect(sword.xPos, sword.yPos, sword.swordSize, sword.swordSize);
            shapeRenderer.rect(hero.playerRectangle.x, hero.playerRectangle.y, hero.playerRectangle.width, hero.playerRectangle.height);
            shapeRenderer.rect(goblinOne.xPos, goblinOne.yPos, goblinOne.goblinWidth, goblinOne.goblinHeight);
            shapeRenderer.rect(table.tableX, table.tableY, table.tableWidth, table.tableHeight);
            shapeRenderer.end();

        }


    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        atlas.dispose();
        myTable.stage.dispose();
        font.dispose();
        shapeRenderer.dispose();
        Player.weapons.clear();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = viewportWidth;
        camera.viewportHeight = viewportHeight;
        camera.update();
        myTable.stage.getViewport().update(width, height, true);
    }
}
