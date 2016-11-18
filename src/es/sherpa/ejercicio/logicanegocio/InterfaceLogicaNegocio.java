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
	 * M�todo para la gesti�n del proceso de la aplicaci�n.
	 * @param nombre nombre del usuario
	 * @param codigoPostal codigo postal
	 * @return modelo con el resultado de la operaci�n y mensaje informativo.
	 */
	public Modelo realizaProceso(final String nombre, final int codigoPostal);
	
}
