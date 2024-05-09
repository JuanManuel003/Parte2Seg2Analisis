package Aplicación;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persistencia {

	private static final Persistencia PersistentArray = null;

	private int[] array;
    private ArrayList<Long> tiempos;

    // Constructor vacío requerido por JAXB
    public Persistencia() {}

    public Persistencia(int[] array) {
        this.array = array;
    }

    public Persistencia( ArrayList<Long> tiempos) {
        this.tiempos = tiempos;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public  ArrayList<Long> getTiempos() {
        return tiempos;
    }

    public void setTiempos( ArrayList<Long> tiempos) {
        this.tiempos = tiempos;
    }

    // Método para serializar el objeto a XML
    public void toXML(String filePath) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Persistencia.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, new FileWriter(filePath));
    }

    // Método estático para deserializar el XML a objeto
    public static Persistencia fromXML(String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Persistencia.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Persistencia) unmarshaller.unmarshal(new FileReader(filePath));
    }

    public static void main(String[] args) {
        int[] Array1 = generarArreglo(10);
        //int[] Array2 = generarArreglo(10);

        try {
            // Serializar el arreglo a XML
            Persistencia persistentArray = new Persistencia(Array1);
            persistentArray.toXML("array1.xml");

            // Deserializar el XML de vuelta al arreglo
            Persistencia newArray = Persistencia.fromXML("array1.xml");
            int[] loadedArray = newArray.getArray();
            for (int element : loadedArray) {
                System.out.println(element);
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] generarArreglo(int tam){
    	int[] arreglo = new int[tam];
    	Random aleatorio = new Random();
    	for(int i=0; i<tam; i++){
    		arreglo[i] = aleatorio.nextInt(10);
    	}
    	return arreglo;
    }



}
