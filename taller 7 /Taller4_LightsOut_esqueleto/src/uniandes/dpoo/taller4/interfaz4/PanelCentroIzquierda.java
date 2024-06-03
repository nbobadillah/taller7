package uniandes.dpoo.taller4.interfaz4;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class PanelCentroIzquierda extends JPanel implements MouseListener {
	private InterfazJuego ventana;
	private boolean[][] tablero;
	private int tamanio;
	private int altoPanel;
	private int anchoPanel;
	private int gap =10;
	private int altoCasilla;
	private int anchoCasilla;

	
	public PanelCentroIzquierda(InterfazJuego ventana, int tamanio, boolean[][] tablero) {
		this.ventana = ventana;
		this.tamanio = tamanio;
		this.tablero = tablero;
		addMouseListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		int click_x = e.getX();
		int click_y = e.getY();
		int[] casilla = convertirCoordenadasACasilla(click_x, click_y);
		ventana.jugar(casilla[0], casilla[1]);
		repaint();
	}
	
	private int[] convertirCoordenadasACasilla(int x, int y){
		int fila = (int) (y / (altoCasilla + gap));
		int columna = (int) (x / (anchoCasilla + gap));
		if (fila >=  tamanio) {
			fila = tamanio - 1;
		}
		if (columna >=  tamanio) {
			columna = tamanio - 1;
		}
		return new int[] { fila, columna };
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        anchoPanel = getWidth() - (gap * (tamanio + 1));
        altoPanel = getHeight() - (gap * (tamanio + 1));
        anchoCasilla = anchoPanel / tamanio;
        altoCasilla = altoPanel / tamanio;
        int x = gap;
        int y = gap;
        g2.setFont(new Font("Times", Font.PLAIN, 14));
        Image image;
		try {
			image = ImageIO.read(new File("data/luz.png"));
	        for (int row = 0; row < tamanio; row++) {
	            for (int col = 0; col < tamanio; col++) {
	            	if(tablero[row][col]) {
	            		g2.setColor(new Color(255, 215, 3));
	            	} else {
	            		g2.setColor(new Color(101, 100, 91));
	            	}
	                g2.fillRoundRect(x, y, anchoCasilla, altoCasilla, 20, 20);
	                g2.drawImage(image, x, y, (int) (anchoCasilla), (int) (altoCasilla), null);
            		g2.setColor(Color.BLACK);
	                x += anchoCasilla + gap;
	            }
	            y += altoCasilla + gap;
	            x = gap;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void actualizar(boolean[][] tablero) {
		this.tablero = tablero;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
