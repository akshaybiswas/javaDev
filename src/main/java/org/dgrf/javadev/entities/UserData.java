/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dgrfiv
 */
@Entity
@Table(name = "user_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserData.findAll", query = "SELECT u FROM UserData u")})
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_email")
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "user_firstname")
    private String userFirstname;
    @Basic(optional = false)
    @Column(name = "user_lastname")
    private String userLastname;
    @Basic(optional = false)
    @Column(name = "user_password")
    private String userPassword;
    @Basic(optional = false)
    @Column(name = "user_status")
    private boolean userStatus;
    @Basic(optional = false)
    @Column(name = "user_lastlogin_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userLastloginTs;
    @Basic(optional = false)
    @Column(name = "user_invalid_attms")
    private int userInvalidAttms;

    public UserData() {
    }

    public UserData(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserData(String userEmail, String userFirstname, String userLastname, String userPassword, boolean userStatus, Date userLastloginTs, int userInvalidAttms) {
        this.userEmail = userEmail;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userLastloginTs = userLastloginTs;
        this.userInvalidAttms = userInvalidAttms;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserLastloginTs() {
        return userLastloginTs;
    }

    public void setUserLastloginTs(Date userLastloginTs) {
        this.userLastloginTs = userLastloginTs;
    }

    public int getUserInvalidAttms() {
        return userInvalidAttms;
    }

    public void setUserInvalidAttms(int userInvalidAttms) {
        this.userInvalidAttms = userInvalidAttms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEmail != null ? userEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserData)) {
            return false;
        }
        UserData other = (UserData) object;
        if ((this.userEmail == null && other.userEmail != null) || (this.userEmail != null && !this.userEmail.equals(other.userEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.dgrf.javadev.entities.UserData[ userEmail=" + userEmail + " ]";
    }
    
}
