package br.com.software.managersoft.controllers;

import br.com.software.managersoft.domain.Categoria;
import br.com.software.managersoft.domain.MovimentoFinanceiro;
import br.com.software.managersoft.domain.TipoCategoriaEnum;
import br.com.software.managersoft.service.CategoriaService;
import br.com.software.managersoft.service.MovimentoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/financeiro")
public class MovimentoController {

    @Autowired
    MovimentoFinanceiroService movimentoFinanceiroService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listarMovimentosFinaceiros(ModelMap model, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("financeiro/listar");

        if (model.containsAttribute("movimentoFinanceiros"))
            modelAndView.addObject("movimentoFinanceiros", model.getAttribute("movimentoFinanceiros"));
        else {
            modelAndView.addObject("movimentoFinanceiros", movimentoFinanceiroService.findAll());
        }

        return modelAndView;
    }

    @GetMapping(path = "/lancar")
    public ModelAndView retornaNovoMovimentoFinanceiro(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("financeiro/lancar");

        modelAndView.addObject("categorias", categoriaService.findAll());
        modelAndView.addObject("tipoCategoria", TipoCategoriaEnum.values());

        if (model.containsAttribute("movimentoFinanceiro")) {
            modelAndView.addObject("movimentoFinanceiro", model.getAttribute("movimentoFinanceiro"));
            modelAndView.addObject("msg", model.getAttribute("msg"));
        } else {
            modelAndView.addObject("movimentoFinanceiro", new MovimentoFinanceiro());
            modelAndView.addObject("msg", new ArrayList<String>());
        }
        return modelAndView;
    }

    @PostMapping
    public String salvarMovimento(@Valid MovimentoFinanceiro movimentoFinanceiro, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        List<String> msg = new ArrayList<>();

        msg.addAll(movimentoFinanceiroService.validar(movimentoFinanceiro));

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("movimentoFinanceiro", movimentoFinanceiro);
            for (ObjectError objectError : bindingResult.getAllErrors()) {

                if (objectError.getDefaultMessage().contains("Failed to convert property value of type 'java.lang.String'")){
                    msg.add("Informe a data de ocorrência");
                } else {
                    msg.add((objectError.getDefaultMessage()));
                }
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            if (movimentoFinanceiro.getId() != null) {
                return "redirect:/financeiro/editar/" + movimentoFinanceiro.getId();
            } else {
                return "redirect:/financeiro/lancar/";
            }
        }

        if (movimentoFinanceiro.getId() != null) {
            movimentoFinanceiroService.edit(movimentoFinanceiro);
        } else {
            movimentoFinanceiroService.insert(movimentoFinanceiro);
        }

        return "redirect:/financeiro";
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarMovimentoFinaceiro(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("financeiro/lancar");

        modelAndView.addObject("movimentoFinanceiro", movimentoFinanceiroService.findById(id));
        modelAndView.addObject("categorias", categoriaService.findAll());

        return modelAndView;
    }

    @GetMapping(path = "/deletar/{id}")
    public String deletarMovimentoFinanceiro(@PathVariable("id") Long id){
        movimentoFinanceiroService.delete(id);
        return "redirect:/financeiro";
    }

    @GetMapping(path = "/filtrar")
    public String filtrarMovimentos(@RequestParam("descricao") String descricao,
                                     @RequestParam("tipoMovimento") String tipoMovimento,
                                     @RequestParam("dataInicio") String dataInicio,
                                     @RequestParam("dataFim") String dataFim,
                                     RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("movimentoFinanceiros",
                movimentoFinanceiroService.listByFilter(descricao, dataInicio, dataFim, tipoMovimento));

        return "redirect:/financeiro";
    }

}
