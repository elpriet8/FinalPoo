package models;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DBconnection {		
	private static Connection conn; // Objeto de tipo Connection 
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "root";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/CSV_DB";
		
	public DBconnection(){
		conn = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			/*if (conn != null){
				System.out.println("Conexión a BD exitosa");
			}*/
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error","No se pudo establecer conexión",JOptionPane.INFORMATION_MESSAGE);
				//System.out.println("No Se conectó");
			}
		}
		// Recupera el objeto de tipo Connection
		public Connection getConnection(){
			return conn;
		}
		// Metodo para cerrar conexion con BD
		public void DesConnecton(){
			conn = null;
			if(conn == null){
				System.out.println("Terminó conexión");
			}
	   }
	}

