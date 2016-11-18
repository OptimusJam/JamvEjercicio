/**
 * @author joseangel.martinez
 *
 */

package es.sherpa.ejercicio.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Modelo {
	
	/**
	 * Status: Resultado de la operación
	 */
	@XmlElement(name="status")
	private String status;
	
	/**
	 * Mensaje: Información sobre el resultado de la operación
	 */
	@XmlElement(name="mensaje")
	private String mensaje;

	/**
	 * @return resultado de la operación
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status resultado de la operación
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return información sobre el resultado de la operación
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje información sobre el resultado de la operación
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder("{ ");

		builder.append("status: " ).append(status).append(", ");
		builder.append("mensaje: " ).append(mensaje).append("}");
		
		return builder.toString();
	
	}
	
	
	public static Modelo fromJSON(final JSONObject objJSON) {
		
		Modelo retorno = null;
		
		try {
			retorno = new Modelo();
			retorno.setStatus(objJSON.getString("status")); 
			retorno.setMensaje(objJSON.getString("mensaje"));
		} catch(Exception ex) {
			return null;
		}
		
		return retorno;
		
	}
}
