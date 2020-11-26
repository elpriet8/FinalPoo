package main;
import controllers.MainController;
import views.StatsView;
import models.EstadosModel;

public class MainExecute {

		public static void main(String[] args) {
			StatsView example = new StatsView(); 
			EstadosModel testmodel = new EstadosModel();
			MainController testcontroller = new MainController(example, testmodel);
		    example.setVisible(true);
		    
			// instanciar vista
			// instanciar controlador
			// instanciar modelo
			// visibilidad vista true
		}
}  