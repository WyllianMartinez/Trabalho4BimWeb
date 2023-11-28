package br.com.software.managersoft.controllers;

import br.com.software.managersoft.domain.MovimentoFinanceiro;
import br.com.software.managersoft.service.MovimentoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/financeiro")
public class MovimentoController {

    @Autowired
    MovimentoFinanceiroService movimentoFinanceiroService;

    @GetMapping
    public ModelAndView listarMovimentosFinaceiros(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("financeiro/listar");

        if (model.containsAttribute("movimentoFinanceiros"))
            modelAndView.addObject("movimentoFinanceiros", model.getAttribute("movimentoFinanceiros"));
        else {
            modelAndView.addObject("movimentoFinanceiros", movimentoFinanceiroService.findAll());
        }

        return modelAndView;
    }






}
