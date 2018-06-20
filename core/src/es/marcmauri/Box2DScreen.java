package es.marcmauri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen {

    public Box2DScreen(MainGame game) {
        super(game);
    }

    private World world;

    private Box2DDebugRenderer renderer;

    private OrthographicCamera camera;

    private Body playerBody, sueloBody, pinchoBody;

    private Fixture playerFixture, sueloFixture, pinchoFixture;

    private boolean haColisionado;

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(7.11f, 4);
        camera.translate(0, 1);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();
                if (fixtureA == playerFixture && fixtureB == sueloFixture ||
                        fixtureA == sueloFixture && fixtureB == playerFixture) {
                    haColisionado = true;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        playerBody = world.createBody(createPlayerBodyDef());
        sueloBody = world.createBody(createSueloBodyDef());
        pinchoBody = world.createBody(createPinchoBodyDef(0.5f));

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(0.5f, 0.5f);
        playerFixture = playerBody.createFixture(playerShape, 3);
        playerShape.dispose();

        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500, 1);
        sueloFixture = sueloBody.createFixture(sueloShape, 1);
        sueloShape.dispose();

        //pinchoFixture = createPinchoFixture(pinchoBody);
    }

    private BodyDef createPinchoBodyDef(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x, 0.5f);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createSueloBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, -1);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private Fixture createPinchoFixture(Body pinchoBody) {
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);

        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        Fixture fix = pinchoBody.createFixture(shape, 1);
        shape.dispose();
        return fix;
    }

    private BodyDef createPlayerBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void dispose() {
        pinchoBody.destroyFixture(pinchoFixture);
        sueloBody.destroyFixture(sueloFixture);
        playerBody.destroyFixture(playerFixture);
        world.destroyBody(pinchoBody);
        world.destroyBody(sueloBody);
        world.destroyBody(playerBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.justTouched() || haColisionado) {
            haColisionado = false;
            saltar();
        }

        world.step(delta, 6, 2);

        camera.update();
        renderer.render(world, camera.combined);
    }

    private void saltar() {
        Vector2 position = playerBody.getPosition();
        playerBody.applyLinearImpulse(0, 20, position.x, position.y, true);
    }
}
