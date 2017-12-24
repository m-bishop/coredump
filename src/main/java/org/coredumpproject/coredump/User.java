package org.coredumpproject.coredump;

/**
 * Created by Gregory on 6/8/2017.
 */

import java.io.Serializable;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;

@Entity
@TransactionManagement(value= TransactionManagementType.BEAN)
public class User implements Serializable{

    @Id
    private int userId;
    private String name;

    public User(){
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.getName();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
