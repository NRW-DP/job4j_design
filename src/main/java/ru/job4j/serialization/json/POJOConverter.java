package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Convert POJO object to JSONObject and JSON string using JSON-Java library (org.json)
 */
public class POJOConverter {
    /**
     * Creates and returns a JSONObject based on employee and address data.
     *
     * @param skills   an array of employee's skills
     * @param address  an object containing employee's address information
     * @param employee an object containing employee's data
     * @return JSONObject containing employee and address data
     */
    private static JSONObject getJsonObject(String[] skills, Address address, EmployeeData employee) {
        JSONArray jsonSkills = new JSONArray(skills);

        JSONObject jsonAddress = new JSONObject();
        jsonAddress.put("streetAddress", address.getStreetAddress());
        jsonAddress.put("city", address.getCity());
        jsonAddress.put("state", address.getState());
        jsonAddress.put("postalCode", address.getPostalCode());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isActive", employee.isActive());
        jsonObject.put("employeeId", employee.getEmployeeId());
        jsonObject.put("fullName", employee.getFullName());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("skills", jsonSkills);
        return jsonObject;
    }

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "CityVille", "State", "12345");
        String[] skills = {"Java", "SQL", "HTML", "CSS"};
        EmployeeData employee = new EmployeeData(true, 1001, "John Doe", address, skills);

        /* Creation of JSONObject object based on data about the employee and his address */
        JSONObject jsonObject = getJsonObject(skills, address, employee);

        /* Output the result to the console */
        System.out.println(jsonObject);

        /* Convert the person object to a json string */
        JSONObject employeeJsonObject = new JSONObject(employee.fromObjectToJson());
        System.out.println(employeeJsonObject);

    }
}