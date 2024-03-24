package ru.job4j.serialization.xml;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MainEmployeeDataXml {
    public static void main(String[] args) throws IOException, JAXBException {
        AddressXml address = new AddressXml("123 Main St", "CityVille", "State", "12345");
        String[] skills = {"Java", "SQL", "HTML", "CSS"};
        EmployeeDataXml employee = new EmployeeDataXml(true, 1001, "John Doe", address, skills);

        JAXBContext context = JAXBContext.newInstance(EmployeeDataXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            /* Serialize */
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* De-serializer */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* De-serialization process  */
            EmployeeDataXml result = (EmployeeDataXml) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
