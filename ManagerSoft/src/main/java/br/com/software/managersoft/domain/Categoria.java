package br.com.software.managersoft.domain;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 60)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoCategoriaEnum tipoCategoria;

    @OneToMany
    @JoinColumn(name = "categoria_id")
    private List<MovimentoFinanceiro> movimentoFinanceiroList;

    public Categoria() {
    }

    public Categoria(Long id, String descricao, TipoCategoriaEnum tipoCategoria) {
        this.id = id;
        this.descricao = descricao;
        this.tipoCategoria = tipoCategoria;
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

    public TipoCategoriaEnum getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoriaEnum tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public List<MovimentoFinanceiro> getMovimentoFinanceiroList() {
        return movimentoFinanceiroList;
    }

    public void setMovimentoFinanceiroList(List<MovimentoFinanceiro> movimentoFinanceiroList) {
        this.movimentoFinanceiroList = movimentoFinanceiroList;
    }
}
