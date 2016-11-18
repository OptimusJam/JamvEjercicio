/**
 * 
 */
package es.sherpa.ejercicio.logicanegocio;

/**
 * @author joseangel.martinez
 * Factoria para obtener los objetos que gestionan el proceso.
 */
public class FactoriaLogicaNegocio {
		
	/**
	 * Proporciona al nivel superior un objeto de l�gica de negocio.
	 * @return objeto de l�gica de negocio
	 */
	public InterfaceLogicaNegocio obtenerLogicaNegocio() {
		return new LogicaNegocio();
	}
	
}