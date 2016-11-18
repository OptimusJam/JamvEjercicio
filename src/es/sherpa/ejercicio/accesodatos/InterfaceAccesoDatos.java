/**
 * 
 */
package es.sherpa.ejercicio.accesodatos;

/**
 * @author joseangel.martinez
 *
 */
public interface InterfaceAccesoDatos {

	/**
	 * Metodo para la inserción de los datos en la BBDD.
	 * @param nombre nombre del usuario
	 * @param ciudad ciudad recuperada del servicio Geonames
	 * @param codigoPostal codigo potal 
	 * @throws Exception en caso de error al insertar en BBDD.
	 */
	public void almacenarDatos(final String nombre, final String ciudad, final int codigoPostal) throws Exception;	
	
}
