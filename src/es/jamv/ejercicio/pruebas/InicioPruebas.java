/**
 * 
 */
package es.jamv.ejercicio.pruebas;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import es.jamv.ejercicio.modelo.MensajesRespuesta;
import es.jamv.ejercicio.modelo.Modelo;

/**
 * @author joseangel.martinez
 *
 */
public class InicioPruebas {

	JSONObject objResponse = null;
	Client cliente = null;
	WebResource recurso = null;
	
	private String UrlServicio = "http://127.0.0.1:8080/SherpaEjercicioJava/rest/getCiudad";	
	
	@Before
	public void previoPrueba() {		
		cliente = Client.create();
		recurso = cliente.resource(UrlServicio);		
	}
	
	@After
	public void postPrueba() {		
		cliente = null;
		recurso = null;		
	}
	
	@Test
	public void pruebaCorrecta() {
		
		Modelo objModelo = null;
		
		try {			
			String response = recurso.path("Nombre").path("42002").accept(MediaType.APPLICATION_JSON).get(String.class);
			objModelo = Modelo.fromJSON(new JSONObject(response));			
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
		Assert.assertNotNull(objModelo);
		Assert.assertEquals(MensajesRespuesta.StatusCorrecto.getDato(), objModelo.getStatus());
		Assert.assertEquals(MensajesRespuesta.MensajeCorrecto.getDato(), objModelo.getMensaje());
	}
	
	@Test
	public void pruebaErrorConexionGeonames() {
	
		Modelo objModelo = null;
		
		try {			
			String response = recurso.path("Nombre").path("42002").accept(MediaType.APPLICATION_JSON).get(String.class);
			objModelo = Modelo.fromJSON(new JSONObject(response));			
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
		Assert.assertNotNull(objModelo);
		Assert.assertEquals(MensajesRespuesta.StatusError.getDato(), objModelo.getStatus());
		Assert.assertEquals(MensajesRespuesta.MensajeErrorSinconexionConGeonames.getDato(), objModelo.getMensaje());
		
	}
	
	@Test
	public void pruebaErrorCodigoPostalSinCiudad () {
		
		Modelo objModelo = null;
		
		try {
			String response = recurso.path("Nombre").path("42012").accept(MediaType.APPLICATION_JSON).get(String.class);
			objModelo = Modelo.fromJSON(new JSONObject(response));			
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
		Assert.assertNotNull(objModelo);
		Assert.assertEquals(MensajesRespuesta.StatusError.getDato(), objModelo.getStatus());
		Assert.assertEquals(MensajesRespuesta.MensajeErrorCodigoPostalSinCiudad.getDato(), objModelo.getMensaje());
		
	}
	
	@Test
	public void pruebaErrorConexionBaseDatos () {
		Modelo objModelo = null;
		
		try {
			String response = recurso.path("Nombre").path("42002").accept(MediaType.APPLICATION_JSON).get(String.class);
			objModelo = Modelo.fromJSON(new JSONObject(response));			
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
		Assert.assertNotNull(objModelo);
		Assert.assertEquals(MensajesRespuesta.StatusError.getDato(), objModelo.getStatus());
		Assert.assertEquals(MensajesRespuesta.MensajeErrorSinconexionConBaseDatos.getDato(), objModelo.getMensaje());		
	}
	
	@Test
	public void pruebaErrorInsercionBaseDatos () {
		Modelo objModelo = null;
		
		try {
			String response = recurso.path("Nombre").path("42002").accept(MediaType.APPLICATION_JSON).get(String.class);
			objModelo = Modelo.fromJSON(new JSONObject(response));			
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
		Assert.assertNotNull(objModelo);
		Assert.assertEquals(MensajesRespuesta.StatusError.getDato(), objModelo.getStatus());
		Assert.assertEquals(MensajesRespuesta.MensajeErrorInsercionBaseDatos.getDato(), objModelo.getMensaje());
	}

}
