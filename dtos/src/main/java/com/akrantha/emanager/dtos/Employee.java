package com.akrantha.emanager.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(email).append(extn).append(dob)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) obj;
        if (id > 0 && employee.id > 0) {
            return employee.id == id;
        }
        return new EqualsBuilder().append(name, employee.name).append(email, employee.email)
                .append(extn, employee.extn).append(dob, employee.dob).isEquals();
    }
}
