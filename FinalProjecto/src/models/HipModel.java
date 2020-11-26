package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;
import controllers.MainController;

public class HipModel {
	private DBconnection connection;
	private static Connection conn;
	private int hconHip,mconHip;
	private int hsinHip,msinHip;
	private String estado;
		
	public HipModel(String edo) {
		connection = new DBconnection();
		try{
			this.estado = edo;
			conn = connection.getConnection();
			this.hipData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void hipData(){
		try{
			ResultSet rs,rs2;
			// Declaracion de Queries
			String sql = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and HIPERTENSION = 1 group by SEXO order by SEXO ASC;";
			String sql2 = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and HIPERTENSION = 2 group by SEXO order by SEXO ASC;";

			PreparedStatement prest = conn.prepareStatement(sql);
			PreparedStatement prest2 = conn.prepareStatement(sql2);
			
			//Preparacion datos Query 1
			rs = prest.executeQuery();
			if(rs.next()) {
				// Validacion de Rsultset
				if(rs.getInt(2) == 1) {
					this.setmconHip(rs.getInt(1));
				}else {
					this.setmconHip(0);
					this.sethconHip(rs.getInt(1));
				}
				if(rs.next()) {
					this.sethconHip(rs.getInt(1));
				}
			}else {
				this.sethconHip(0);
				this.setmconHip(0);
			}
			// Preparacion Query 2
			rs2 = prest2.executeQuery();
			if(rs2.next()) {
				// Validacion de Resultset
				if(rs2.getInt(2) == 1) {
					this.setmsinHip(rs2.getInt(1));
				}else {
					this.setmsinHip(0);
					this.sethsinHip(rs2.getInt(1));
				}
				if(rs2.next()) {
					this.sethsinHip(rs2.getInt(1));
				}
			}else {
				this.setmsinHip(0);
				this.sethsinHip(0);
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
		   dataset.addValue(this.gethconHip(), "Hombres", "Con Hipertensión");
		   dataset.addValue(this.getmconHip(), "Mujeres", "Con Hipertensión");
		   dataset.addValue(this.gethsinHip(), "Hombres", "Sin Hipertensión");
		   dataset.addValue(this.getmsinHip(), "Mujeres", "Sin Hipertensión");		   
		   MainController.updateGraph("Hipertensión en COVID-19", "Población", "Hipertensión", dataset,estado,"Hipertensión");
	}
	public int gethconHip() {
		return hconHip;
	}
	public void sethconHip(int hconHip) {
		this.hconHip = hconHip;
	}
	public int getmconHip() {
		return mconHip;
	}
	public void setmconHip(int mconHip) {
		this.mconHip = mconHip;
	}
	public int gethsinHip() {
		return hsinHip;
	}
	public void sethsinHip(int hsinHip) {
		this.hsinHip = hsinHip;
	}
	public int getmsinHip() {
		return msinHip;
	}
	public void setmsinHip(int msinHip) {
		this.msinHip = msinHip;
	}
}
