package es.sherpa.ejercicio.resource;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.sherpa.ejercicio.logicanegocio.FactoriaLogicaNegocio;
import es.sherpa.ejercicio.logicanegocio.InterfaceLogicaNegocio;
import es.sherpa.ejercicio.modelo.Modelo;

/**
 * @author joseangel.martinez
 *
 */

@Path("/")
public final class Resource {

	private final String MENSAJE_ERROR_VALIDACION_CP = "Solo se admiten 5 digitos en el segundo parámetro";
	
	@GET
	@Path("/getCiudad/{nombre}/{codigoPostal}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Modelo getCiudad(@NotNull @PathParam("nombre") final String nombre, 
    						@NotNull @Digits(integer=5, fraction=0, message=MENSAJE_ERROR_VALIDACION_CP) @PathParam("codigoPostal") int codigoPostal) {
		
		Modelo objModelo = new Modelo();
		
		//Se accede a la lógica de negocio
		InterfaceLogicaNegocio objLogicaNegocio = new FactoriaLogicaNegocio().obtenerLogicaNegocio();
		
		objModelo = objLogicaNegocio.realizaProceso(nombre, codigoPostal);
		
        return objModelo;
    }    
}