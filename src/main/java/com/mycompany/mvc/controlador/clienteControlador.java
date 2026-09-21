package com.mycompany.mvc.controlador;

import com.mycompany.mvc.Dao.ClienteDao;
import com.mycompany.mvc.bd.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class clienteControlador {

    private ClienteDao clienteDao = new ClienteDao();
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteDao.listarTodos());
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "clientes";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("clientes", clienteDao.listarTodos());
        model.addAttribute("cliente", clienteDao.buscarPorId(id));
        return "clientes";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente, RedirectAttributes ra) {
        try {
            boolean ok = cliente.getIdCliente() == 0
                    ? clienteDao.insertar(cliente)
                    : clienteDao.actualizar(cliente);
            if (!ok) {
                ra.addFlashAttribute("error", "Error al momento de almacenar");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clientes";
    }
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        clienteDao.eliminar(id);
        return "redirect:/clientes";
    }
}