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
		String caso = "";
		Americano algoritmosAmericanos = new Americano();


		try {
			//Caso 1
			/*Persistencia cargarArray1 = Persistencia.fromXML("Casos de prueba/CasoPrueba1Arreglo1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("Casos de prueba/CasoPrueba1Arreglo2.xml");
			arreglo2 = cargarArray2.getArray();
			caso = "Tiempos de ejecución/tiemposCaso1.xml";*/

			//Caso 2
			/*Persistencia cargarArray1 = Persistencia.fromXML("Casos de prueba/CasoPrueba2Arreglo1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("Casos de prueba/CasoPrueba2Arreglo2.xml");
			arreglo2 = cargarArray2.getArray();
			caso = "Tiempos de ejecución/tiemposCaso2.xml";*/

			//Caso 3
			Persistencia cargarArray1 = Persistencia.fromXML("Casos de prueba/CasoPrueba3Arreglo1.xml");
			arreglo1 = cargarArray1.getArray();
			Persistencia cargarArray2 = Persistencia.fromXML("Casos de prueba/CasoPrueba3Arreglo2.xml");
			arreglo2 = cargarArray2.getArray();
			caso = "Tiempos de ejecución/tiemposCaso3.xml";

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
				algoritmosAmericanos.ResultadoAmericanoIterativo(arreglo1, arreglo2, caso);
		        break;

			case 2:
				algoritmosAmericanos.ResultadoAmericanoRecursivo(arreglo1, arreglo2, caso);
				break;

			case 3:
				algoritmosAmericanos.ResultadoAmericanoIterativoDinamico(arreglo1, arreglo2, caso);
				break;

			case 4:
				algoritmosAmericanos.ResultadoAmericanoRecursivoDinamico(arreglo1, arreglo2, caso);
				break;
			}
		}while(opcion!=5);

	}

}
