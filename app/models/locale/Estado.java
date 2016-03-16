package models.locale;

import com.avaje.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by haroldo on 14/03/16.
 */

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome" , "pais_id"})
})
public class Estado extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 3)
    private String sigla;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Pais pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return Json.toJson(this).toString();
    }
}
