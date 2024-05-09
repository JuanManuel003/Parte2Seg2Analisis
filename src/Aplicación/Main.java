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
			//Caso 1
			/*Persistencia cargarArray1 = Persistencia.fromXML("Casos de prueba/CasoPrueba1Arreglo1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("Casos de prueba/CasoPrueba1Arreglo2.xml");
			arreglo2 = cargarArray2.getArray();*/

			//Caso 2
			/*Persistencia cargarArray1 = Persistencia.fromXML("Casos de prueba/CasoPrueba2Arreglo1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("Casos de prueba/CasoPrueba2Arreglo2.xml");
			arreglo2 = cargarArray2.getArray();*/

			//Caso 3
			Persistencia cargarArray1 = Persistencia.fromXML("Casos de prueba/CasoPrueba3Arreglo1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("Casos de prueba/CasoPrueba3Arreglo2.xml");
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
			}
		}while(opcion!=5);

	}

}
