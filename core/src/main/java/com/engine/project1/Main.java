package com.engine.project1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    public ShapeRenderer shapeRenderer;
    public Weapon sword;

    public OrthographicCamera camera;
    public float viewportWidth = 640f;
    public float viewportHeight = 480f;
    public Texture texture;

    public Stage stage;
    public Table stageTable;

    public Skin debugButtonSkin;
    public Skin invButtonSkin;

    public boolean isDrawing = false;

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


        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        camera.update();
        texture = new Texture("texturetwo.png");

        debugButtonSkin = new Skin(Gdx.files.internal("ui/buttonStyle.json"));
        invButtonSkin = new Skin(Gdx.files.internal("ui/invButtonStyle.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stageTable = new Table();
        stageTable.top().left();
        stageTable.setFillParent(true);
        stage.addActor(stageTable);
        stageTable.setDebug(true);

        Button debugButton = new Button(debugButtonSkin);
        Button invButton = new Button(invButtonSkin);

        stageTable.add(debugButton).width(100).height(50);
        stageTable.add(invButton).width(75).height(50);

        debugButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {

                isDrawing = !isDrawing;
            }
        });
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

        spriteBatch.end();

        hero.updateRectangle();
        sword.updateRectangle();
        table.updateRectangle();
        goblinOne.updateRectangle();

        stage.act(deltaTime);
        stage.draw();



        if (isDrawing) {
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
        stage.dispose();
        Player.weapons.clear();
    }

    @Override
    public void resize(int width, int height) {
        spriteBatch.getProjectionMatrix().setToOrtho2D(hero.xPos, hero.yPos, width, height);
        camera.viewportWidth = viewportWidth;
        camera.viewportHeight = viewportHeight;
        camera.update();
        stage.getViewport().update(width, height, true);
    }
}
