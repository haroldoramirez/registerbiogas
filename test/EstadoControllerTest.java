import com.fasterxml.jackson.databind.JsonNode;
import models.locale.Estado;
import models.locale.Pais;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.CREATED;
import static play.test.Helpers.*;

/**
 * Created by Haroldo on 18/03/2016.
 */
public class EstadoControllerTest extends WithApplication {

    @Test
    public void inserirTest() {

        Pais pais = new Pais();
        pais.setId(Long.parseLong("1"));
        pais.setNome("Brasil");
        pais.setSigla("BR");

        Estado estado = new Estado();
        estado.setId(Long.parseLong("1"));
        estado.setSigla("PR");
        estado.setNome("Paraná");
        estado.setPais(pais);

        JsonNode jsonEstado = Json.toJson(estado);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/estado")
                .bodyJson(jsonEstado);

        Result result = route(request);

        assertEquals(CREATED, result.status());
    }

    @Test
    public void estadoBuscaTodosTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/estados");

        Result result = route(request);

        assertEquals(OK, result.status());
    }

    @Test
    public void estadoBustaPorIdTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/estado/1");

        Result result = route(request);

        assertEquals(NOT_FOUND, result.status());
    }
}
