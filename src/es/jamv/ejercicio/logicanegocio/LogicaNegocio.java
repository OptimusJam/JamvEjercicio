/**
 * 
 */
package es.jamv.ejercicio.logicanegocio;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import es.jamv.ejercicio.accesodatos.FactoriaAccesoDatos;
import es.jamv.ejercicio.accesodatos.InterfaceAccesoDatos;
import es.jamv.ejercicio.modelo.MensajesRespuesta;
import es.jamv.ejercicio.modelo.Modelo;

/**
 * @author joseangel.martinez
 *
 */
final class LogicaNegocio implements InterfaceLogicaNegocio {

	/**
	 * Datos de acceso al servicio geonames
	 * @author joseangel.martinez
	 *
	 */
	private enum DatosServicioGeoname {
		
		NombreUsuarioServicioGeonames("jamv_18"),		
		UrlServicioGeonames("http://api.geonames.org/postalCodeSearchJSON?postalcode="),
		UrlServcioGeonamesArgumentos("&maxRows=1&username="+DatosServicioGeoname.NombreUsuarioServicioGeonames.getDato()+"&country=ES");
		
		private String dato;
		
		public String getDato() {
			return this.dato;
		}
		
		private DatosServicioGeoname(final String dato) {
			this.dato = dato;
		}
		
	}
		
	public LogicaNegocio() {}
	
	@Override
	public Modelo realizaProceso(String nombre, int codigoPostal) {
		// TODO Auto-generated method stub
		Modelo objModelo = null;
		InterfaceAccesoDatos objAccesoDatos = null;
		
		try {
			//Realizar la consulta consulta al servicio externo para obtener la ciudad
			String ciudad = consultarServicioGeonames(codigoPostal);
			
			//Realizar el almacenamiento de los datos
			objAccesoDatos = new FactoriaAccesoDatos().obtenerAccesoDatos();
			objAccesoDatos.almacenarDatos(nombre, ciudad, codigoPostal);
			
			//Si todo va bien, devolvemos el modelo con la informacion.
			objModelo = new Modelo();
			objModelo.setStatus(MensajesRespuesta.StatusCorrecto.getDato());
			objModelo.setMensaje(MensajesRespuesta.MensajeCorrecto.getDato());
		
		} catch (Exception ex) {
			objModelo = new Modelo();
			objModelo.setStatus(MensajesRespuesta.StatusError.getDato());
			objModelo.setMensaje(ex.getMessage());
		}
		
		return objModelo;
		
	}
	
	/**
	 * Método para la consulta de la ciudad por código postal
	 * @param codigoPostal codigo postal
	 * @return ciudad correspondiente al código postal
	 * @throws Exception en caso de error de conexion o código postal no asignado a una ciudad
	 */
	private String consultarServicioGeonames(final int codigoPostal) throws Exception {
		
		String ciudad = null;
		JSONObject objResponse = null;
		Client cliente = null;
		WebResource recurso = null;
		
		try {
			cliente = Client.create();
			recurso = cliente.resource(DatosServicioGeoname.UrlServicioGeonames.getDato() 
				+ String.valueOf(codigoPostal) 
				+ DatosServicioGeoname.UrlServcioGeonamesArgumentos.getDato());
			String response = recurso.accept(MediaType.APPLICATION_JSON).get(String.class);
			objResponse = new JSONObject(response);
		} catch(Exception ex) {
			throw new Exception(MensajesRespuesta.MensajeErrorSinconexionConGeonames.getDato());			
		}
		
		if ( ( !((org.json.JSONArray)objResponse.get("postalCodes")).isNull(0) )
			&&	( ( (org.json.JSONObject)((org.json.JSONArray)objResponse.get("postalCodes")).get(0)).get("placeName") != null ) ) {
			ciudad = (String)((org.json.JSONObject)((org.json.JSONArray)objResponse.get("postalCodes")).get(0)).get("placeName");
		} else {
			throw new Exception(MensajesRespuesta.MensajeErrorCodigoPostalSinCiudad.getDato());
		}
	
		return ciudad;
		
	}
	
}
