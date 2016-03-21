package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.locale.Estado;
import models.locale.Pais;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Haroldo on 17/03/2016.
 */
public class EstadoController extends Controller {

    public Result inserir() {

        Logger.info("Inserir Controller.");

        Estado estado = Json.fromJson(request().body().asJson(), Estado.class);

        Pais pais = Ebean.find(Pais.class, estado.getPais().getId());

        estado.setPais(pais);

        try {
            Ebean.save(estado);
        } catch (PersistenceException e) {
            return badRequest("O estado '" + estado.getNome() + "' já esta cadastrado. Selecione outro Estado.");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return badRequest("Erro interno de sistema.");
        }

        return created(Json.toJson(estado));
    }

    public Result atualizar(Long id) {

        Estado estado = Json.fromJson(request().body().asJson(), Estado.class);

        Estado estadoBusca = Ebean.find(Estado.class, id);

        if (estadoBusca == null) {
            return notFound("Estado não encontrado.");
        }

        Pais pais = Ebean.find(Pais.class, estado.getPais().getId());

        estado.setPais(pais);

        try {
            Ebean.update(estado);
            Logger.info("Estado atualizado.");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return badRequest("Erro interno de sistema.");
        }

        return ok(Json.toJson(estado));
    }

    public Result buscaPorId(Long id) {

        Estado estado = Ebean.find(Estado.class, id);

        if (estado == null) {
            Logger.warn("Estado não encontrado.");
            return notFound("Estado não encontrado.");
        }

        return ok(Json.toJson(estado));
    }

    public Result buscaPorIdPais(Long id) {
        Logger.info("Filtra Estado pelo ID Pais.");

        Query<Estado> query = Ebean.createQuery(Estado.class, "find estado where (pais.id = :pais_id)");
        query.setParameter("pais_id", id);
        List<Estado> listaEstados = query.findList();
        return ok(Json.toJson(listaEstados));
    }

    public Result buscaTodos() {
        Logger.info("Busca todos os Estados.");

        return ok(Json.toJson(Ebean.find(Estado.class)
                .order()
                .asc("nome")
                .findList()));
    }

    public Result remover(Long id) {

        Estado estado = Ebean.find(Estado.class, id);

        if (estado == null) {
            Logger.warn("Estado não encontrado.");
            return notFound("Estado não encontrado.");
        }

        try {
            Ebean.delete(estado);
            Logger.info("Estado removido.");
        } catch (PersistenceException e) {
            Logger.error(e.getMessage());
            return badRequest("Existem Cidades que dependem deste Estado.");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return badRequest("Erro interno de sistema.");
        }

        return ok(Json.toJson(estado));
    }

    public Result filtra(String filtro) {
        Logger.info("Filtra Estado.");

        Query<Estado> query = Ebean.createQuery(Estado.class, "find estado where (nome like :nome or pais.nome like :paisNome)");
        query.setParameter("nome", "%" + filtro + "%");
        query.setParameter("paisNome", "%" + filtro + "%");
        List<Estado> filtroDeEstados = query.findList();

        return ok(Json.toJson(filtroDeEstados));
    }
}
