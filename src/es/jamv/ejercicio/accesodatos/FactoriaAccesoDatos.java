/**
 * 
 */
package es.jamv.ejercicio.accesodatos;


/**
 * @author joseangel.martinez
 * Factoria para obtener clases de acceso a la BBDD 
 */
public class FactoriaAccesoDatos {
	
	/**
	 * Proporciona al nivel superior un objeto para acceder a la BBDD 
	 * @return Objeto para acceder a la BBDD
	 * @throws Exception Error de conexion con la BBDD
	 */
	public InterfaceAccesoDatos obtenerAccesoDatos() throws Exception {
		return new AccesoDatos();
	}
}