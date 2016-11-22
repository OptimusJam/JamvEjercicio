/**
 * 
 */
package es.jamv.ejercicio.resource;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.jamv.ejercicio.logicanegocio.FactoriaLogicaNegocio;
import es.jamv.ejercicio.logicanegocio.InterfaceLogicaNegocio;
import es.jamv.ejercicio.modelo.Modelo;

/**
 * @author joseangel.martinez
 *
 */
@Path("/Nuevo/")
public class ResourceNuevo {

	@GET
	@Path("/getCiudad/{nombre}/{codigoPostal}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Modelo getCiudad(@NotNull @PathParam("nombre") final String nombre, 
    						@NotNull @Digits(integer=5, fraction=0) @PathParam("codigoPostal") int codigoPostal) {
		
		Modelo objModelo = new Modelo();
		
		//Se accede a la lógica de negocio
		InterfaceLogicaNegocio objLogicaNegocio = new FactoriaLogicaNegocio().obtenerLogicaNegocio();
		
		//Realización de algo.
		
		objModelo = objLogicaNegocio.realizaProceso(nombre, codigoPostal);
		
        return objModelo;
    }
	
}
