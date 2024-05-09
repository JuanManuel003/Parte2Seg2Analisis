package Algoritmos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import com.sun.javafx.collections.MappingChange.Map;

import Aplicación.Persistencia;

public class Americano {

	private long startTimeNano, endTimeNano, elapsedTimeNano;
    private HashMap<String, Long> tiempos = new HashMap();

	Persistencia TEXML = new Persistencia(tiempos);

	// ----------------------------------------------------Resultados--------------------------------------------------

	public void ResultadoAmericanoIterativo(int[] arreglo1, int[] arreglo2) {

        //calcular el tiempo que tarda el algoritmo en ordenar el arreglo
		startTimeNano = System.nanoTime();
		int[] resultado = AmericanoIterativo(arreglo1, arreglo2);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano recursivo ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");

		imprimirArreglo(resultado);
		tiempos.put("Americano Iterativo", elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 HashMap<String, Long> loadedArray = newArray.getTiempos();
            System.out.println(loadedArray);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ResultadoAmericanoRecursivo(int[] arreglo1, int[] arreglo2) {
		int tamArr1 = arreglo1.length;
		int tamArr2 = arreglo2.length;
		int[] arreglo3 = new int[tamArr1 + tamArr2];
		startTimeNano = System.nanoTime();
		int[] resultado = americanoRecursivo(arreglo1, arreglo2, arreglo3, tamArr1, tamArr2, 0);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano recursivo ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");

		imprimirArreglo(resultado);
		tiempos.put("Americano Recursivo", elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 HashMap<String, Long> loadedArray = newArray.getTiempos();
            System.out.println(loadedArray);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ResultadoAmericanoIterativoDinamico(int[] arreglo1, int[] arreglo2) {
		ArrayList<Integer> resultado = new ArrayList<>();
		// Esta linea se hace para llenar el arraylist con 0's de acuerdo al
		// tamaño
		resultado = llenarArray(resultado, arreglo1.length + arreglo2.length);
		startTimeNano = System.nanoTime();
		resultado = AmericanoIterativoDinamico(arreglo1, arreglo2, resultado);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano iterativo dinamico ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");
		imprimirArray(resultado);
		tiempos.put("Americano Iterativo Dinamico", elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 HashMap<String, Long> loadedArray = newArray.getTiempos();
            System.out.println(loadedArray);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ResultadoAmericanoRecursivoDinamico(int[] arreglo1, int[] arreglo2) {
		int i = arreglo1.length;
		int j = arreglo2.length;
		int tamañoResultado = i+j;
	    ArrayList<Integer> resultado = new ArrayList<>(Collections.nCopies(tamañoResultado, 0));
		startTimeNano = System.nanoTime();
		resultado = AmericanoRecursivoDinamico(arreglo1, arreglo2, resultado, i, j, 0);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano recursivo dinamico ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");

		imprimirArray(resultado);
		tiempos.put("Americano recursivo Dinamico", elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 HashMap<String, Long> loadedArray = newArray.getTiempos();
            System.out.println(loadedArray);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------Algoritmos--------------------------------------------------

	public int[] AmericanoIterativo(int[] arreglo1, int[] arreglo2) {
		int resultado[] = new int[arreglo1.length + arreglo2.length];
		for (int i = arreglo2.length - 1; i >= 0; i--) {

			for (int j = arreglo1.length - 1; j >= 0; j--) {

				resultado[i + j + 1] += arreglo1[j] * arreglo2[i];
				resultado[i + j] += (resultado[i + j + 1] / 10);
				resultado[i + j + 1] %= 10;

			}

		}
		return resultado;
	}

	public int[] americanoRecursivo(int arreglo1[], int arreglo2[], int resultado[], int i, int j, int acarreo) {

		int result;
		int mult;
		if (i <= 0) {
			return resultado;
		}
		if (j > 0) {
			mult = arreglo1[i - 1] * arreglo2[j - 1];
			result = mult + resultado[i + j - 1] + acarreo;
			resultado[i + j - 1] = result % 10;
			acarreo = result / 10;
			if (j == 1 && acarreo != 0) {
				resultado[i + j - 2] = acarreo;
				acarreo = 0;
			}
			return americanoRecursivo(arreglo1, arreglo2, resultado, i, --j, acarreo);

		} else {
			return americanoRecursivo(arreglo1, arreglo2, resultado, --i, arreglo2.length, acarreo);
		}
	}

	public ArrayList<Integer> AmericanoIterativoDinamico(int[] arreglo1, int[] arreglo2,
			ArrayList<Integer> resultado) {

		int acarreo = 0;
		int result;
		int mult;

		for (int i = arreglo1.length; i > 0; i--) {
			for (int j = arreglo2.length; j > 0; j--) {
				mult = arreglo1[i - 1] * arreglo2[j - 1];
				result = mult + resultado.get(i + j - 1) + acarreo;
				resultado.set(i + j - 1, result % 10);
				acarreo = result / 10;
				if (j == 1 && acarreo != 0) {
					resultado.set(i + j - 2, acarreo);
					acarreo = 0;
				}
			}
		}

		return resultado;
	}

	public ArrayList<Integer> AmericanoRecursivoDinamico(int arreglo1[], int arreglo2[],
			ArrayList<Integer> resultado, int i, int j, int acarreo) {
		int result;
		int mult;

		if (i <= 0) {
			return resultado;
		}

		if (j > 0) {
			mult = arreglo1[i - 1] * arreglo2[j - 1];
			result = mult + resultado.get(i + j - 1) + acarreo;
			resultado.set(i + j - 1, result % 10);
			acarreo = result / 10;

			if (j == 1 && acarreo != 0) {
				resultado.add(i + j - 2, acarreo);
				acarreo = 0;
			}

			return AmericanoRecursivoDinamico(arreglo1, arreglo2, resultado, i, j - 1, acarreo);
		} else {
			return AmericanoRecursivoDinamico(arreglo1, arreglo2, resultado, i - 1, arreglo2.length, acarreo);
		}
	}

	// ----------------------------------------------------Adicionales--------------------------------------------------

	public static void imprimirArreglo(int[] arreglo) {
		String arr = "el resultado de la multiplicacion es: ";
		for (int i = 0; i < arreglo.length; i++) {
			arr += "" + arreglo[i];
		}
		System.out.println(arr);
	}

	public static void imprimirArray(ArrayList<Integer> array) {
		String arr = "el resultado de la multiplicacion es: ";
		for (int i = 0; i < array.size(); i++) {
			arr += "" + array.get(i);
		}
		System.out.println(arr);
	}

	public static ArrayList<Integer> llenarArray(ArrayList<Integer> arreglo, int n){

		for(int i=0; i<n;  i++) {

			arreglo.add(0);
		}
		return arreglo;
	}

}
