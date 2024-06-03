package uniandes.dpoo.taller4.interfaz4;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelInferior extends JPanel {
	private JLabel jugadas;
	private JTextField jugador;
	
	public PanelInferior(int numJugadas) {
		this.jugadas = crearLabel(String.valueOf(numJugadas));
		this.jugador = new JTextField("");
		
		setLayout(new GridLayout(1,4));
		setBackground(Color.GRAY);
		
		add(crearLabel("Jugadas:"));
		add(jugadas);
		add(crearLabel("Jugador:"));
		add(jugador);
	}
	
	public void setJugadas(int cantidad) {
		jugadas.setText(String.valueOf(cantidad));
	}
	
	public String getJugador() {
		return jugador.getText();
	}
	
	public JLabel crearLabel(String nombre) {
		JLabel label = new JLabel(nombre);
		label.setFont(new Font("Times", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label;
	}
}