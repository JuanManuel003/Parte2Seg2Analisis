package Aplicación;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.javafx.collections.MappingChange.Map;

@XmlRootElement
public class Persistencia {

	private static final Persistencia PersistentArray = null;

	private int[] array;
    private HashMap<String, Long> tiempos;

    // Constructor vacío requerido por JAXB
    public Persistencia() {}

    public Persistencia(int[] array) {
        this.array = array;
    }

    public Persistencia( HashMap<String, Long> tiempos) {
        this.tiempos = tiempos;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public  HashMap<String, Long> getTiempos() {
        return tiempos;
    }

    public void setTiempos( HashMap<String, Long> tiempos) {
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
}
