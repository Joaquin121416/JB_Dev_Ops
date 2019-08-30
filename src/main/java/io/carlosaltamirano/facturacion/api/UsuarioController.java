package io.carlosaltamirano.facturacion.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import io.carlosaltamirano.facturacion.core.exception.FacturacionWebException;
import io.carlosaltamirano.facturacion.core.model.Usuario;
import io.carlosaltamirano.facturacion.core.service.UsuarioService;

@Controller
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/usuarios")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		List<Usuario> usuarios = usuarioService.listarTodos();
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@PostMapping("/usuarios/crear")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario crear(@RequestBody Usuario usuario) {
		logger.debug("Crear usuario request: {}", usuario);
		try {
			return usuarioService.crear(usuario);
			
		} catch (FacturacionWebException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
