package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.DefaultCategoryDataset;  

public class StatsView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Font font1 = new Font("SansSerif", Font.PLAIN, 21);
	public JLabel filtros = new JLabel("");
	public JPanel main = new JPanel();
	public JPanel submain = new JPanel();
	public JPanel options = new JPanel();
	public JPanel graph = new JPanel();
	public String tituloGrafica,ejeX,ejeY;
	public DefaultCategoryDataset dataset;
	public JFreeChart chart;
	public JComboBox<Object> edos = new JComboBox<Object>();
	public JCheckBox hom = new JCheckBox("Hombres en México");
	public JCheckBox muj = new JCheckBox("Mujeres en México");
	public JCheckBox asma = new JCheckBox("Asma");
	public JCheckBox epoc = new JCheckBox("EPOC");
	public JCheckBox edad = new JCheckBox("Edad");
	public JCheckBox hip = new JCheckBox("Hipertensión");
	public JCheckBox obes = new JCheckBox("Obesidad");
	public JCheckBox diab = new JCheckBox("Diabetes");
	
	public StatsView(){
		super("Estadisticas COVID-19");
		setSize(1280, 780);  
		setLocationRelativeTo(null);  
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);

	    setLayout(new GridLayout(1,2));
	    this.main.setLayout(new GridLayout(2,1));
	    this.main.setSize(380,780);
	    this.submain.setLayout(new BorderLayout());
	    this.options.setLayout(new GridLayout(4,2));
	    this.graph.setSize(800,780);
	    this.graph.setLayout(new BorderLayout());
	    
	    this.edos.setFocusable(true);
	    this.edos.setForeground(Color.decode("#1DBD3D"));
	    this.edos.setBackground(Color.WHITE);
	    
	    // Estilos CheckBox
	    this.asma.setForeground(Color.decode("#e84043"));
	    this.epoc.setForeground(Color.decode("#e84043"));
	    this.obes.setForeground(Color.decode("#e84043"));
	    this.hip.setForeground(Color.decode("#e84043"));
	    this.diab.setForeground(Color.decode("#e84043"));
	    this.edad.setForeground(Color.decode("#e84043"));
	    this.hom.setForeground(Color.decode("#e84043"));
	    this.muj.setForeground(Color.decode("#e84043"));
	    
	    // Tipo de Letra
	    this.filtros.setFont(font1);
	    this.asma.setFont(font1);
	    this.epoc.setFont(font1);
	    this.obes.setFont(font1);
	    this.hip.setFont(font1);
	    this.diab.setFont(font1);
	    this.edad.setFont(font1);
	    this.muj.setFont(font1);
	    this.hom.setFont(font1);
	    
	    // Inserción Elementos en Frame y Panel
	    this.submain.add(edos,BorderLayout.CENTER);
	    this.submain.add(filtros,BorderLayout.NORTH);
	    this.main.add(submain);
	    this.main.add(options);
	    this.options.add(hom);
	    this.options.add(muj);
	    this.options.add(epoc);
	    this.options.add(hip);
	    this.options.add(obes);
	    this.options.add(diab);
	    this.options.add(edad);
	    this.options.add(asma);
	    this.options.setBackground(Color.WHITE);
	    this.submain.setBackground(Color.WHITE);
	    this.main.setBackground(Color.WHITE);
	    this.graph.setBackground(Color.WHITE);
	    add(this.main);
	    add(this.graph);
	}
}
