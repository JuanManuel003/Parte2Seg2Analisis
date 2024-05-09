package Algoritmos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.JAXBException;

import Aplicación.Persistencia;

public class Americano {

	private long startTimeNano, endTimeNano, elapsedTimeNano;
	private  ArrayList<Long> tiempos = new ArrayList<Long>();

	Persistencia TEXML = new Persistencia(tiempos);

	// ----------------------------------------------------Resultados--------------------------------------------------

	public void ResultadoAmericanoIterativo(int[] arreglo1, int[] arreglo2) {

        //calcular el tiempo que tarda el algoritmo en ordenar el arreglo
		startTimeNano = System.nanoTime();
		int[] resultado = AmericanoIterativo(arreglo1, arreglo2);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano iterativo ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");

		imprimirArreglo(resultado);
		tiempos.add(elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 ArrayList<Long> loadedArray = newArray.getTiempos();
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
		tiempos.add(elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 ArrayList<Long> loadedArray = newArray.getTiempos();
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

		tiempos.add(elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 ArrayList<Long> loadedArray = newArray.getTiempos();
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
		tiempos.add(elapsedTimeNano);

		try {
			TEXML.toXML("Tiempos de ejecución/tiempos.xml");
			Persistencia newArray = Persistencia.fromXML("Tiempos de ejecución/tiempos.xml");
			 ArrayList<Long> loadedArray = newArray.getTiempos();
            System.out.println(loadedArray);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------Algoritmos--------------------------------------------------

	public int[] AmericanoIterativo(int[] arreglo1, int[] arreglo2) {
		int resultado[] = new int[arreglo1.length + arreglo2.length]; // Aqui
																		// declaramos
																		// y
																		// asignamos
																		// el
																		// tamaño
																		// al
																		// arreglo
																		// que
																		// nos
																		// da el
																		// resultado
																		// de la
																		// multiplicación
		int acarreo = 0; // Esta variable es el acarreo
		int result;
		int mult;

		// Este arreglo se recorre de manera tal que su primer numero sea el de
		// la izquierda
		for (int i = arreglo1.length; i > 0; i--) {
			// Este arreglo se recorre de manera tal que su primer numero sea el
			// de la izquierda
			for (int j = arreglo2.length; j > 0; j--) {
				// Aqui asignamos la multiplicacion de los dos valores de la
				// posición
				mult = arreglo1[i - 1] * arreglo2[j - 1];
				// Aqui sumamos el resultado en la posicion indicada, el
				// resultado de multiplicacion y el acarreo en caso de que haya
				result = mult + resultado[i + j - 1] + acarreo;
				// Aqui separamos el primer digito
				resultado[i + j - 1] = result % 10;
				// Aqui separamos el segundo digito en caso de que haya
				acarreo = result / 10;
				// Este condicional se utiliza en caso de que un numero tenga
				// acarreo y ya haya llegado a su fin, con el fin de tenerlo en
				// cuenta
				if (j == 1 && acarreo != 0) {
					resultado[i + j - 2] = acarreo;
					acarreo = 0;
				}
			}
		}
		return resultado;
	}

	public int[] americanoRecursivo(int arreglo1[], int arreglo2[], int resultado[], int i, int j, int acarreo) {

		int result;
		int mult;
		// Caso base que devuelve el resultado de las recursiones
		if (i <= 0) {
			return resultado;
		}
		// Condicional para iterar recursivamente los arreglos
		if (j > 0) {
			// Aqui asignamos la multiplicacion de los dos valores de la
			// posición
			mult = arreglo1[i - 1] * arreglo2[j - 1];
			// Aqui sumamos el resultado en la posicion indicada, el resultado
			// de multiplicacion y el acarreo en caso de que haya
			result = mult + resultado[i + j - 1] + acarreo;
			// Aqui separamos el primer digito
			resultado[i + j - 1] = result % 10;
			// Aqui separamos el segundo digito en caso de que haya
			acarreo = result / 10;
			// Este condicional se utiliza en caso de que un numero tenga
			// acarreo y ya haya llegado a su fin, con el fin de tenerlo en
			// cuenta
			if (j == 1 && acarreo != 0) {
				resultado[i + j - 2] = acarreo;
				acarreo = 0;
			}
			// se llama recursivamente el metodo disminuyendo la j
			return americanoRecursivo(arreglo1, arreglo2, resultado, i, --j, acarreo);

		} else {
			// Se llama recursivamente el metodo disminuyendo la i y
			// restableciendo el tamaño de j
			return americanoRecursivo(arreglo1, arreglo2, resultado, --i, arreglo2.length, acarreo);
		}
	}

	public ArrayList<Integer> AmericanoIterativoDinamico(int[] arreglo1, int[] arreglo2,
			ArrayList<Integer> resultado) {

		// En esta variable se guarda el acarreo en caso de que haya
		int acarreo = 0;
		int result;
		int mult;

		// Este ciclo se recorre de derecha a izquierda para simular el metodo
		// de multiplicacion americana
		for (int i = arreglo1.length; i > 0; i--) {
			// Este ciclo se recorre de derecha a izquierda para simular el
			// metodo de multiplicacion americana
			for (int j = arreglo2.length; j > 0; j--) {
				// Aqui asignamos la multiplicacion de los dos valores de la
				// posición
				mult = arreglo1[i - 1] * arreglo2[j - 1];
				// Aqui sumamos el resultado en la posicion indicada, el
				// resultado de multiplicacion y el acarreo en caso de que haya
				result = mult + resultado.get(i + j - 1) + acarreo;
				// Aqui separamos el primer digito
				resultado.set(i + j - 1, result % 10);
				// Aqui separamos el segundo digito en caso de que haya
				acarreo = result / 10;
				// Este condicional se utiliza en caso de que un numero tenga
				// acarreo y ya haya llegado a su fin, con el fin de tenerlo en
				// cuenta
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

		// Caso base que devuelve el resultado de las recursiones
		if (i <= 0) {
			return resultado;
		}

		// Condicional para iterar recursivamente los arreglos
		if (j > 0) {
			// Aquí asignamos la multiplicación de los dos valores de la
			// posición
			mult = arreglo1[i - 1] * arreglo2[j - 1];
			// Aquí sumamos el resultado en la posición indicada, el resultado
			// de multiplicación y el acarreo en caso de que haya
			result = mult + resultado.get(i + j - 1) + acarreo;
			// Aquí separamos el primer dígito
			resultado.set(i + j - 1, result % 10);
			// Aquí separamos el segundo dígito en caso de que haya
			acarreo = result / 10;

			// Este condicional se utiliza en caso de que un número tenga
			// acarreo y ya haya llegado a su fin, con el fin de tenerlo en
			// cuenta
			if (j == 1 && acarreo != 0) {
				resultado.add(i + j - 2, acarreo);
				acarreo = 0;
			}

			// Se llama recursivamente el método disminuyendo la j
			return AmericanoRecursivoDinamico(arreglo1, arreglo2, resultado, i, j - 1, acarreo);
		} else {
			// Se llama recursivamente el método disminuyendo la i y
			// restableciendo el tamaño de j
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

	static long medirTiempo(Runnable metodo) {
        long inicio = System.nanoTime();
        metodo.run();
        long fin = System.nanoTime();
        return fin - inicio;
    }
}
