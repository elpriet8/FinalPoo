package models;
import controllers.MainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;

public class AsmaModel {

	private DBconnection connection;
	private static Connection conn;
	private int hombresAsma,hsinAsma;
	private int mujeresAsma,msinAsma;
	private String estado;
		
	public AsmaModel(String edo) {
		connection = new DBconnection();
		try{
			this.estado = edo;
			conn = connection.getConnection();
			this.asmaData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void asmaData(){
		try{
			ResultSet rs,rs2;
			// Declaracion de Queries
			String sql = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and ASMA = 1 group by SEXO order by SEXO ASC;";
			String sql2 = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and ASMA = 2 group by SEXO order by SEXO ASC;";

			PreparedStatement prest = conn.prepareStatement(sql);
			PreparedStatement prest2 = conn.prepareStatement(sql2);
			
			//Preparacion datos Query 1
			rs = prest.executeQuery();
			if(rs.next()) {
				// Validacion de Resultset
				if(rs.getInt(2) == 1) {
					this.setmujeresAsma(rs.getInt(1));
				}else {
					this.setmujeresAsma(0);
					this.sethombresAsma(rs.getInt(1));
				}
				if(rs.next()) {
					this.sethombresAsma(rs.getInt(1));
				}
			}else {
				this.sethombresAsma(0);
				this.sethombresAsma(0);
			}
			// Preparacion Query 2
			rs2 = prest2.executeQuery();
			if(rs2.next()) {
				// Validacion de Resultset
				if(rs2.getInt(2) == 1) {
					this.setmsinAsma(rs2.getInt(1));
				}else {
					this.setmsinAsma(0);
					this.sethsinAsma(rs2.getInt(1));
				}
				if(rs2.next()) {
					this.sethsinAsma(rs2.getInt(1));
				}
			}else {
				this.setmsinAsma(0);
				this.sethsinAsma(0);
			}
			createDataset();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" SQLException : " + e.getMessage() );	
		}
	}
	
	public void createDataset() {  
		   DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		   dataset.addValue(this.gethombresAsma(), "Hombres", "Con Asma");
		   dataset.addValue(this.getmujeresAsma(), "Mujeres", "Con Asma");
		   dataset.addValue(this.gethsinAsma(), "Hombres", "Sin Asma");
		   dataset.addValue(this.getmsinAsma(), "Mujeres", "Sin Asma");		   
		   MainController.updateGraph("Casos Asma", "Población", "Asma", dataset, estado, "Asma");
	}
	
	public int gethombresAsma() {
		return hombresAsma;
	}

	public void sethombresAsma(int hombresAsma) {
		this.hombresAsma = hombresAsma;
	}

	public int getmujeresAsma() {
		return mujeresAsma;
	}

	public void setmujeresAsma(int mujeresAsma) {
		this.mujeresAsma = mujeresAsma;
	}

	public int gethsinAsma() {
		return hsinAsma;
	}

	public void sethsinAsma(int h) {
		this.hsinAsma = h;
	}
	public int getmsinAsma() {
		return msinAsma;
	}

	public void setmsinAsma(int h) {
		this.msinAsma = h;
	}
}
