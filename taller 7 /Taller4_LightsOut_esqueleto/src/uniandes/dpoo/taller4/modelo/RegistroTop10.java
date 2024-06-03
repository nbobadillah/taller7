package uniandes.dpoo.taller4.modelo;

public class RegistroTop10
{
	private String nombre;
	private int puntos;
	public RegistroTop10(String nombre, int puntos)
	{
		this.nombre = nombre.toUpperCase().replace(";", "*");
		this.puntos = puntos;

		if (this.nombre.length() > 3)
			this.nombre = this.nombre.substring(0, 3);
	}

	public int darPuntos()
	{
		return puntos;
	}

	public String darNombre()
	{
		return nombre;
	}

	@Override
	public String toString()
	{
		return nombre + " ..... " + puntos;
	}
}
