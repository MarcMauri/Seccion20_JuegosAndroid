package es.marcmauri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import es.marcmauri.actors.ActorJugador;
import es.marcmauri.actors.ActorPinchos;

public class MainGameScreen extends BaseScreen {

    private Stage stage;

    private ActorJugador jugador;
    private ActorPinchos pinchos;

    private Texture texturaJugador, texturaPinchos;

    private TextureRegion regionPinchos;

    public MainGameScreen(MainGame game) {
        super(game);
        texturaJugador = new Texture("player.png");
        texturaPinchos = new Texture("pinchos.png");
        regionPinchos = new TextureRegion(texturaPinchos, 0, 64, 128, 64);
    }

    @Override
    public void show() {
        stage = new Stage();
        jugador = new ActorJugador(texturaJugador);
        pinchos = new ActorPinchos(regionPinchos);
        stage.addActor(jugador);
        stage.addActor(pinchos);

        jugador.setPosition(20, 100);
        pinchos.setPosition(500,100);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        texturaJugador.dispose();
    }
}
