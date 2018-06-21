package es.marcmauri.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPinchos extends Actor {

    private TextureRegion pinchos;

    public ActorPinchos(TextureRegion pinchos) {
        this.pinchos = pinchos;
        setSize(pinchos.getRegionWidth(), pinchos.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        // <-- -250px/seg
        setX(getX() - 250*delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pinchos, getX(), getY());
    }
}
