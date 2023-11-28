package br.com.software.managersoft.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "MOVIMENTOFINANCEIRO")
public class MovimentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 60, message = "A descrição deve ter entre 5 e 60 caracteres")
    private String descricao;

    @NotNull
    private Date dataOcorrencia;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull(message = "Informe o valor do movimento")
    private Double valor;

    public MovimentoFinanceiro() {
    }

    public MovimentoFinanceiro(Long id, String descricao, Date dataOcorrencia, Categoria categoria, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.dataOcorrencia = dataOcorrencia;
        this.categoria = categoria;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
