package ru.job4j.serialization.json;

import com.google.gson.Gson;
import java.util.Arrays;
    /**
    * Represents employee data including basic information and skills.
    */
public class EmployeeData {
    private final boolean isActive;
    private final int employeeId;
    private final String fullName;
    private final Address address;
    private final String[] skills;

    public EmployeeData(boolean isActive, int employeeId, String fullName, Address address, String[] skills) {
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.address = address;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "EmployeeData{"
                + "isActive=" + isActive
                + ", employeeId=" + employeeId
                + ", fullName='" + fullName + '\''
                + ", address=" + address
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }

        public boolean isActive() {
            return isActive;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public String getFullName() {
            return fullName;
        }

        public Address getAddress() {
            return address;
        }

        public String[] getSkills() {
            return skills;
        }

        /**
     * Converts the object to its JSON representation.
     *
     * @return JSON representation of the object
     */
    public String fromObjectToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Converts JSON representation back to an EmployeeData object.
     *
     * @param json JSON representation of the object
     * @return EmployeeData object restored from JSON
     */
    public static EmployeeData fromJsonToObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, EmployeeData.class);
    }

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "CityVille", "State", "12345");
        String[] skills = {"Java", "SQL", "HTML", "CSS"};
        EmployeeData employee = new EmployeeData(true, 1001, "John Doe", address, skills);

        String json = employee.fromObjectToJson();
        System.out.println("JSON representation:");
        System.out.println(json);

        EmployeeData restoredEmployee = EmployeeData.fromJsonToObject(json);
        System.out.println("\nRestored object:");
        System.out.println(restoredEmployee.fromObjectToJson());
    }
}
    class Address {
        private final String streetAddress;
        private final String city;
        private final String state;
        private final String postalCode;

        public Address(String streetAddress, String city, String state, String postalCode) {
            this.streetAddress = streetAddress;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getPostalCode() {
            return postalCode;
        }
    }

