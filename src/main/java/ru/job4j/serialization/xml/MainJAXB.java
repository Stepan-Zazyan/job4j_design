package ru.job4j.serialization.xml;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainJAXB {
    public static void main(String[] args) throws JAXBException, JsonProcessingException {
       Animal animal = new Animal(true, 100, "tiger",
               new Insects(2, "bee"), new String[] {"dog", "cat"});
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
            System.out.println("deserialized animal object= " + result);
        }

        JSONObject jsonInsects = new JSONObject("{size:2,name:bee}");
        System.out.println("jsonInsects= " + jsonInsects);
        List<String> list = new ArrayList<>();
        list.add("dog");
        list.add("cat");
        JSONArray jsonPride2 = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("predator", animal.isPredator());
        jsonObject.put("speed", animal.getSpeed());
        jsonObject.put("name", animal.getName());
        jsonObject.put("insect", animal.getInsect());
        jsonObject.put("pride", animal.getPride());
        /* Выведем результат в консоль */
        System.out.println("jsonObject= " + jsonObject.toString());
        /* Преобразуем объект person в json-строку */
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(animal);
        System.out.println(jsonString);
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
    @XmlElement
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

    public Animal(boolean b, int i, String tiger) {
        this.predator = b;
        this.speed = i;
        this.name = tiger;
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

    public boolean isPredator() {
        return predator;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJSON() {
        JSONObject jsonAnimal = new JSONObject();
        jsonAnimal.put("predator", predator);
        jsonAnimal.put("speed", speed);
        jsonAnimal.put("name", name);
        jsonAnimal.put("insect", insect.toJSON());
        jsonAnimal.put("pride", new JSONArray(pride));
        return jsonAnimal;
    }

    public Insects getInsect() {
        return insect;
    }

    public String[] getPride() {
        return pride;
    }
}

@XmlRootElement(name = "insects")
@XmlAccessorType(XmlAccessType.FIELD)
class Insects {
    @XmlAttribute
    private int size;
    @XmlAttribute
    private String name;

    public Insects() {

    }
    public Insects(int size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Insects{"
                + "size=" + size
                + ", name='" + name + '\''
                + '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonInsects = new JSONObject();
        jsonInsects.put("size", size);
        jsonInsects.put("name", name);
        return jsonInsects;
    }
}
