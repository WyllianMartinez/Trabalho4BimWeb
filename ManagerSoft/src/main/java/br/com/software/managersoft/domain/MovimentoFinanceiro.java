package br.com.software.managersoft.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "MOVIMENTOFINANCEIRO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoFinanceiro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 60)
    private String descricao;

    private LocalDateTime dataOcorrencia;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Double valor;


}
