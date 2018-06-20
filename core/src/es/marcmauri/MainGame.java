package es.marcmauri;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainGame extends ApplicationAdapter {

    @Override
    public void create() {
        //
    }

    @Override
    public void dispose() {
        // Cuando se cierra el juego, se ejecuta
        // Importante liberar recursos en este paso
    }

    @Override
    public void render() {
        // Primero es importante limpiar el buffer de la grafica
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0.5f, 1);

        if (Gdx.input.justTouched()) {
            System.out.println("Estas tocando la pantalla.");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            System.out.println("Woooah, back button");
        }
    }
}
