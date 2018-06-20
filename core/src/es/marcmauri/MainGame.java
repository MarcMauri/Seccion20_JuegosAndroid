package es.marcmauri;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainGame extends ApplicationAdapter {

    private Texture player, pinchos;

    private TextureRegion regionPinchos;

    private SpriteBatch batch;

    private int width, height;

    private int widthPlayer, heightPlayer;

    @Override
    public void create() {
        player = new Texture("player.png");
        pinchos = new Texture("pinchos.png");
        batch = new SpriteBatch();

        regionPinchos = new TextureRegion(pinchos, 0, 64, 128, 64);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        widthPlayer = player.getWidth();
        heightPlayer = player.getHeight();
    }

    @Override
    public void dispose() {
        // Cuando se cierra el juego, se ejecuta
        // Importante liberar recursos en este paso
        player.dispose();
        pinchos.dispose();
        batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0.5f, 1);
        // Primero es importante limpiar el buffer de la grafica
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(player, 50, 0);
        batch.draw(regionPinchos, 250, 0);
        batch.end();
    }
}
