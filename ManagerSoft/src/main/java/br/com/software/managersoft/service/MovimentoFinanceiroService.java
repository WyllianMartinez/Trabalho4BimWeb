package br.com.software.managersoft.service;

import br.com.software.managersoft.domain.MovimentoFinanceiro;
import br.com.software.managersoft.repository.MovimentoFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentoFinanceiroService {

    @Autowired
    MovimentoFinanceiroRepository movimentoFinanceiroRepository;

    public void insert(MovimentoFinanceiro movimentoFinanceiro) {
        movimentoFinanceiroRepository.saveAndFlush(movimentoFinanceiro);
    }

    public void edit(MovimentoFinanceiro movimentoFinanceiro) {
        movimentoFinanceiroRepository.saveAndFlush(movimentoFinanceiro);
    }

    public List<MovimentoFinanceiro> findAll(){
        return movimentoFinanceiroRepository.findAllByOrderByIdAsc();
    }

    public MovimentoFinanceiro findById(Long id){
        return movimentoFinanceiroRepository.findById(id).get();
    }

    public void delete(Long id) {
        movimentoFinanceiroRepository.deleteById(id);
    }

    public List<MovimentoFinanceiro> listByFilter(String descricao) {
        if (descricao.isEmpty()) {
            return movimentoFinanceiroRepository.findAllByOrderByIdAsc();
        } else {
            return movimentoFinanceiroRepository.findAllByDescricaoContainingIgnoreCaseOrderByIdAsc(descricao);
        }
    }

    public List<String> validate(MovimentoFinanceiro movimentoFinanceiro) {

        List<String> msg = new ArrayList<>();

        return msg;
    }

}
