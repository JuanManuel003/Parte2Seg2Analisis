package Aplicación;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Numeros")
public class GenerarNumeros {

    private List<String> numeros;

    public GenerarNumeros() {
        this.numeros = new ArrayList<>();
    }

    @XmlElement(name = "Numero")
    public List<String> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<String> numeros) {
        this.numeros = numeros;
    }

    public static void main(String[] args) {
        int minDigits = 3; // Mínimo tamaño de los números
        int maxDigits = 7; // Máximo tamaño de los números
        int cantidad = 10; // Cantidad de números a generar

        GenerarNumeros generador = new GenerarNumeros();
        generador.generarYGuardarNumeros(minDigits, maxDigits, cantidad);
    }

    public void generarYGuardarNumeros(int minDigits, int maxDigits, int cantidad) {
        generarNumeros(minDigits, maxDigits, cantidad);
        guardarNumerosXML();
    }

    private void generarNumeros(int minDigits, int maxDigits, int cantidad) {
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            int digits = rand.nextInt(maxDigits - minDigits + 1) + minDigits; // Genera un número aleatorio de dígitos entre minDigits y maxDigits
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < digits; j++) {
                sb.append(rand.nextInt(10)); // Genera un dígito aleatorio del 0 al 9
            }
            numeros.add(sb.toString());
        }
    }

    private void guardarNumerosXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(GenerarNumeros.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Guardar el objeto como XML en un archivo
            marshaller.marshal(this, new FileWriter("numeros.xml"));

            System.out.println("Archivo XML generado correctamente.");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
