package models;
import controllers.MainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;

public class EdadModel {

	private DBconnection connection;
	private static Connection conn;
	private int edad20, edad2029, edad3039, edad4049, edad5059, edad6069, edad700;
	private String estado;
		
	public EdadModel(String edo) {
		connection = new DBconnection();
		try{
			this.estado = edo;
			conn = connection.getConnection();
			this.edadData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void edadData(){
		try{
			ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7;
			// Declaracion de Queries
			
			String sql1 = "select count(*) from TBL_NAME where EDAD <20 and ENTIDAD_UM = " +"'"+ estado + "';";
			String sql2 = "select count(*) from TBL_NAME where EDAD >= 20 and EDAD < 30 and ENTIDAD_UM = "+"'" + estado + "';";
			String sql3 = "select count(*) from TBL_NAME where EDAD >= 30 and EDAD < 40 and ENTIDAD_UM = "+"'" + estado + "';";
			String sql4 = "select count(*) from TBL_NAME where EDAD >= 40 and EDAD < 50 and ENTIDAD_UM = "+"'"+ estado + "';";
			String sql5 = "select count(*) from TBL_NAME where EDAD >= 50 and EDAD < 60 and ENTIDAD_UM = "+"'" + estado + "';";
			String sql6 = "select count(*) from TBL_NAME where EDAD >= 60 and EDAD < 70 and ENTIDAD_UM = "+"'" + estado + "';";
			String sql7 = "select count(*) from TBL_NAME where EDAD >= 70 and EDAD < 120 and ENTIDAD_UM = "+"'" + estado + "';";
			
			PreparedStatement prest1 = conn.prepareStatement(sql1);
			PreparedStatement prest2 = conn.prepareStatement(sql2);
			PreparedStatement prest3 = conn.prepareStatement(sql3);
			PreparedStatement prest4 = conn.prepareStatement(sql4);
			PreparedStatement prest5 = conn.prepareStatement(sql5);
			PreparedStatement prest6 = conn.prepareStatement(sql6);
			PreparedStatement prest7 = conn.prepareStatement(sql7);
			
			rs1 = prest1.executeQuery();
			rs2 = prest2.executeQuery();
			rs3 = prest3.executeQuery();
			rs4 = prest4.executeQuery();
			rs5 = prest5.executeQuery();
			rs6 = prest6.executeQuery();
			rs7 = prest7.executeQuery();

			
			if(rs1.next()) {
				this.edad20 = rs1.getInt(1);
			}else {
				this.edad20 = 0;
			}
			if(rs2.next()) {
				this.edad2029 = rs2.getInt(1);
			}else {
				this.edad2029 = 0;
			}
			if(rs3.next()) {
				this.edad3039 = rs3.getInt(1);
			}else {
				this.edad3039 = 0;
			}
			if(rs4.next()) {
				this.edad4049 = rs4.getInt(1);
			}else {
				this.edad4049 = 0;
			}
			if(rs5.next()) {
				this.edad5059 = rs5.getInt(1);
			}else {
				this.edad5059 = 0;
			}
			if(rs6.next()) {
				this.edad6069 = rs6.getInt(1);
			}else {
				this.edad6069 = 0;
			}
			if(rs7.next()) {
				this.edad700 = rs7.getInt(1);
			}else {
				this.edad700 = 0;
			}
		createDataset();
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" SQLException : " + e.getMessage() );	
		}
	}
			
	public void createDataset() {  
		   DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		   dataset.addValue(edad20, "Menor 20", "< 20");
		   dataset.addValue(edad2029, "20 - 29", "20 - 29");
		   dataset.addValue(edad3039, "30 - 39", "30 - 39");
		   dataset.addValue(edad4049, "40 - 49", "40 - 49");
		   dataset.addValue(edad5059, "50 - 59", "50 - 59");
		   dataset.addValue(edad6069, "60 - 69", "60 - 69");
		   dataset.addValue(edad700, "70 - 100+", "70 - 100+");
		   MainController.updateGraph("Casos Covid por Edad", "Edad", "Población", dataset,estado,"No aplica");
	}
}
