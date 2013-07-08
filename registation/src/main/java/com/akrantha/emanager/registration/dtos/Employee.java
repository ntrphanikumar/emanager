package com.akrantha.emanager.registration.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.akrantha.emanager.registration.services.InMemoryTable.InMemoryPersistable;

@XmlRootElement(name = "Employee")
public class Employee implements InMemoryPersistable, Serializable {
    private static final long serialVersionUID = -1738373669527745443L;

    private int id;

    private String name;

    private Date dob;

    private String email;

    private String extn;

    @XmlElement(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "DateOfBirth")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @XmlElement(name = "EMail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "Extn")
    public String getExtn() {
        return extn;
    }

    public void setExtn(String extn) {
        this.extn = extn;
    }

}
