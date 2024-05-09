package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;

public class Americano {

	private long startTimeNano, endTimeNano, elapsedTimeNano;

	// ----------------------------------------------------Resultados--------------------------------------------------

	public void ResultadoAmericanoIterativo(int[] arreglo1, int[] arreglo2) {

        //calcular el tiempo que tarda el algoritmo en ordenar el arreglo
		startTimeNano = System.nanoTime();
		int[] resultado = AmericanoIterativo(arreglo1, arreglo2);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano iterativo ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");

		imprimirArreglo(resultado);
	}

	public void ResultadoAmericanoRecursivo(int[] arreglo1, int[] arreglo2) {
		int tamArr1 = arreglo1.length;
		int tamArr2 = arreglo2.length;
		int[] arreglo3 = new int[tamArr1 + tamArr2];
		startTimeNano = System.nanoTime();
		int[] resultado = americanoRecursivo(arreglo1, arreglo2, arreglo3, tamArr1, tamArr2, 0);
		endTimeNano=System.nanoTime();
		elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("Tiempo que tarda el algoritmo Americano iterativo ordenar el arreglo es: "+elapsedTimeNano+" nanosegundos");

		imprimirArreglo(resultado);
	}

	public void ResultadoAmericanoIterativoDinamico(int[] arreglo1, int[] arreglo2) {
		ArrayList<Integer> resultado = new ArrayList<>();
		// Esta linea se hace para llenar el arraylist con 0's de acuerdo al
		// tama�o
		resultado = llenarArray(resultado, arreglo1.length + arreglo2.length);
		resultado = AmericanoIterativoDinamico(arreglo1, arreglo2, resultado);
		imprimirArray(resultado);
	}

	public void ResultadoAmericanoRecursivoDinamico(int[] arreglo1, int[] arreglo2) {
		int i = arreglo1.length;
		int j = arreglo2.length;
		int tama�oResultado = i+j;
	    ArrayList<Integer> resultado = new ArrayList<>(Collections.nCopies(tama�oResultado, 0));
		resultado = AmericanoRecursivoDinamico(arreglo1, arreglo2, resultado, i, j, 0);
		imprimirArray(resultado);
	}

	// ----------------------------------------------------Algoritmos--------------------------------------------------

	public int[] AmericanoIterativo(int[] arreglo1, int[] arreglo2) {
		int resultado[] = new int[arreglo1.length + arreglo2.length]; // Aqui
																		// declaramos
																		// y
																		// asignamos
																		// el
																		// tama�o
																		// al
																		// arreglo
																		// que
																		// nos
																		// da el
																		// resultado
																		// de la
																		// multiplicaci�n
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
				// posici�n
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
			// posici�n
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
			// restableciendo el tama�o de j
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
				// posici�n
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
			// Aqu� asignamos la multiplicaci�n de los dos valores de la
			// posici�n
			mult = arreglo1[i - 1] * arreglo2[j - 1];
			// Aqu� sumamos el resultado en la posici�n indicada, el resultado
			// de multiplicaci�n y el acarreo en caso de que haya
			result = mult + resultado.get(i + j - 1) + acarreo;
			// Aqu� separamos el primer d�gito
			resultado.set(i + j - 1, result % 10);
			// Aqu� separamos el segundo d�gito en caso de que haya
			acarreo = result / 10;

			// Este condicional se utiliza en caso de que un n�mero tenga
			// acarreo y ya haya llegado a su fin, con el fin de tenerlo en
			// cuenta
			if (j == 1 && acarreo != 0) {
				resultado.add(i + j - 2, acarreo);
				acarreo = 0;
			}

			// Se llama recursivamente el m�todo disminuyendo la j
			return AmericanoRecursivoDinamico(arreglo1, arreglo2, resultado, i, j - 1, acarreo);
		} else {
			// Se llama recursivamente el m�todo disminuyendo la i y
			// restableciendo el tama�o de j
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
