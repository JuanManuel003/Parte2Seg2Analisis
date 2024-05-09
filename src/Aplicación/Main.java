package Aplicación;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;

import Algoritmos.Americano;

public class Main {

	public static void main(String[] args) {

		int opcion = 0;
		int[] arreglo1 = null, arreglo2 = null;
		Americano algoritmosAmericanos = new Americano();
		long startTimeNano, endTimeNano, elapsedTimeNano;


		try {
			//caso 1
			Persistencia cargarArray1 = Persistencia.fromXML("array1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("array2.xml");
			arreglo2 = cargarArray2.getArray();

			ArrayList<Integer> array1;

			int tamArr1 = arreglo1.length;
			int tamArr2 = arreglo2.length;
			int[] resultado;


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		do{
			opcion = Integer.parseInt(JOptionPane.showInputDialog("Algoritmos: \n\n"
														+ "1. Americano iterativo \n"
														+ "2. Americano recursivo \n"
														+ "3. Americano iterativo dinamico \n"
														+ "4. Americano recursivo dinamico \n"
														+ "5. salir"));

			switch (opcion) {
			case 1:
				algoritmosAmericanos.ResultadoAmericanoIterativo(arreglo1, arreglo2);
		        break;

			case 2:
				algoritmosAmericanos.ResultadoAmericanoRecursivo(arreglo1, arreglo2);
				break;

			case 3:
				algoritmosAmericanos.ResultadoAmericanoIterativoDinamico(arreglo1, arreglo2);
				break;

			case 4:
				algoritmosAmericanos.ResultadoAmericanoRecursivoDinamico(arreglo1, arreglo2);
				break;

			case 6:
				int[] resultado = americano(arreglo1, arreglo2);
				System.out.println("El algoritmo del class");
				algoritmosAmericanos.imprimirArreglo(resultado);
				break;

			}
		}while(opcion!=5);


	}

	static long medirTiempo(Runnable metodo) {
        long inicio = System.nanoTime();
        metodo.run();
        long fin = System.nanoTime();
        return fin - inicio;
    }

	public static int[] americano(int arreglo1[], int arreglo2[]) {

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

}
