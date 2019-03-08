/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev.dto;

/**
 *
 * @author dgrfiv
 */
public class UserDTO {
    private String email;
    private String firstName;
    private String lastname;
    private boolean activeFlag;
    private boolean loggedInUser;

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(boolean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    
    
    
}
