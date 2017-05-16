package com.coredumpproject.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Employee implements Serializable {

    @Id
    private int idEmployee;

    private String email;

    private String firstname;

    private String lastname;

    public Employee() {
    }

    public int getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Employee [idEmployee=" + idEmployee + ", email=" + email
                + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }

}
