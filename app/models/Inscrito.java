package models;

import com.avaje.ebean.Model;
import models.locale.Cidade;
import models.locale.Estado;
import models.locale.Pais;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by haroldo on 14/03/16.
 */

@Entity
public class Inscrito extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false)
    private char sexo;

    @Column(nullable = false)
    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date dataNascimento;

    @Column(nullable = false, length = 30)
    private String escolaridade;

    @Column(nullable = false, length = 30)
    private String profissao;

    @Column(nullable = false, length = 45)
    private String instituicao;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Cidade cidade;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Estado estado;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Pais pais;

    @Column(nullable = false, length = 12)
    private String telefone;

    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false, length = 35)
    private String email;

    @Column(nullable = false)
    private boolean ead;

    @Column(nullable = false, length = 25)
    private String fonte;

    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date dataCadastro;

    @Formats.DateTime(pattern="dd-MM-yyyy")
    private Date dataAlteracao;

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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEad() {
        return ead;
    }

    public void setEad(boolean ead) {
        this.ead = ead;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
