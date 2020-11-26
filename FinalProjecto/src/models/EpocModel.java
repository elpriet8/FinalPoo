package models;
import controllers.MainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;

public class EpocModel {
	private DBconnection connection;
	private static Connection conn;
	private int hconEpoc,mconEpoc;
	private int hsinEpoc,msinEpoc;
	private String estado;
		
	public EpocModel(String edo) {
		connection = new DBconnection();
		try{
			this.estado = edo;
			conn = connection.getConnection();
			this.epocData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void epocData(){
		try{
			ResultSet rs,rs2;
			// Declaracion de Queries
			String sql = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and EPOC = 1 group by SEXO order by SEXO ASC;";
			String sql2 = "select count(SEXO),SEXO from TBL_NAME where ENTIDAD_UM =" +"'" + estado+"'"+" and EPOC = 2 group by SEXO order by SEXO ASC;";

			PreparedStatement prest = conn.prepareStatement(sql);
			PreparedStatement prest2 = conn.prepareStatement(sql2);
			
			//Preparacion datos Query 1
			rs = prest.executeQuery();
			if(rs.next()) {
				// Validacion de Resultset
				if(rs.getInt(2) == 1) {
					this.setmconEpoc(rs.getInt(1));
				}else {
					this.setmconEpoc(0);
					this.sethconEpoc(rs.getInt(1));
				}
				if(rs.next()) {
					this.sethconEpoc(rs.getInt(1));
				}
			}else {
				this.sethconEpoc(0);
				this.setmconEpoc(0);
			}
			// Preparacion Query 2
			rs2 = prest2.executeQuery();
			if(rs2.next()) {
				// Validaciond e Resultset
				if(rs2.getInt(2) == 1) {
					this.setmsinEpoc(rs2.getInt(1));
				}else {
					this.setmsinEpoc(0);
					this.sethsinEpoc(rs2.getInt(1));
				}
				if(rs2.next()) {
					this.sethsinEpoc(rs2.getInt(1));
				}
			}else {
				this.setmsinEpoc(0);
				this.sethsinEpoc(0);
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
		   dataset.addValue(this.gethconEpoc(), "Hombres", "Con EPOC");
		   dataset.addValue(this.getmconEpoc(), "Mujeres", "Con EPOC");
		   dataset.addValue(this.gethsinEpoc(), "Hombres", "Sin EPOC");
		   dataset.addValue(this.getmsinEpoc(), "Mujeres", "Sin EPOC");		   
		   MainController.updateGraph("EPOC en COVID-19", "Población", "EPOC", dataset,estado,"EPOC");
	}
	public int gethconEpoc() {
		return hconEpoc;
	}
	public void sethconEpoc(int hconEpoc) {
		this.hconEpoc = hconEpoc;
	}
	public int getmconEpoc() {
		return mconEpoc;
	}
	public void setmconEpoc(int mconEpoc) {
		this.mconEpoc = mconEpoc;
	}
	public int gethsinEpoc() {
		return hsinEpoc;
	}
	public void sethsinEpoc(int hsinEpoc) {
		this.hsinEpoc = hsinEpoc;
	}
	public int getmsinEpoc() {
		return msinEpoc;
	}
	public void setmsinEpoc(int msinEpoc) {
		this.msinEpoc = msinEpoc;
	}
}
