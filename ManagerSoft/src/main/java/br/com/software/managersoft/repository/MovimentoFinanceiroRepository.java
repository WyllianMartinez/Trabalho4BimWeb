package br.com.software.managersoft.repository;

import br.com.software.managersoft.domain.MovimentoFinanceiro;
import br.com.software.managersoft.domain.TipoCategoriaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Long> {
    @Query
    public List<MovimentoFinanceiro> findAllByOrderByIdAsc();

    @Query
    public List<MovimentoFinanceiro> findTop10ByOrderByIdDesc();

    @Query("SELECT mf FROM MovimentoFinanceiro mf WHERE " +
            "(:descricao IS NULL OR LOWER(mf.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))) " +
            "AND (:tipoMovimento IS NULL OR mf.categoria.tipoCategoria = :tipoMovimento) " +
            "AND mf.dataOcorrencia BETWEEN :dataInicio AND :dataFim order by mf.id ASC")
    public List<MovimentoFinanceiro> findMovimentoFinanceiroByParamsDate(
            @Param("descricao") String descricao,
            @Param("tipoMovimento") TipoCategoriaEnum tipoMovimento,
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim
    );

    @Query("SELECT mf FROM MovimentoFinanceiro mf WHERE " +
            "(:descricao IS NULL OR LOWER(mf.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))) " +
            "AND (:tipoMovimento IS NULL OR mf.categoria.tipoCategoria = :tipoMovimento) order by mf.id ASC")
    public List<MovimentoFinanceiro> findMovimentoFinanceiroByParams(
            @Param("descricao") String descricao,
            @Param("tipoMovimento") TipoCategoriaEnum tipoMovimento
    );



}
