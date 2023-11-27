package br.com.software.managersoft.repository;

import br.com.software.managersoft.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query
    public List<Categoria> findAllByOrderByIdAsc();
    @Query
    public List<Categoria> findAllByDescricaoContainingIgnoreCaseOrderByIdAsc(String descricao);

}
