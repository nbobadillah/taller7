package uniandes.dpoo.taller4.interfaz4;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import uniandes.dpoo.taller4.modelo.RegistroTop10;

@SuppressWarnings("serial")
public class Top10Interfaz extends JDialog  {
	private JList<JPanel> lista;
	private JScrollPane scroll;
	
	public Top10Interfaz(Collection<RegistroTop10> regis) {
		this.lista = new JList<JPanel>(crearModelo(regis));
		this.lista.setCellRenderer(new ListCellRenderer<JPanel>() {
		    public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel panel, int index, boolean isSelected, boolean cellHasFocus) {
		    	if (index != 0  && (cellHasFocus || isSelected)) {
		    		 panel.setBackground(new Color(129, 195, 255));
		    	} else if (index != 0){
		    		panel.setBackground(list.getBackground());
		    	}
		    	return panel;
		    }
		});
		this.scroll = new JScrollPane(this.lista);
		add(scroll);
		
		pack();
		setTitle("Top 10");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public DefaultListModel<JPanel> crearModelo(Collection<RegistroTop10> regis) {
		JPanel panel;
		JLabel numero, jugador, puntaje;
		RegistroTop10 registro;
		RegistroTop10[] registros = regis.toArray(new RegistroTop10[regis.size()]);
		DefaultListModel<JPanel> modelo = new DefaultListModel<JPanel>();
		for(int i = -1; i < registros.length; i++) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			if (i == -1) {
				panel.setBackground(new Color (100,135,255));
				numero = crearLabel("#");
				numero.setForeground(Color.WHITE);
				jugador = crearLabel("Nombre");
				jugador.setForeground(Color.WHITE);
				puntaje = crearLabel("Puntos");
				puntaje.setForeground(Color.WHITE);
			} else {
				registro = registros[i];
				numero = crearLabel(String.valueOf(i+1));
				jugador = crearLabel(registro.darNombre());
				puntaje = crearLabel(String.valueOf(registro.darPuntos()));
			}
			panel.add(numero);
			panel.add(jugador);
			panel.add(puntaje);
			modelo.addElement(panel);
		}
		return modelo;
	}
	
	public JLabel crearLabel(String nombre) {
		JLabel label = new JLabel(nombre);
		label.setFont(new Font("Times", Font.BOLD, 16));
		return label;
	}
}
