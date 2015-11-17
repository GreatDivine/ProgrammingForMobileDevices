package com.example.greatdivine.contactsapp;

/**
 * Created by Great Divine on 11/16/2015.
 */
public class Contact {

    private int m_Id;
    private String m_Name;
    private String m_Email;
    private String m_Phone;
    private String m_Street;
    private String m_City;
    private String m_Country;

    public Contact() {
        this.m_Id = 0;
        this.m_Name = "";
        this.m_Email = "";
        this.m_Phone = "";
        this.m_Street = "";
        this.m_City = "";
        this.m_Country = "";
    }

    public Contact(int m_Id, String m_Name, String m_Email, String m_Phone, String m_Street, String m_City, String m_Country) {
        this.m_Id = m_Id;
        this.m_Name = m_Name;
        this.m_Email = m_Email;
        this.m_Phone = m_Phone;
        this.m_Street = m_Street;
        this.m_City = m_City;
        this.m_Country = m_Country;
    }

    public void setId(int m_Id) {
        this.m_Id = m_Id;
    }

    public void setName(String m_Name) {
        this.m_Name = m_Name;
    }

    public void setEmail(String m_Email) {
        this.m_Email = m_Email;
    }

    public void setPhone(String m_Phone) {
        this.m_Phone = m_Phone;
    }

    public void setStreet(String m_Street) {
        this.m_Street = m_Street;
    }

    public void setCity(String m_City) {
        this.m_City = m_City;
    }

    public void setCountry(String m_Country) {
        this.m_Country = m_Country;
    }

    public int getId() {
        return m_Id;
    }

    public String getName() {
        return m_Name;
    }

    public String getEmail() {
        return m_Email;
    }

    public String getPhone() {
        return m_Phone;
    }

    public String getStreet() {
        return m_Street;
    }

    public String getCity() {
        return m_City;
    }

    public String getCountry() {
        return m_Country;
    }
}
