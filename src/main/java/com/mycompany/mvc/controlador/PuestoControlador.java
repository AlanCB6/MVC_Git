package com.mycompany.mvc.controlador;

import com.mycompany.mvc.Dao.PuestoDao;
import com.mycompany.mvc.bd.Puesto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/puestos")
public class PuestoControlador {

    private PuestoDao puestoDao = new PuestoDao();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("puestos", puestoDao.listarTodos());
        if (!model.containsAttribute("puesto")) {
            model.addAttribute("puesto", new Puesto());
        }
        return "puestos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("puestos", puestoDao.listarTodos());
        model.addAttribute("puesto", puestoDao.buscarPorId(id));
        return "puestos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Puesto puesto, RedirectAttributes ra) {
        try {
            boolean ok = puesto.getIdPuesto() == 0
                    ? puestoDao.insertar(puesto)
                    : puestoDao.actualizar(puesto);
            if (!ok) {
                ra.addFlashAttribute("error", "Error al momento de almacenar");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/puestos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        puestoDao.eliminar(id);
        return "redirect:/puestos";
    }
}