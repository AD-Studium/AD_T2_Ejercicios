/**
 * 
 */
package es.studium.EjercicioPerros;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alvca
 *
 */
public class Perros {

	private static void crearTablaPerros() throws SQLException{
			Connection conexion = null;
			try {
				conexion = GestorConexiones.conectar("ad_tema2");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String consulta = "CREATE TABLE IF NOT EXISTS perros (id INT PRIMARY KEY, raza VARCHAR(10), tamano VARCHAR(10), edad INT,"
					+ "color VARCHAR(10))";
			Statement st = conexion.createStatement();
			st.executeUpdate(consulta);
			st.close();
		
	}
	private static void insertarPerroSQL(int id, String raza, String tamano, int edad, String color) {
		try {
			Connection conexion =GestorConexiones.conectar("ad_tema2");
			String consulta = "INSERT INTO perros VALUES (" + String.valueOf(id) + ", \"" + raza + "\", \""
					+ String.valueOf(tamano) + "\"," + edad + ", \"" + color + "\")";
			Statement st = conexion.createStatement();
			st.execute(consulta);
			System.out.println("Insertado perro " + id);
			st.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void insertarPerroRSMax(String raza, String tamano, int edad, String color) throws SQLException {
		final String CONSULTA_MAX_ID = "SELECT MAX(id) AS MAXID FROM perros;";
		final String CONSULTA_TODOS = "SELECT * FROM perros";
		Connection conexion = null;
		try {
			conexion = GestorConexiones.conectar("ad_tema2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Averiguamos cuál es el id mayor */
		Statement st = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(CONSULTA_MAX_ID);
 
		/* Otra forma: int maxID = rs.first() ? rs.getInt("MAXID") : 0; */
		int maxID = 0;
		if (rs.first()) {
			maxID = rs.getInt("MAXID");
		}
		rs.close();
		st.close();
 
		/* Insertar nuevo Perro */
		st = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(CONSULTA_TODOS);
 
		rs.moveToInsertRow();
		rs.updateInt("id", maxID + 1);
		rs.updateString("raza", raza);
		rs.updateString("tamano", tamano);
		rs.updateInt("edad", edad);
		rs.updateString("color", color);
		rs.insertRow();
 
		rs.close();
		st.close();
 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
