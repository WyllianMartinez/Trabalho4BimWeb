package br.com.software.managersoft.controllers;
import br.com.software.managersoft.domain.Categoria;
import br.com.software.managersoft.domain.TipoCategoriaEnum;
import br.com.software.managersoft.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listaCategorias(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("categoria/listar");

        if (model.containsAttribute("categorias"))
            modelAndView.addObject("categorias", model.getAttribute("categorias"));
        else {
            modelAndView.addObject("categorias", categoriaService.findAll());
        }

        return modelAndView;
    }


    @GetMapping(path = "/filtrar")
    public String filtrarProfessores(@RequestParam("descricao") String descricao, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("categorias",
                categoriaService.listByFilter(descricao));
        return "redirect:/categoria";
    }


    @GetMapping(path = "/cadastrar")
    public ModelAndView retornaNovoProfessor(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("categoria/cadastrar");

        if (model.containsAttribute("categoria")) {
            modelAndView.addObject("categoria", model.getAttribute("categoria"));
            modelAndView.addObject("msg", model.getAttribute("msg"));
        } else {
            modelAndView.addObject("categoria", new Categoria());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        modelAndView.addObject("tiposCategoria", TipoCategoriaEnum.values());

        return modelAndView;
    }


}
