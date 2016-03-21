import controllers.PaisController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;

/**
 * Created by Haroldo on 18/03/2016.
 */
public class PaisControllerTest {

    Application app = fakeApplication();

    @Before
    public void startApp() {

        start(app);

        start(fakeApplication(inMemoryDatabase()));
    }

    @After
    public void stopApp() {
        stop(fakeApplication());
    }

    @Test
    public void paisBuscaTodosTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/paises");

        Result result = route(request);

        assertEquals(OK, result.status());
    }

    @Test
    public void paisBustaPorIdTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/pais/1");

        Result result = route(request);

        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void paisFiltroTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/pais/filtro/Brasil");

        Result result = route(request);

        assertEquals(OK, result.status());
    }

}
