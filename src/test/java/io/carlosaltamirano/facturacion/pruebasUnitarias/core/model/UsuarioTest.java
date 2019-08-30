package io.carlosaltamirano.facturacion.pruebasUnitarias.core.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import io.carlosaltamirano.facturacion.core.model.Usuario;
import io.carlosaltamirano.facturacion.test.categoria.PruebaUnitaria;

@Category(PruebaUnitaria.class)
public class UsuarioTest {

	private static Validator validator;
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void creacionUsuarioExitoso() {
		Usuario usuario = new Usuario();
		usuario.setNombres("Carlos");
		usuario.setApellidos("Altamirano");
		usuario.setEmail("caltamiranob.1987@gmail.com");
		
		Set<ConstraintViolation<Usuario>> 
		constraintViolations = validator.validate(usuario);
		
		assertThat(constraintViolations.size(), is(0));
	}
	
	@Test
	public void validacionCreacionUsuarioCuandoEmailEsVacio() {
		Usuario usuario = new Usuario();
		usuario.setNombres("Carlos");
		usuario.setApellidos("Altamirano");
		usuario.setEmail("");
		
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size(), is(1));
	}
	
}
