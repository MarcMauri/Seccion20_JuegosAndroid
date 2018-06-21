package es.marcmauri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

import es.marcmauri.entities.FloorEntity;
import es.marcmauri.entities.PlayerEntity;
import es.marcmauri.entities.SpikeEntity;

public class GameScreen extends BaseScreen {

    private Stage stage;
    private World world;
    private PlayerEntity player;

    private List<FloorEntity> floorList = new ArrayList<FloorEntity>();
    private List<SpikeEntity> spikeList = new ArrayList<SpikeEntity>();


    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, -10), true);
    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("player.png");
        Texture floorTexture = game.getManager().get("floor.png");
        Texture overfloorTexture = game.getManager().get("overfloor.png");
        Texture spikeTexture = game.getManager().get("spike.png");
        player = new PlayerEntity(world, playerTexture, new Vector2(1.5f, 1.5f));

        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, 0, 1000, 1));
        spikeList.add(new SpikeEntity(world, spikeTexture, 6, 1));

        stage.addActor(player);
        for (FloorEntity floor : floorList) {
            stage.addActor(floor);
        }
        for (SpikeEntity spike : spikeList) {
            stage.addActor(spike);
        }
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
        for (FloorEntity floor : floorList) {
            floor.detach();
            floor.remove();
        }
        for (SpikeEntity spike : spikeList) {
            spike.detach();
            spike.remove();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}