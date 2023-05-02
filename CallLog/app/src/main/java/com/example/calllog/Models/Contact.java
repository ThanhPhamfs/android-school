package com.example.calllog.Models;

import java.util.ArrayList;

public class Contact {
    private String name;
    private ArrayList<String> numbers = new ArrayList<>();
    private ArrayList<String> emails = new ArrayList<>();
    private ArrayList<String> address = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(String... numbers) {
        for (String item :
                numbers) {
            this.numbers.add(item);
        }
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(String... emails) {
        for (String item :
                emails) {
            this.emails.add(item);
        }    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public void setAddress(String... address) {
        for (String item :
                address) {
            this.address.add(item);
        }    }

    public Contact(String name, ArrayList<String> numbers, ArrayList<String> emails, ArrayList<String> address) {
        this.name = name;
        this.numbers = numbers;
        this.emails = emails;
        this.address = address;
    }

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                ", emails=" + emails +
                ", address=" + address +
                '}';
    }
}
