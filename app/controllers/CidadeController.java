package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.locale.Cidade;
import models.locale.Estado;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Haroldo on 21/03/2016.
 */
public class CidadeController extends Controller {

    public Result inserir() {

        Cidade cidade = Json.fromJson(request().body().asJson(), Cidade.class);

        Estado estado = Ebean.find(Estado.class, cidade.getEstado().getId());

        cidade.setEstado(estado);

        try {
            Ebean.save(cidade);
        } catch (PersistenceException e) {
            return badRequest("A cidade '" + cidade.getNome() + "' já esta cadastrada. Selecione outro Estado.");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema.");
        }

        return created(Json.toJson(cidade));
    }

    public Result atualizar(Long id) {

        Cidade cidade = Json.fromJson(request().body().asJson(), Cidade.class);

        Cidade cidadeBusca = Ebean.find(Cidade.class, id);

        if (cidadeBusca == null) {
            Logger.warn("Cidade não encontrada.");
            return notFound("Cidade não encontrada.");
        }

        Estado estado = Ebean.find(Estado.class, cidade.getEstado().getId());

        cidade.setEstado(estado);

        try {
            Ebean.update(cidade);
            Logger.info("Cidade atualizada.");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return badRequest("Erro interno de sistema.");
        }

        return ok(Json.toJson(cidade));
    }

    public Result buscaPorId(Long id) {

        Cidade cidade = Ebean.find(Cidade.class, id);

        if (cidade == null) {
            Logger.warn("Cidade não encontrada.");
            return notFound("Cidade não encontrada.");
        }

        return ok(Json.toJson(cidade));
    }

    public Result buscaPorIdEstado(Long id) {
        Logger.info("Filtra Cidade pelo ID Estado.");

        Query<Cidade> query = Ebean.createQuery(Cidade.class, "find cidade where (estado.id = :estado_id)");
        query.setParameter("estado_id", id);
        List<Cidade> listaCidades = query.findList();
        return ok(Json.toJson(listaCidades));
    }

    public Result buscaTodos() {
        Logger.info("Busca todas as Cidades.");

        return ok(Json.toJson(Ebean.find(Cidade.class).findList()));
    }

    public Result remover(Long id) {

        Cidade cidade = Ebean.find(Cidade.class, id);

        if (cidade == null) {
            Logger.warn("Cidade não encontrada.");
            return notFound("Cidade não encontrada.");
        }

        try {
            Ebean.delete(cidade);
            Logger.info("Cidade removida.");
        } catch (PersistenceException e) {
            Logger.error(e.getMessage());
            return badRequest("Existem Pessoas que dependem desta Cidade.");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return badRequest("Erro interno de sistema.");
        }

        return ok(Json.toJson(cidade));
    }

    public Result filtra(String filtro) {
        Logger.info("Filtra Cidade");

        Query<Cidade> query = Ebean.createQuery(Cidade.class, "find cidade where (nome like :nome or estado.nome like :estadoNome)");
        query.setParameter("nome", "%" + filtro + "%");
        query.setParameter("estadoNome", "%" + filtro + "%");
        List<Cidade> filtroDeCidades = query.findList();
        return ok(Json.toJson(filtroDeCidades));
    }
}
