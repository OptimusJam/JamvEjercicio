/**
 * 
 */
package es.sherpa.ejercicio.accesodatos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

import es.sherpa.ejercicio.modelo.MensajesRespuesta;

/**
 * @author joseangel.martinez
 * Clase encargada de realizar la conexión a BBDD y la ejecución de sus operaciones.
 */
final class AccesoDatos implements InterfaceAccesoDatos {

	/**
	 * Constantes para los campos del properties.
 	 * @author joseangel.martinez
	 */
	private enum ConstantesBaseDatos {
		
		DB_DRIVER("DB_DRIVER"),
		DB_CONNECTION("DB_CONNECTION"),
		DB_SCHEMA("DB_SCHEMA"),
		DB_USER("DB_USER"),
		DB_PASSWORD("DB_PASSWORD");
		
		String dato;
		
		ConstantesBaseDatos(final String dato) {
			this.dato = dato;
		}
		
		public String getDato() {
			return this.dato;
		}
	}
	
	private static Connection conexionDB = null;	
	
	/**
	 * Constructor
	 * @throws Exception en caso de error al crear la conexion con la BBDD
	 */
	public AccesoDatos() throws Exception {
		
		Properties properties = new Properties();
		
		try {
			
			if (this.conexionDB == null) {
				//Cargamos los datos de configuracion de un properties
				try {
					InputStream is = this.getClass().getClassLoader().getResourceAsStream("../configuracion.properties");
		            properties.load(is);
		        } catch(IOException e) {
		            throw new Exception();
		        }			
				
				//Obtenemos la conexion
				Class.forName(properties.getProperty(ConstantesBaseDatos.DB_DRIVER.getDato()));
				conexionDB = DriverManager.getConnection(
						properties.getProperty(ConstantesBaseDatos.DB_CONNECTION.getDato()) + properties.getProperty(ConstantesBaseDatos.DB_SCHEMA.getDato()), 
						properties.getProperty(ConstantesBaseDatos.DB_USER.getDato()),
						properties.getProperty(ConstantesBaseDatos.DB_PASSWORD.getDato()) );
			}
		} catch (Exception ex) {
			throw new Exception(MensajesRespuesta.MensajeErrorSinconexionConBaseDatos.getDato());
		} 
	}
	
	@Override
	public void almacenarDatos(String nombre, String ciudad, int codigoPostal) throws Exception {
		
		Statement statement = null;
		ResultSet objResultSet = null;
		
		try {
			
			statement = conexionDB.createStatement();
			
			String getMasterId = "SELECT id_master FROM master WHERE nombre = '" + nombre + "'";
			String insertMaster = "INSERT INTO master (nombre) VALUES ('"+ nombre +"')";
						
			int id_master = 0;
			boolean permanecer = true;
			while (permanecer) {			
				objResultSet = statement.executeQuery(getMasterId);
				while (objResultSet.next()) {
					id_master = objResultSet.getInt("id_master");
					permanecer = false;					
				}
				if (!permanecer) {
					break;
				}
				int val = statement.executeUpdate(insertMaster);
				if (val != 1) {
					throw new Exception();
				}
			}			
			String insertDetalle = "INSERT INTO detalle (codigo_postal, ciudad, id_master ) VALUES ('" + codigoPostal + "','" + ciudad + "'," + id_master + ")";
			int val = statement.executeUpdate(insertDetalle);
			if (val != 1) {
				throw new Exception();
			}						
		} catch (Exception ex) {
			throw new Exception(MensajesRespuesta.MensajeErrorInsercionBaseDatos.getDato());
		} finally {
			
			if (statement != null) {
				statement.close();
			}
			
			if (conexionDB != null) {
				conexionDB.close();
			}
			conexionDB = null;
		}		
	}
}
