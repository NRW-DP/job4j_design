package ru.job4j.serialization.xml;

import java.util.Arrays;

public class EmployeeDataXml {
    private final boolean isActive;
    private final int employeeId;
    private final String fullName;
    private final AddressXml address;
    private final String[] skills;

    public EmployeeDataXml(boolean isActive, int employeeId, String fullName, AddressXml address, String[] skills) {
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.address = address;
        this.skills = skills;
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
    class AddressXml {
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String postalCode;

    public AddressXml(String streetAddress, String city, String state, String postalCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }
}