/**
 * 
 */
package es.sherpa.ejercicio.logicanegocio;

import es.sherpa.ejercicio.modelo.Modelo;

/**
 * @author joseangel.martinez
 *
 */
public interface InterfaceLogicaNegocio {

	/**
	 * Método para la gestión del proceso de la aplicación.
	 * @param nombre nombre del usuario
	 * @param codigoPostal codigo postal
	 * @return modelo con el resultado de la operación y mensaje informativo.
	 */
	public Modelo realizaProceso(final String nombre, final int codigoPostal);
	
}
