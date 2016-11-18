/**
 * 
 */
package es.sherpa.ejercicio.modelo;

/**
 * @author joseangel.martinez
 *
 */
public enum MensajesRespuesta {

	StatusCorrecto("CORRECTO"),
	StatusError("ERROR"),
	MensajeCorrecto("EL PROCESO HA FINALIZADO CON ÉXITO"),
	MensajeErrorCodigoPostalSinCiudad("EL CODIGO POSTAL NO CORRESPONDE CON NINGUNA CIUDAD"),
	MensajeErrorSinconexionConGeonames("NO SE HA PODIDO REALIZAR LA CONEXION CON EL SERVICIO GEONAMES"),
	MensajeErrorSinconexionConBaseDatos("NO SE HA PODIDO REALIZAR LA CONEXION CON LA BASE DE DATOS"),
	MensajeErrorInsercionBaseDatos("NO SE HA PODIDO REALIZAR LA INSERCION EN LA BASE DE DATOS");		
	
	private final String mensajeError;

	public String getDato() {
		return mensajeError;
	}

	private MensajesRespuesta(final String mensaje) {
		mensajeError = mensaje;
	}
}