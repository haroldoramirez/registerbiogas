package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.Inscricao;
import models.locale.Pais;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by haroldo on 15/03/16.
 */
public class InscricaoController extends Controller {

    public Result inserir() {
        return TODO;
    }

    public Result atualizar(Long id) {
        return TODO;
    }

    public Result buscaPorId(Long id) {
        Inscricao inscricao = Ebean.find(Inscricao.class, id);

        if (inscricao == null) {
            return notFound("Inscrição não encontrada.");
        }

        return ok(Json.toJson(inscricao));
    }

    public Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Inscricao.class)
                .order()
                .asc("nome")
                .findList()));
    }

    public Result filtra(String filtro) {
        Query<Inscricao> query = Ebean.createQuery(Inscricao.class, "find inscricao where (nome like :nome)");
        query.setParameter("nome", "%" + filtro + "%");
        List<Inscricao> filtroDeInscricoes = query.findList();

        return ok(Json.toJson(filtroDeInscricoes));
    }
}
