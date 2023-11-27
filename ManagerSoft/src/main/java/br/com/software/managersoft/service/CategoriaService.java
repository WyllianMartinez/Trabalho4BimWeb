package br.com.software.managersoft.service;

import br.com.software.managersoft.domain.Categoria;
import br.com.software.managersoft.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public void insert(Categoria categoria) {
        categoriaRepository.saveAndFlush(categoria);
    }

    public void edit(Categoria categoria){
        categoriaRepository.saveAndFlush(categoria);
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAllByOrderByIdAsc();
    }

    public Categoria findById(Long id){
        return categoriaRepository.findById(id).get();
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> listByFilter(String descricao) {
        if (descricao.isEmpty()) {
            return categoriaRepository.findAll();
        } else {
            return categoriaRepository.findAllByDescricaoContainingIgnoreCaseOrderByIdAsc(descricao);
        }
    }

}
