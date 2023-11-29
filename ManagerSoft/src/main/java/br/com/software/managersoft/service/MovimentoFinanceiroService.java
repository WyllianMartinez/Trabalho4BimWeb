package br.com.software.managersoft.service;

import br.com.software.managersoft.domain.MovimentoFinanceiro;
import br.com.software.managersoft.domain.SaldoDTO;
import br.com.software.managersoft.domain.TipoCategoriaEnum;
import br.com.software.managersoft.repository.MovimentoFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MovimentoFinanceiroService {

    @Autowired
    MovimentoFinanceiroRepository movimentoFinanceiroRepository;

    public void insert(MovimentoFinanceiro movimentoFinanceiro) {
        movimentoFinanceiroRepository.saveAndFlush(movimentoFinanceiro);
    }

    public void edit(MovimentoFinanceiro movimentoFinanceiro) {
        MovimentoFinanceiro movimentoFinanceiroCadastrado = findById(movimentoFinanceiro.getId());
        movimentoFinanceiro.setCategoria(movimentoFinanceiroCadastrado.getCategoria());
        movimentoFinanceiroRepository.saveAndFlush(movimentoFinanceiro);
    }

    public List<MovimentoFinanceiro> findAll(){
        return movimentoFinanceiroRepository.findAllByOrderByIdAsc();
    }

    public List<MovimentoFinanceiro> buscaUltimosDezRegistros(){
        return movimentoFinanceiroRepository.findTop10ByOrderByIdDesc();
    }


    public MovimentoFinanceiro findById(Long id){
        return movimentoFinanceiroRepository.findById(id).get();
    }

    public void delete(Long id) {
        movimentoFinanceiroRepository.deleteById(id);
    }

    public List<MovimentoFinanceiro> listByFilter(String descricao, String dataInicio, String dataFim, String tipoMovimento) {

        TipoCategoriaEnum tipoCategoriaEnum;

        if (Objects.equals(tipoMovimento, "RECEITA")) {
            tipoCategoriaEnum = TipoCategoriaEnum.R;
        } else if (Objects.equals(tipoMovimento, "DESPESA")) {
            tipoCategoriaEnum = TipoCategoriaEnum.D;
        } else {
            tipoCategoriaEnum = null;
        }

        if (descricao.isEmpty() && dataInicio.isEmpty() && dataFim.isEmpty() && tipoMovimento.isEmpty()) {
            return movimentoFinanceiroRepository.findAllByOrderByIdAsc();
        } else if(!dataInicio.isEmpty() && !dataFim.isEmpty() ) {


            Date dataInicioConvertida = null;
            Date dataFimConvertida = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                if (dataInicio != null && !Objects.equals(dataInicio, "")) {
                    dataInicioConvertida = new Date(sdf.parse(dataInicio).getTime());
                    System.out.println("Data Inicio " + dataInicioConvertida);
                }

                if (dataFim != null && !Objects.equals(dataFim, "")) {
                    dataFimConvertida = new Date(sdf.parse(dataFim).getTime());
                    System.out.println("Data Fim " + dataFimConvertida);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return movimentoFinanceiroRepository.findMovimentoFinanceiroByParamsDate(descricao, tipoCategoriaEnum, dataInicioConvertida, dataFimConvertida);
        } else {
            return movimentoFinanceiroRepository.findMovimentoFinanceiroByParams(descricao, tipoCategoriaEnum);
        }
    }

    public List<String> validar(MovimentoFinanceiro movimentoFinanceiro) {

        List<String> msg = new ArrayList<>();

        if (movimentoFinanceiro.getId() == null) {
            if (movimentoFinanceiro.getCategoria() == null) {
                msg.add("Informe o tipo da categoria");
            }
        }
        return msg;
    }

    public SaldoDTO getSaldos() {
        double entradas = 0.0;
        double saidas = 0.0;

        if(movimentoFinanceiroRepository.getEntradas() != null) {
            entradas = movimentoFinanceiroRepository.getEntradas();
        }

        if (movimentoFinanceiroRepository.getSaidas() != null){
            saidas = movimentoFinanceiroRepository.getSaidas();
        }

        double saldo = entradas - saidas;

        return new SaldoDTO(entradas, saidas, saldo);
    }
}
