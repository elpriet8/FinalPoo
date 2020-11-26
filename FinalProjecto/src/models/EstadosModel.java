package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadosModel {
	
	private DBconnection connection;
	private static Connection conn;
	public String estado;
	
	public EstadosModel() {
		connection = new DBconnection();
		try{
			conn = connection.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println(" SQLException : " + e.getMessage() );	
		}
	}
	
	public static ArrayList<String> cargarEdos(){
		ArrayList<String> estados = new ArrayList<String>();
		
		try{
			PreparedStatement prest = conn.prepareStatement("select ENTIDAD_UM from TBL_NAME group by ENTIDAD_UM order by ENTIDAD_UM ASC;");
			ResultSet rs;
			rs = prest.executeQuery();
			estados.add("Seleciona un Estado:");
			while(rs.next()){
	        	// Lectura rs y llenado de Combobox
	        	estados.add(rs.getString(1));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estados;
	}
	
}
