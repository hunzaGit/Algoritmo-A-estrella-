package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.aStar.AEstrella;
import Negocio.aStar.Camino;
import Negocio.aStar.Mapa;
import Negocio.aStar.Nodo;
import Negocio.cronometro.Cronometro;
import Negocio.heuristica.CalcularDistanciaLineaRecta;
import Negocio.heuristica.InterfazHeuristica;


public class VentanaPrincipal extends javax.swing.JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonComenzar;
	private javax.swing.JButton jButtonReiniciar;
	private javax.swing.JButton jButtonRedimensionar;

	private javax.swing.JLabel jLabelNombre;
	private javax.swing.JLabel jLabelFacultad;

	private javax.swing.JPanel panelTablero;
	private javax.swing.JPanel panelInputs;

	private javax.swing.JLabel jLabelInicio;
	private javax.swing.JTextField textFieldIniX;
	private javax.swing.JTextField textFieldIniY;

	private javax.swing.JLabel jLabelFinal;
	private javax.swing.JTextField textFieldFinX;
	private javax.swing.JTextField textFieldFinY;

	private javax.swing.JTextField textFieldAnchuraTablero;
	private javax.swing.JTextField textFieldAlturaTablero;

	private int anchuraTablero = 15;
	private int alturaTablero = 10;

	// End of variables declaration
	private TableroCeldas tableroCeldas;
	private Mapa mapaLogico;
	InterfazHeuristica interfazHeuristic;
	AEstrella pathFinder;
	int[][] obstacleMap;
	Nodo nodo;

	// Paneles Intermedios
	private JPanel panelInputsIni;
	private JPanel panelInputsFin;
	private JPanel panelInputsTam;
	private JPanel panelInputsIniFin;
	private JPanel panelTamInputs;
	private JPanel panelInsputsSolo;
	private String stringTamañoTiempoTablero;

	// //////////////////////////////
	/**
	 * Creates new form Principal
	 */
	public VentanaPrincipal(Mapa map, InterfazHeuristica h, AEstrella pathFinder) {
		tableroCeldas = new TableroCeldas(this.anchuraTablero,
				this.alturaTablero);
		tableroCeldas.setAltura(alturaTablero);
		tableroCeldas.setAnchura(anchuraTablero);
		this.mapaLogico = map;
		interfazHeuristic = h;
		this.pathFinder = pathFinder;
		create();
		agregarManejadoresDeEventos();
		
		textFieldIniX.requestFocus();
		textFieldIniX.requestFocus(true);
	
	}

	public VentanaPrincipal(int anchura, int altura) {
		this.anchuraTablero = anchura;
		this.alturaTablero = altura;
		tableroCeldas = new TableroCeldas(this.anchuraTablero,
				this.alturaTablero);
		;
		tableroCeldas.setAltura(alturaTablero);
		tableroCeldas.setAnchura(anchuraTablero);
		tableroCeldas.inicializarEscuchadores(this);
		obstacleMap = new int[anchuraTablero + 1][alturaTablero + 1];
		create();
		agregarManejadoresDeEventos();
		
	}

	private void agregarManejadoresDeEventos() {
		ALElementosVentana oyenteAL = new ALElementosVentana();
		KLElementosVentana oyenteKL = new KLElementosVentana();
		FLElementosVentana oyenteFL = new FLElementosVentana();
		
		jButtonComenzar.addActionListener(oyenteAL);
		jButtonRedimensionar.addActionListener(oyenteAL);
		jButtonReiniciar.addActionListener(oyenteAL);
		
		jButtonComenzar.addKeyListener(oyenteKL);
		jButtonRedimensionar.addKeyListener(oyenteKL);
		jButtonReiniciar.addKeyListener(oyenteKL);
		
		textFieldIniY.addFocusListener(oyenteFL);
		textFieldFinY.addFocusListener(oyenteFL);
		
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void create() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		this.setSize(1200, 1000);
		this.setTitle("Algortimo A* [ " + anchuraTablero + "x" + alturaTablero
				+ " ] - Rodrigo de Miguel González");
		jLabelNombre = new javax.swing.JLabel();
		jLabelFacultad = new javax.swing.JLabel();

		jButtonComenzar = new javax.swing.JButton("Iniciar");
		jButtonReiniciar = new javax.swing.JButton("Reiniciar");
		jButtonRedimensionar = new javax.swing.JButton("Redimensionar");

		textFieldIniX = new javax.swing.JTextField(5);
		textFieldIniY = new javax.swing.JTextField(5);
		textFieldFinX = new javax.swing.JTextField(5);
		textFieldFinY = new javax.swing.JTextField(5);

		textFieldAlturaTablero = new javax.swing.JTextField(5);
		textFieldAnchuraTablero = new javax.swing.JTextField(5);

		jLabelInicio = new javax.swing.JLabel();
		jLabelFinal = new javax.swing.JLabel();

		panelTablero = new javax.swing.JPanel();
		panelInputs = new JPanel();
		panelInputsIni = new JPanel();
		panelInputsFin = new JPanel();
		panelInputsTam = new JPanel();

		Font font = new Font("Futura", Font.BOLD, 12);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		String nombre = "Rodrigo de Miguel González";
		String facultad = "Facultad Informatica - UCM";
		nombre = "";
		facultad = "";
		jLabelNombre.setText(nombre);
		jLabelNombre.setFont(font);
		jLabelFacultad.setText(facultad);
		jLabelFacultad.setFont(font);

		jLabelInicio.setText("Inicio:");
		jLabelInicio.setFont(font);
		jLabelFinal.setText("Final:");
		jLabelFinal.setFont(font);

		panelInputsIniFin = new JPanel();
		panelInputsIniFin.setLayout(new BorderLayout());
		panelInsputsSolo = new JPanel();
		panelInputsIniFin.setBorder(BorderFactory.createTitledBorder(""));

		this.setLayout(new BorderLayout());
		panelTablero.add(tableroCeldas);

		panelInputs.setLayout(new FlowLayout());

		panelInputsIni.add(textFieldIniX);
		panelInputsIni.add(textFieldIniY);
		panelInputsIni.setBorder(BorderFactory.createTitledBorder("Inicio"));
		panelInsputsSolo.add(panelInputsIni);

		panelInputsFin.add(textFieldFinX);
		panelInputsFin.add(textFieldFinY);
		panelInputsFin.setBorder(BorderFactory.createTitledBorder("Final"));
		panelInsputsSolo.add(panelInputsFin);

		panelInputsIniFin.add(panelInsputsSolo);
		panelInputsIniFin.add(jButtonComenzar, BorderLayout.SOUTH);

		panelInputsTam.setLayout(new BorderLayout());
		panelTamInputs = new JPanel();

		panelTamInputs.add(textFieldAnchuraTablero);
		panelTamInputs.add(textFieldAlturaTablero);
		panelInputsTam.add(panelTamInputs);
		
		stringTamañoTiempoTablero = "Tablero[ "+ anchuraTablero + "x" + alturaTablero + " ]";
		
		panelInputsTam.setBorder(BorderFactory.createTitledBorder(stringTamañoTiempoTablero));
		panelInputsTam.add(jButtonRedimensionar, BorderLayout.SOUTH);

		panelInputs.add(panelInputsIniFin);

		panelInputs.add(panelInputsTam);

		panelInputs.add(jButtonReiniciar);

		this.add(tableroCeldas, BorderLayout.CENTER);
		this.add(panelInputs, BorderLayout.SOUTH);

		/////////////////////////////////////////////////////////////////////////////

		setSize(1000, 700);
		setLocationRelativeTo(this);

		// pack();
		textFieldIniX.requestFocus();
	}

	
	/** Metodo que pinta el camino de celdas que ha resuleto Negocio
	 * 
	 * @param caminoMasCorto el camino más corto
	 */
	protected void pintarCaminoCeldas(final Camino caminoMasCorto) {
		new Thread(new Runnable() {

			public void run() {
				if (caminoMasCorto == null) {
					JOptionPane.showMessageDialog(null,
							"No existe un camino posible");
					restaurarVentana();
				} // no hay camino
				else
					for (int x = 0; x < mapaLogico.getAnchuraMapaLogico(); x++) {

						for (int y = 0; y < mapaLogico.getAlturaMapaLogico(); y++) {
							nodo = mapaLogico.getNodo(x, y);

							if (caminoMasCorto.contains(nodo.getX(),
									nodo.getY())) {
								try {
									// Retardo para pintar celdas
									Thread.sleep(120);

									if (x < mapaLogico.getAnchuraMapaLogico()) {
										tableroCeldas.pintarCelda(nodo.getX(),
												nodo.getY());
									}
								} catch (InterruptedException e) {
								}

							}
						}

					}
				int xFinal = Integer.parseInt(textFieldFinX.getText());
				int yFinal = Integer.parseInt(textFieldFinY.getText());
				tableroCeldas.pintarFinal(xFinal, yFinal);
			}

		}).start();
	}

	protected void restaurarVentana() {
		dispose();
		new VentanaPrincipal(anchuraTablero, alturaTablero).pintarse();
	}

	public void pintarse() {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});

	}

	public void actionPerformed(ActionEvent arg0) {
		Celda fuente = (Celda) arg0.getSource(); // el que generó el evento
		tableroCeldas.pintarObstaculo(fuente.getFila(), fuente.getColumna());
		obstacleMap[fuente.getFila()][fuente.getColumna()] = 1;
	}

	
	/** Action Listener común para lso elementos de la ventana
	 * 
	 * @author Rodrigo de Miguel
	 *
	 */
	public class ALElementosVentana implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// Acciones boton Comenzar
			if (event.getSource() == jButtonComenzar) {
				try {

					int xInicio = Integer.parseInt(textFieldIniX.getText());
					int yInicio = Integer.parseInt(textFieldIniY.getText());

					int xFinal = Integer.parseInt(textFieldFinX.getText());
					int yFinal = Integer.parseInt(textFieldFinY.getText());

					if (xInicio > anchuraTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor X Inicio fuera de rango");
					else if (yInicio > alturaTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor Y Inicio fuera de rango");
					else if (xFinal > anchuraTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor X Final fuera de rango");
					else if (yFinal > alturaTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor Y Final fuera de rango");
					else {
						
						Cronometro crono = new Cronometro();
						crono.start();
						tableroCeldas.inicializarComienzoFin(xInicio, yInicio,
								xFinal, yFinal);
						// Inicializamos el mapa.
						mapaLogico = new Mapa(anchuraTablero, alturaTablero,
								obstacleMap);
						// Inicializamos la heurística.
						interfazHeuristic = new CalcularDistanciaLineaRecta();
						// Inicializamos el algoritmo
						pathFinder = new AEstrella(mapaLogico,
								interfazHeuristic);
						// Calculamos el camino más corto.
						pathFinder.calcularCaminoMasCorto(xInicio, yInicio,
								xFinal, yFinal);

						Camino caminoMasCorto = pathFinder.getCaminoMasCorto();
						crono.stop();
						
						
						
						
						panelInputsTam.setBorder(BorderFactory.createTitledBorder(stringTamañoTiempoTablero.concat(" - " +crono.getElapsedTime() + " ms")));
						pintarCaminoCeldas(caminoMasCorto);
						
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"Introduzca un número valido");
				}
			}

			if (event.getSource() == jButtonRedimensionar) {
				try {

					int anchura = Integer.parseInt(textFieldAnchuraTablero
							.getText());
					int altura = Integer.parseInt(textFieldAlturaTablero
							.getText());

					VentanaPrincipal.this.alturaTablero = altura;
					VentanaPrincipal.this.anchuraTablero = anchura;
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"Introduzca un número valido");
				}

				VentanaPrincipal.this.restaurarVentana();
			}

			if (event.getSource() == jButtonReiniciar) {
				VentanaPrincipal.this.restaurarVentana();
			}

		}
	}

	/** Key Listener común para lso elementos de la ventana
	 * 
	 * @author Rodrigo de Miguel
	 *
	 */
	public class KLElementosVentana implements KeyListener {

		public void keyPressed(KeyEvent arg0) {

			if (arg0.getKeyCode() == 10) {

				if (jButtonComenzar.isFocusOwner()) {
					jButtonComenzar.doClick();
					jButtonReiniciar.requestFocus();
				}

				if (jButtonRedimensionar.isFocusOwner()) {
					jButtonRedimensionar.doClick();
				}
				
				if (jButtonReiniciar.isFocusOwner()) {
					jButtonReiniciar.doClick();
				}
			}

		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	
	/** Focus Listener común para lso elementos de la ventana
	 * 
	 * @author Rodrigo de Miguel
	 *
	 */
	public class FLElementosVentana implements FocusListener {

		public void focusGained(FocusEvent arg0) {

		}

		public void focusLost(FocusEvent arg0) {

			


			if (arg0.getSource() == textFieldIniY) {
				try {

					int xInicio = Integer.parseInt(textFieldIniX.getText());
					int yInicio = Integer.parseInt(textFieldIniY.getText());

					
					if (xInicio > anchuraTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor X Inicio fuera de rango");
					else if (yInicio > alturaTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor Y Inicio fuera de rango");
					else {

						tableroCeldas.getCelda(xInicio, yInicio).setColorMeta();

					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"Introduzca un número valido");
				}
			}

			if (arg0.getSource() == textFieldFinY) {
				try {

					int xFinal = Integer.parseInt(textFieldFinX.getText());
					int yFinal = Integer.parseInt(textFieldFinY.getText());
					
					if (xFinal > anchuraTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor X Final fuera de rango");
					else if (yFinal > alturaTablero - 1)
						JOptionPane.showMessageDialog(null,
								"Coor Y Final fuera de rango");
					else {

						tableroCeldas.getCelda(xFinal, yFinal).setColorFinal();

					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"Introduzca un número valido");
				}
			}
		}
	}
}
