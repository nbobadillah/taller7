package uniandes.dpoo.taller4.modelo;

/**
 * Esta clase sirve para representar un tablero del juego LightsOut.
 * 
 * El juego gira alrededor de un tablero cuadrado con luces que pueden estar
 * prendidas o apagadas. En cada jugada, el jugador selecciona una luz y esta
 * cambia de estado (de prendida a apagada y viceversa). Lo mismo sucede con las
 * 8 luces vecinas a la luz seleccionada por el jugador.
 *
 * El objetivo del juego es lograr que todas las luces est�n prendidas, en la
 * menor cantidad de jugadas posibles.
 */
public class Tablero
{
	private boolean[][] tablero;

	private boolean[][] tablero_original;
	private int jugadas;

	public Tablero(int tamano)
	{
		tablero = new boolean[tamano][tamano];
		tablero_original = new boolean[tamano][tamano];
		for (int i = 0; i < tablero.length; i++)
			for (int ii = 0; ii < tablero.length; ii++)
			{
				tablero[i][ii] = true;
				tablero_original[i][ii] = true;
			}
		jugadas = 0;
	}
	public void reiniciar()
	{
		for (int i = 0; i < tablero.length; i++)
			for (int ii = 0; ii < tablero.length; ii++)
				tablero[i][ii] = tablero_original[i][ii];
		jugadas = 0;
	}

	public void salvar_tablero()
	{
		for (int i = 0; i < tablero.length; i++)
			for (int ii = 0; ii < tablero.length; ii++)
				tablero_original[i][ii] = tablero[i][ii];
	}

	public void desordenar(int dificultad)
	{
		int temp = jugadas;
		for (int i = 0; i < dificultad; i++)
		{
			int tam = tablero.length;

			int fila = (int) (Math.random() * tam);
			int col = (int) (Math.random() * tam);
			jugar(fila, col);
		}
		salvar_tablero();
		jugadas = temp;
	}

	public int darJugadas()
	{
		return jugadas;
	}
	public boolean[][] darTablero()
	{
		return tablero;
	}
	public void jugar(int fila, int columna)
	{
		int tam = tablero.length;

		for (int df = -1; df < 2; df++)
		{
			for (int dc = -1; dc < 2; dc++)
			{
				int f = fila + df;
				int c = columna + dc;
				if (f >= 0 && f < tam && c >= 0 && c < tam)
				{
					tablero[f][c] = !tablero[f][c];
				}
			}
		}
		jugadas++;
	}
	public boolean tableroIluminado()
	{
		boolean iluminado = true;

		for (int i = 0; i < tablero.length && iluminado; i++)
			for (int ii = 0; ii < tablero.length && iluminado; ii++)
				iluminado = tablero[i][ii];

		return iluminado;
	}
	public int calcularPuntaje()
	{
		int tam = tablero.length;
		int factorDificultad = tam * tam;

		return factorDificultad - jugadas;
	}
}
