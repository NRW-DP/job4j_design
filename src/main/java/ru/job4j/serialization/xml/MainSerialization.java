package ru.job4j.serialization.xml;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainSerialization {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /* Obtain context for accessing API */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /* Create serializer */
        Marshaller marshaller = context.createMarshaller();
        /* Specify that we need formatting */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Serialize */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* For deserialization, we need to create a deserializer */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* deserialize */
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
