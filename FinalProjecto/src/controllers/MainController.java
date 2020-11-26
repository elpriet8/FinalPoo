package controllers;
import views.StatsView;
import models.AsmaModel;
import models.DiabetesModel;
import models.EdadModel;
import models.EpocModel;
import models.EstadosModel;
import models.GeneroModel;
import models.HipModel;
import models.ObesidadModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainController implements ActionListener{
	
	private static StatsView vp1 = new StatsView();
	public EstadosModel pm1;
	public ArrayList<String> estados;

	public MainController(StatsView vista1, EstadosModel model1){
		MainController.vp1 = vista1;
		this.pm1 = model1;
		MainController.vp1.muj.addActionListener(this);
		MainController.vp1.hom.addActionListener(this);
		MainController.vp1.edos.addActionListener(this);
		MainController.vp1.asma.addActionListener(this);
		MainController.vp1.epoc.addActionListener(this);
		MainController.vp1.diab.addActionListener(this);
		MainController.vp1.obes.addActionListener(this);
		MainController.vp1.hip.addActionListener(this);
		MainController.vp1.edad.addActionListener(this);
		llenarEdos();
	}
	
	private void llenarEdos(){
		this.estados = EstadosModel.cargarEdos();
		for(int i=0;i<estados.size();i++) {
			vp1.edos.addItem(estados.get(i));
		}
	}
	
	public static void updateGraph(String titulo, String ejex, String ejey, DefaultCategoryDataset dataset,String edo, String cond) {
		try {
			vp1.chart = ChartFactory.createBarChart(  
				titulo, //Chart Title  
		        ejex, // Category axis  
				ejey, // Value axis
		        dataset, 
		        PlotOrientation.VERTICAL,
			    true,true,false
				);
			BarRenderer rend = (BarRenderer) vp1.chart.getCategoryPlot().getRenderer();
			rend.setMaximumBarWidth(.3);
			vp1.filtros.setText("<html><br>&ensp;<b>Estás viendo</b><br><br>\n"+ "&ensp;Estado: "+ edo + "<br>&ensp;Condicion: "+cond+"</html>");
			vp1.graph.removeAll();
			ChartPanel panel=new ChartPanel(vp1.chart);
			panel.setSize(500,500);
		    vp1.graph.add(panel, BorderLayout.CENTER);
		    vp1.graph.updateUI();
		    vp1.graph.repaint();
		    vp1.main.updateUI();
		    vp1.main.repaint();
			}catch(Exception err){
				System.out.println("Ocurrio un Error");
			}
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == vp1.edos) {
			if(MainController.vp1.edad.isSelected()) {
				EdadModel edad = new EdadModel(this.estados.get(vp1.edos.getSelectedIndex()));
			}else if(MainController.vp1.asma.isSelected()) {
				AsmaModel asma = new AsmaModel(this.estados.get(vp1.edos.getSelectedIndex()));
			}else if(MainController.vp1.hip.isSelected()) {
				HipModel hiper = new HipModel(this.estados.get(vp1.edos.getSelectedIndex()));
			}else if(MainController.vp1.obes.isSelected()) {
				ObesidadModel obeso = new ObesidadModel(this.estados.get(vp1.edos.getSelectedIndex()));
			}else if(MainController.vp1.epoc.isSelected()) {
				EpocModel epoc = new EpocModel(this.estados.get(vp1.edos.getSelectedIndex()));
			}else if(MainController.vp1.diab.isSelected()) {
				DiabetesModel diab = new DiabetesModel(this.estados.get(vp1.edos.getSelectedIndex()));
			}
		}
		if(e.getSource() == vp1.asma) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.diab.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			AsmaModel asma = new AsmaModel(this.estados.get(vp1.edos.getSelectedIndex()));	
		}
		if(e.getSource() == vp1.diab) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			DiabetesModel diab = new DiabetesModel(this.estados.get(vp1.edos.getSelectedIndex()));
		}
		if(e.getSource() == vp1.edad) {
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.diab.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			EdadModel edad = new EdadModel(this.estados.get(vp1.edos.getSelectedIndex()));
		}
		if(e.getSource() == vp1.hip) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.diab.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			HipModel hiper = new HipModel(this.estados.get(vp1.edos.getSelectedIndex()));
			
		}
		if(e.getSource() == vp1.obes) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.diab.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			ObesidadModel obeso = new ObesidadModel(this.estados.get(vp1.edos.getSelectedIndex()));
		}
		if(e.getSource() == vp1.epoc) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.diab.setSelected(false);
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			EpocModel epoc = new EpocModel(this.estados.get(vp1.edos.getSelectedIndex()));
		}
		if(e.getSource() == vp1.hom) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.muj.setSelected(false);
			GeneroModel homMod = new GeneroModel(true);
		}
		if(e.getSource() == vp1.muj) {
			MainController.vp1.edad.setSelected(false);
			MainController.vp1.hip.setSelected(false);
			MainController.vp1.obes.setSelected(false);
			MainController.vp1.asma.setSelected(false);
			MainController.vp1.epoc.setSelected(false);
			MainController.vp1.hom.setSelected(false);
			GeneroModel mujMod = new GeneroModel(false);
			
		}
	}
}
