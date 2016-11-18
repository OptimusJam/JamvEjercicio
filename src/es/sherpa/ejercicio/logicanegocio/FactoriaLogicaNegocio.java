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
	 * Proporciona al nivel superior un objeto de lógica de negocio.
	 * @return objeto de lógica de negocio
	 */
	public InterfaceLogicaNegocio obtenerLogicaNegocio() {
		return new LogicaNegocio();
	}
	
}