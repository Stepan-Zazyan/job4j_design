package ru.job4j.serialization.xml;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

public class MainJAXB {
    public static void main(String[] args) throws JAXBException {
       Animal animal = new Animal(true, 100, "tiger",
               new Insects(), new String[] {"dog", "cat"});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Animal.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(animal, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Animal result = (Animal) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
@XmlRootElement(name = "animal")
@XmlAccessorType(XmlAccessType.FIELD)
class Animal {
    @XmlAttribute
    private boolean predator;
    @XmlAttribute
    private int speed;
    @XmlAttribute
    private String name;
    private Insects insect;
    private String[] pride;

    public Animal() {

    }

    public Animal(boolean predator, int speed, String name, Insects insect, String[] pride) {
        this.predator = predator;
        this.speed = speed;
        this.name = name;
        this.insect = insect;
        this.pride = pride;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "predator=" + predator
                + ", speed=" + speed
                + ", name='" + name + '\''
                + ", insect=" + insect
                + ", pride=" + Arrays.toString(pride)
                + '}';
    }
}

@XmlRootElement(name = "insects")
class Insects {
    @XmlAttribute
    private int size;
    @XmlAttribute
    private String name;
}
