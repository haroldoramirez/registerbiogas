package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.locale.Pais;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by haroldo on 15/03/16.
 */
public class PaisController extends Controller {

    public Result buscaPorId(Long id) {
        Pais pais = Ebean.find(Pais.class, id);

        if (pais == null) {
            return notFound("País não encontrado.");
        }

        return ok(Json.toJson(pais));
    }

    public Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Pais.class)
                .order()
                .asc("nome")
                .findList()));
    }

    public Result filtra(String filtro) {

        Query<Pais> query = Ebean.createQuery(Pais.class, "find pais where (nome like :nome or name like :name)");
        query.setParameter("nome", "%" + filtro + "%");
        query.setParameter("name", "%" + filtro + "%");
        List<Pais> filtroDePaises = query.findList();

        return ok(Json.toJson(filtroDePaises));
    }
}
