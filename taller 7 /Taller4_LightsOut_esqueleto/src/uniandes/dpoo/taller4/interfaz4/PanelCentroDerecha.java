package uniandes.dpoo.taller4.interfaz4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCentroDerecha extends JPanel {
	private InterfazJuego ventana;
	private Top10Interfaz dialog;
	private JButton nuevo;
	private JButton reiniciar;
	private JButton top;
	private JButton cambiar;
	
	public PanelCentroDerecha(InterfazJuego ventana) {
		this.ventana = ventana;
		this.nuevo = crearBoton("NUEVO");
		this.reiniciar = crearBoton("REINICIAR");
		this.top = crearBoton("TOP 10");
		this.cambiar = crearBoton("CAMBIAR JUGADOR");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setAlignmentY(Component.CENTER_ALIGNMENT);
		
	    Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, 50);
	    this.nuevo.setMaximumSize(maxButtonSize);
	    this.reiniciar.setMaximumSize(maxButtonSize);
	    this.top.setMaximumSize(maxButtonSize);
	    this.cambiar.setMaximumSize(maxButtonSize);
		
		add(Box.createVerticalGlue());
		add(this.nuevo);
		add(Box.createVerticalStrut(10));
		add(this.reiniciar);
		add(Box.createVerticalStrut(10));
		add(this.top);
		add(Box.createVerticalStrut(10));
		add(this.cambiar);
		add(Box.createVerticalGlue());
		
	    nuevo.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	ventana.nuevo();
	        }
	    });
	    
	    reiniciar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	ventana.reiniciar();
	        }
	    });
	    
	    top.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
	        	dialog = new Top10Interfaz(ventana.darRegistros());
	        }
	    });
	    
	    cambiar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	ventana.cambiarJugador();
	        }
	    });
	}
	
	public JButton crearBoton(String nombre) {
		JButton boton = new JButton(nombre);
		boton.setForeground(Color.WHITE);
		boton.setBackground(new Color (100,135,255));
		boton.setFont(new Font("Times", Font.BOLD, 16));
		return boton;
	}
}