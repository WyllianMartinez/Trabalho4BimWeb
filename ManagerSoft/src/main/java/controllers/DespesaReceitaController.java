package controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/categorias")
public class DespesaReceitaController {

    private static final Logger logger = LoggerFactory.getLogger(DespesaReceitaController.class);

    @GetMapping
    public String categorias(Model model) {
        logger.info("HomeController - Home method called");
        model.addAttribute("user", "Wyllian");
        return "registros";
    }

}
