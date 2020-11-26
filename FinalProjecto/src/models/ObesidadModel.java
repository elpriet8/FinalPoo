package models;
import controllers.MainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;

public class ObesidadModel {

	private DBconnection connection;
	private static Connection conn;
	private int hconObes,mconObes;
	private int hsinObes,msinObes;
	private String estado;
		
	public ObesidadModel(String edo) {
		connection = new DBconnection();
		try{
			this.estado = edo;
			conn = connection.getConnection();
			this.obesidadData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void obesidadData(){
		try{
			ResultSet rs,rs2;
			// Declaracion de Queries
			String sql = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and OBESIDAD = 1 group by SEXO order by SEXO ASC;";
			String sql2 = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and OBESIDAD = 2 group by SEXO order by SEXO ASC;";

			PreparedStatement prest = conn.prepareStatement(sql);
			PreparedStatement prest2 = conn.prepareStatement(sql2);
			
			//Preparacion datos Query 1
			rs = prest.executeQuery();
			if(rs.next()) {
				// Validacion de Resultset
				if(rs.getInt(2) == 1) {
					this.setMconObes(rs.getInt(1));
				}else {
					this.setMconObes(0);
					this.setHconObes(rs.getInt(1));
				}
				if(rs.next()) {
					this.setHconObes(rs.getInt(1));
				}
			}else {
				this.setHconObes(0);
				this.setMconObes(0);
			}
			// Preparacion Query 2
			rs2 = prest2.executeQuery();
			if(rs2.next()) {
				// Validacion de resultset
				if(rs2.getInt(2) == 1) {
					this.setMsinObes(rs2.getInt(1));
				}else {
					this.setMsinObes(0);
					this.setHsinObes(rs2.getInt(1));
				}
				if(rs2.next()) {
					this.setHsinObes(rs2.getInt(1));
				}
			}else {
				this.setMsinObes(0);
				this.setHsinObes(0);
			}
			// crear dataset
			createDataset();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" SQLException : " + e.getMessage() );	
		}
	}
	
	public void createDataset() {  
		   DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		   dataset.addValue(this.getHconObes(), "Hombres", "Con Obesidad");
		   dataset.addValue(this.getMconObes(), "Mujeres", "Con Obesidad");
		   dataset.addValue(this.getHsinObes(), "Hombres", "Sin Obesidad");
		   dataset.addValue(this.getMsinObes(), "Mujeres", "Sin Obesidad");		   
		   MainController.updateGraph("Obesidad en COVID-19", "Población", "Obesidad", dataset,estado,"Obesidad");
	}
	public int getHconObes() {
		return hconObes;
	}
	public void setHconObes(int hconObes) {
		this.hconObes = hconObes;
	}
	public int getMconObes() {
		return mconObes;
	}
	public void setMconObes(int mconObes) {
		this.mconObes = mconObes;
	}
	public int getHsinObes() {
		return hsinObes;
	}
	public void setHsinObes(int hsinObes) {
		this.hsinObes = hsinObes;
	}
	public int getMsinObes() {
		return msinObes;
	}
	public void setMsinObes(int msinObes) {
		this.msinObes = msinObes;
	}
	
	
}
