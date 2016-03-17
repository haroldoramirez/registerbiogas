package models.locale;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by haroldo on 14/03/16.
 */

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome" , "estado_id"})
})
public class Cidade extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Estado estado;

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
