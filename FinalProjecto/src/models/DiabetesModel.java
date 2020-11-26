package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.data.category.DefaultCategoryDataset;

import controllers.MainController;

public class DiabetesModel {
	private DBconnection connection;
	private static Connection conn;
	private int hconDiab,mconDiab;
	private int hsinDiab,msinDiab;
	private String estado;
		
	public DiabetesModel(String edo) {
		connection = new DBconnection();
		try{
			this.estado = edo;
			conn = connection.getConnection();
			this.diabetesData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void diabetesData(){
		try{
			ResultSet rs,rs2;
			// Declaracion de Queries
			String sql = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and DIABETES = 1 group by SEXO order by SEXO ASC;";
			String sql2 = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and DIABETES = 2 group by SEXO order by SEXO ASC;";

			PreparedStatement prest = conn.prepareStatement(sql);
			PreparedStatement prest2 = conn.prepareStatement(sql2);
			
			//Preparacion datos Query 1
			rs = prest.executeQuery();
			if(rs.next()) {
				// Validacion de Resultset
				if(rs.getInt(2) == 1) {
					this.setmconDiab(rs.getInt(1));
				}else {
					this.setmconDiab(0);
					this.sethconDiab(rs.getInt(1));
				}
				if(rs.next()) {
					this.sethconDiab(rs.getInt(1));
				}
			}else {
				this.sethconDiab(0);
				this.setmconDiab(0);
			}
			// Preparacion Query 2
			rs2 = prest2.executeQuery();
			if(rs2.next()) {
				// Validacion de resultset
				if(rs2.getInt(2) == 1) {
					this.setmsinDiab(rs2.getInt(1));
				}else {
					this.setmsinDiab(0);
					this.sethsinDiab(rs2.getInt(1));
				}
				if(rs2.next()) {
					this.sethsinDiab(rs2.getInt(1));
				}
			}else {
				this.setmsinDiab(0);
				this.sethsinDiab(0);
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
		   dataset.addValue(this.gethconDiab(), "Hombres", "Con Diabetes");
		   dataset.addValue(this.getmconDiab(), "Mujeres", "Con Diabetes");
		   dataset.addValue(this.gethsinDiab(), "Hombres", "Sin Diabetes");
		   dataset.addValue(this.getmsinDiab(), "Mujeres", "Sin Diabetes");		   
		   MainController.updateGraph("Diabates en COVID-19", "Población", "Diabetes", dataset,estado,"Diabetes");
	}
	public int gethconDiab() {
		return hconDiab;
	}
	public void sethconDiab(int hconDiab) {
		this.hconDiab = hconDiab;
	}
	public int getmconDiab() {
		return mconDiab;
	}
	public void setmconDiab(int mconDiab) {
		this.mconDiab = mconDiab;
	}
	public int gethsinDiab() {
		return hsinDiab;
	}
	public void sethsinDiab(int hsinDiab) {
		this.hsinDiab = hsinDiab;
	}
	public int getmsinDiab() {
		return msinDiab;
	}
	public void setmsinDiab(int msinDiab) {
		this.msinDiab = msinDiab;
	}	
}

