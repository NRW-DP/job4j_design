package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDataXml {
    private  boolean isActive;
    private  int employeeId;
    private  String fullName;
    private  AddressXml address;
    private  String[] skills;

    public EmployeeDataXml(boolean isActive, int employeeId, String fullName, AddressXml address, String[] skills) {
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.address = address;
        this.skills = skills;
    }

    public EmployeeDataXml() {
    }

    @Override
    public String toString() {
        return "EmployeeDataXml{"
                + "isActive=" + isActive
                + ", employeeId=" + employeeId
                + ", fullName='" + fullName + '\''
                + ", address=" + address
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }

    public static void main(String[] args) {
        AddressXml address = new AddressXml("123 Main St", "CityVille", "State", "12345");
        String[] skills = {"Java", "SQL", "HTML", "CSS"};
        EmployeeDataXml employee = new EmployeeDataXml(true, 1001, "John Doe", address, skills);

    }
}
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
    class AddressXml {
    private  String streetAddress;
    private  String city;
    private  String state;
    private  String postalCode;

    public AddressXml() {
    }
    public AddressXml(String streetAddress, String city, String state, String postalCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

}