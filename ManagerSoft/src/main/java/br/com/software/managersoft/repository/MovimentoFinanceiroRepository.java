package br.com.software.managersoft.repository;

import br.com.software.managersoft.domain.MovimentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Long> {
    @Query
    public List<MovimentoFinanceiro> findAllByOrderByIdAsc();
    @Query
    public List<MovimentoFinanceiro> findAllByDescricaoContainingIgnoreCaseOrderByIdAsc(String descricao);
}
