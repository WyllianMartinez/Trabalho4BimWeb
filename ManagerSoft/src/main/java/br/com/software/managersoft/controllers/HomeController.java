package br.com.software.managersoft.controllers;

import br.com.software.managersoft.service.MovimentoFinanceiroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    MovimentoFinanceiroService movimentoFinanceiroService;

    @GetMapping
    public ModelAndView listarMovimentosFinaceirosHome(ModelMap model, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("index");

        if (model.containsAttribute("movimentoFinanceiros"))
            modelAndView.addObject("movimentoFinanceiros", model.getAttribute("movimentoFinanceiros"));
        else {
            modelAndView.addObject("movimentoFinanceiros", movimentoFinanceiroService.buscaUltimosDezRegistros());
        }

        return modelAndView;
    }

}
