import controllers.PaisController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;
import static play.test.Helpers.fakeApplication;

/**
 * Created by Haroldo on 18/03/2016.
 */
public class RotasTest {

    Application app = fakeApplication();

    @Before
    public void startApp() {

        final PaisController paisController = new PaisController();

        start(app);

        start(fakeApplication(inMemoryDatabase()));
    }

    @After
    public void stopApp() {
        stop(fakeApplication());
    }

    @Test
    public void testBadRoute() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/xx/Kiwi");

        Result result = route(request);
        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void testPaisesRoute() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/paises");

        Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testEstadosRoute() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/estados");

        Result result = route(request);
        assertEquals(NOT_FOUND, result.status());
    }
}
