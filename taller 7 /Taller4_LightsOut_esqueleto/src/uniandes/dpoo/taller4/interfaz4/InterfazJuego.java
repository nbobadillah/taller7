package uniandes.dpoo.taller4.interfaz4;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.Top10;
import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import java.io.File;
import java.util.Collection;

@SuppressWarnings("serial")
public class InterfazJuego extends JFrame {
	private File archivo;
	private Tablero tablero;
	private Top10 top10;
	private int tam = 5;
	private int dificultad = 5;
	private String jugador = "";
	private PanelSuperior pSuperior;
	private PanelCentroIzquierda pTablero;
	private PanelCentroDerecha pJuego;
	private PanelInferior pInferior;
	
	public InterfazJuego() {
		this.archivo = new File("data/top10.csv");
		this.tablero = new Tablero(tam);
		this.tablero.desordenar(dificultad);
		this.tablero.salvar_tablero();
		this.top10 = new Top10();
		this.top10.cargarRecords(this.archivo);
		this.pSuperior= new PanelSuperior(this);
		this.pTablero = new PanelCentroIzquierda(this, tam, tablero.darTablero());
		this.pJuego = new PanelCentroDerecha(this);
		this.pInferior = new PanelInferior(tablero.darJugadas());
		
		setLayout(new BorderLayout());
		add(this.pSuperior, BorderLayout.NORTH);
		add(this.pTablero, BorderLayout.CENTER);
		add(this.pJuego, BorderLayout.EAST);
		add(this.pInferior, BorderLayout.SOUTH);
		
		setTitle("Lights Out");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setSize(800, 600);
		
		// Esto se usa para que al cerrar la ventana se salven los resultados
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				salvarTop10();
			}
		});
		
	}
	
	public static void main(String[] args) {
		new InterfazJuego();
	}
	
	public void salvarTop10() {
		try {
			top10.salvarRecords(this.archivo);
		} catch (Exception e) {
			System.out.println("Ocurrio un error al salvar el Top 10");
			e.printStackTrace();
		}
	}

	public void jugar(int i, int j) {
		tablero.jugar(i,  j);
		pTablero.actualizar(tablero.darTablero());
		pInferior.setJugadas(tablero.darJugadas());
		if(tablero.tableroIluminado()) {
			int puntaje = tablero.calcularPuntaje();
			String mensaje = "";
			if(top10.esTop10(puntaje)) {
				jugador = pInferior.getJugador();
				top10.agregarRegistro(jugador, puntaje);
				mensaje = "\nEres parte del Top 10!";
			}
			
			int respuesta = JOptionPane.showConfirmDialog(this, "Obtuviste " + puntaje + " puntos!" + mensaje + "\nDesea iniciar un nuevo juego?", "Fin del juego", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
			    nuevo();
			} else {
			    reiniciar();
			}
		}
	}
	
	public void nuevo() {
		this.tam = pSuperior.getTamanio();
		this.dificultad = pSuperior.getDificultad();
		this.tablero = new Tablero(tam);
		this.tablero.desordenar(dificultad);
		this.tablero.salvar_tablero();
		pInferior.setJugadas(tablero.darJugadas());
		remove(this.pTablero);
		this.pTablero = new PanelCentroIzquierda(this, tam, tablero.darTablero());
		add(this.pTablero, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	public void reiniciar() {
		this.tablero.reiniciar();
		pInferior.setJugadas(tablero.darJugadas());
		pTablero.actualizar(tablero.darTablero());
	}
	
	public void cambiarJugador() {
		String mensaje;
		jugador = pInferior.getJugador();
		if (jugador.equals("")) {
			mensaje = "Bienvenido jugador!"; 
		} else {
			mensaje = "Bienvenido jugador " + jugador + "!"; 
		}
		JOptionPane.showMessageDialog(this, mensaje, "Cambiar jugador", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public Collection<RegistroTop10> darRegistros() {
		return this.top10.darRegistros();
	}
	
}
