package br.com.software.managersoft.controllers;

import br.com.software.managersoft.domain.Categoria;
import br.com.software.managersoft.domain.TipoCategoriaEnum;
import br.com.software.managersoft.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listaCategorias(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("categoria/listar");

        if (model.containsAttribute("categorias"))
            modelAndView.addObject("categorias", model.getAttribute("categorias"));
        else {
            modelAndView.addObject("categorias", categoriaService.findAll());
        }

        return modelAndView;
    }


    @GetMapping(path = "/filtrar")
    public String filtrarProfessores(@RequestParam("descricao") String descricao, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("categorias",
                categoriaService.listByFilter(descricao));
        return "redirect:/categoria";
    }


    @GetMapping(path = "/cadastrar")
    public ModelAndView retornaNovaCategoria(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("categoria/cadastrar");

        modelAndView.addObject("tiposCategoria", TipoCategoriaEnum.values());

        if (model.containsAttribute("categoria")) {
            modelAndView.addObject("categoria", model.getAttribute("categoria"));
            modelAndView.addObject("msg", model.getAttribute("msg"));
        } else {
            modelAndView.addObject("categoria", new Categoria());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @PostMapping
    public String salvarCategoria(@Valid Categoria categoria, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        List<String> msg = new ArrayList<>();

        msg.addAll(categoriaService.validar(categoria));

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("categoria", categoria);
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            if (categoria.getId() != null) {
                return "redirect:/categoria/editar/" + categoria.getId();
            } else {
                return "redirect:/categoria/cadastrar/";
            }
        }

        if (categoria.getId() != null) {
            categoriaService.edit(categoria);
        } else {
            categoriaService.insert(categoria);
        }

        return "redirect:/categoria";
    }

    @GetMapping(path = "/deletar/{id}")
    public String deletarCategoria(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        List<String> msg = new ArrayList<>(categoriaService.validaRemocao(id));

        if (!msg.isEmpty()){

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/categoria";
        } else {
            categoriaService.delete(id);
            return "redirect:/categoria";
        }

    }


    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarCategoria(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("categoria/cadastrar");

        modelAndView.addObject("categoria", categoriaService.findById(id));
        modelAndView.addObject("tiposCategoria", TipoCategoriaEnum.values());

        return modelAndView;
    }


}
