package com.engine.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyTable {

    public Stage stage;
    public Table stageTable;
    public Skin debugButtonSkin;
    public Skin invButtonSkin;
    public Skin labelSkin;
    public boolean isDrawing = false;
    public boolean isInvOpen = false;
    Weapon sword;
    Player player;
    public Label infoLabel;

    public MyTable(TextureAtlas atlas) {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        debugButtonSkin = new Skin(Gdx.files.internal("ui/buttonStyle.json"));
        invButtonSkin = new Skin(Gdx.files.internal("ui/invButtonStyle.json"));
        labelSkin = new Skin(Gdx.files.internal("Fonts/labelStyle.json"));
        stageTable = new Table();
        stageTable.top().left();
        stageTable.setFillParent(true);
        stage.addActor(stageTable);
        stageTable.setDebug(false);
        Button debugButton = new Button(debugButtonSkin);
        Button invButton = new Button(invButtonSkin);
        Label invLabel = new Label("", labelSkin);
        infoLabel = new Label("", labelSkin);
        invLabel.setAlignment(Align.center);
        invLabel.setVisible(false);
        infoLabel.setAlignment(Align.center);
        infoLabel.setVisible(true);
        stageTable.add(debugButton).width(100).height(50);
        stageTable.add(invButton).width(75).height(50);
        stageTable.add(invLabel).width(225).height(75);
        stageTable.add(infoLabel).width(200).height(100);
        sword = new Weapon(atlas);
        player = new Player(atlas, sword, this);
        player.setMyTable(this);

        debugButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                isDrawing = !isDrawing;
            }
        });
        invButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                isInvOpen = !isInvOpen;
                invLabel.setVisible(isInvOpen);
                if (isInvOpen) {
                    if (Player.weapons.isEmpty()) { // come back and check if weapons has sword.
                        invLabel.setText("");
                    } else {
                        invLabel.setText(sword.toString());
                    }
                }
            }
        });
    }
}
