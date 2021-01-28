/**
 * Conecta con la base de datos dada dentro del servidor MySQL localhost
 * 
 * @param database Nombre de la base de datos
 * @return La conexión con la base de datos
 * @throws Exception agrupa las excepciones de tipo InstantiationException,
 *                   IllegalAccessException, ClassNotFoundException y
 *                   SQLException
 */
package es.studium.GestorConexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alvca
 *
 */
public class GestorConexiones {
	static String driver = "com.mysql.jdbc.Driver";
	static String login = "ClaseStudium";
	static String password = "Studium2020;";
	
	public static synchronized Connection conectar(String database) throws Exception {
		Connection connect = null;
		try
		{
		String url ="jdbc:mysql://localhost:3306/"+database+"?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&useSSL=false";
		//Cargar los controladores para el acceso a la BD
		Class.forName(driver);
		//Establecer la conexión con la BD hotel
		connect = DriverManager.getConnection(url, login, password);
		}
		catch (SQLException e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		return connect;
	}
	
	public static synchronized void desconectar(Connection coneccion) throws Exception
	{
		try
		{
			if(coneccion!=null)
			{
				coneccion.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
}
