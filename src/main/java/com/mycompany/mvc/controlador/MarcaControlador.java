package com.mycompany.mvc.controlador;

import com.mycompany.mvc.Dao.MarcaDao;
import com.mycompany.mvc.bd.Marca;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/marcas")
public class MarcaControlador {

    private MarcaDao marcaDao = new MarcaDao();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaDao.listarTodos());
        if (!model.containsAttribute("marca")) {
            model.addAttribute("marca", new Marca());
        }
        return "marcas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("marcas", marcaDao.listarTodos());
        model.addAttribute("marca", marcaDao.buscarPorId(id));
        return "marcas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Marca marca, RedirectAttributes ra) {
        try {
            boolean ok = marca.getIdMarca() == 0
                    ? marcaDao.insertar(marca)
                    : marcaDao.actualizar(marca);
            if (!ok) {
                ra.addFlashAttribute("error", "Error al momento de almacenar la marca");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/marcas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        marcaDao.eliminar(id);
        return "redirect:/marcas";
    }
}