package es.marcmauri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import es.marcmauri.actors.ActorJugador;

public class MainGameScreen extends BaseScreen {

    private Stage stage;

    private ActorJugador jugador;

    private Texture texturaJugador;

    public MainGameScreen(MainGame game) {
        super(game);
        texturaJugador = new Texture("player.png");
    }

    @Override
    public void show() {
        stage = new Stage();
        jugador = new ActorJugador(texturaJugador);
        stage.addActor(jugador);

        jugador.setPosition(20, 100);
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
