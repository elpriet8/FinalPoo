package models;
import controllers.MainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;
public class GeneroModel {
	private DBconnection connection;
	private static Connection conn;
	private int total;
	private String genero;
	private boolean hombres;
			
		public GeneroModel(boolean hombres) {
			connection = new DBconnection();
			try{
				this.hombres = hombres;
				conn = connection.getConnection();
				this.genData();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		private void genData(){
			try{
				ResultSet rs;
				String sql;
				if(hombres) {
					sql = "select count(*) from TBL_NAME where  SEXO = 2";
					this.genero = "Hombres";
				}else {
					sql = "select count(*) from TBL_NAME where  SEXO = 1";
					this.genero = "Mujeres";
				}

				PreparedStatement prest = conn.prepareStatement(sql);
				
				//Preparacion datos Query
				rs = prest.executeQuery();
				if(rs.next()) {
					// Validacion de Resultset
					this.setTotal(rs.getInt(1));
				}else {
					this.setTotal(0);
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
			   dataset.addValue(this.getTotal(), genero, "Nivel Nacional");	   
			   MainController.updateGraph(genero + " a Nivel Nacional", "Poblacion COVID-19",genero, dataset, "Nivel Nacional","No Aplica");
		}
		public int getTotal() {
			return total;
		}
		
		private void setTotal(int total) {
			this.total = total;
		}
}
