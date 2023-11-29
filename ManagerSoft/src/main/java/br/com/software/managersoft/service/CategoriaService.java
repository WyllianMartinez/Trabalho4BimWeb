package br.com.software.managersoft.service;

import br.com.software.managersoft.domain.Categoria;
import br.com.software.managersoft.domain.TipoCategoriaEnum;
import br.com.software.managersoft.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public void insert(Categoria categoria) {
        categoriaRepository.saveAndFlush(categoria);
    }

    public void edit(Categoria categoria) {
        Categoria categoriaFind = findById(categoria.getId());
        categoria.setTipoCategoria(categoriaFind.getTipoCategoria());
        categoria.setMovimentoFinanceiroList(categoriaFind.getMovimentoFinanceiroList());
        categoriaRepository.saveAndFlush(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAllByOrderByIdAsc();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).get();
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> listByFilter(String descricao) {
        if (descricao.isEmpty()) {
            return categoriaRepository.findAllByOrderByIdAsc();
        } else {
            return categoriaRepository.findAllByDescricaoContainingIgnoreCaseOrderByIdAsc(descricao);
        }
    }

    public List<String> validar(Categoria categoria) {

        List<String> msg = new ArrayList<>();

        if (categoria.getId() == null) {
            if (categoria.getTipoCategoria() != TipoCategoriaEnum.D && categoria.getTipoCategoria() != TipoCategoriaEnum.R) {
                msg.add("Informe o tipo da categoria");
            }
        }
        return msg;
    }

    public List<String> validaRemocao(Long id) {

        List<String> msg = new ArrayList<>();

        Categoria categoria = findById(id);

        if (categoria != null) {
            if (!categoria.getMovimentoFinanceiroList().isEmpty()) {
                msg.add("Não é possível remover a categoria pois a mesma está sendo utilizada em movimentos financeiros.");
            }
        } else {
            msg.add("Categoria não encontrada");
        }

        return msg;

    }
}
