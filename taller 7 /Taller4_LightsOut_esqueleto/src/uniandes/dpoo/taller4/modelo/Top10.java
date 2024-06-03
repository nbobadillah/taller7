package uniandes.dpoo.taller4.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Top10
{

	private PriorityQueue<RegistroTop10> registros;


	private int peorPuntaje;
	public Top10()
	{
		registros = new PriorityQueue<>(new Comparator<RegistroTop10>()
		{
			@Override
			public int compare(RegistroTop10 o1, RegistroTop10 o2)
			{
				int p1 = o1.darPuntos();
				int p2 = o2.darPuntos();
				return p1 > p2 ? -1 : (p1 == p2 ? 0 : 1);
			}
		});

		for (int i = 0; i < 10; i++)
		{
			RegistroTop10 r = new RegistroTop10("AAA", 0);
			registros.add(r);
		}
		peorPuntaje = 0;
	}
	public boolean esTop10(int puntaje)
	{
		return puntaje > peorPuntaje;
	}
	public void agregarRegistro(String nombre, int puntaje)
	{
		RegistroTop10 nuevoRegistro = new RegistroTop10(nombre, puntaje);
		registros.add(nuevoRegistro);
		if (registros.size() > 10)
		{
			ArrayList<RegistroTop10> temp = new ArrayList<>();
			for (int i = 0; i < 10; i++)
			{
				temp.add(registros.poll());
			}
			peorPuntaje = temp.get(9).darPuntos();

			registros.clear();
			registros.addAll(temp);
		}
	}

	public Collection<RegistroTop10> darRegistros()
	{
		return registros;
	}

	public void salvarRecords(File archivo) throws FileNotFoundException, UnsupportedEncodingException
	{
		OutputStream os = new FileOutputStream(archivo);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		Collection<RegistroTop10> temp = darRegistros();
		for (RegistroTop10 reg : temp)
		{
			String nombre = reg.darNombre();
			int puntos = reg.darPuntos();
			pw.println(nombre + ";" + puntos);
		}
		pw.close();
	}

	public void cargarRecords(File archivo)
	{
		if (archivo.exists())
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				String linea = br.readLine();
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String nombre = partes[0];
					int puntos = Integer.parseInt(partes[1]);
					agregarRegistro(nombre, puntos);

					linea = br.readLine();
				}
				br.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontr� el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un n�mero se pudo convertir a int ...");
				e.printStackTrace();
			}

		}
	}
}
