/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
public class UserMaintanance {
    
    
    public List<UserData> getActiveUsers() {
        UserDataDAO userDataDAO = new UserDataDAO();
        
        List<UserData> activeUsers = userDataDAO.getUsersByStaus(true);
        
        return activeUsers;
    }
    
    public List<UserData> getInactiveUsers() {
        UserDataDAO userDataDAO = new UserDataDAO();
        List<UserData> inactiveUsers = userDataDAO.getUsersByStaus(false);
        
        return inactiveUsers;
        
    }
    

    
    public void ActivateUser(String email) {
        UserDataDAO uAU = new UserDataDAO();
        UserData userData = uAU.findUserData(email);
        
        userData.setUserStatus(true);
        userData.setUserInvalidAttms(0);
        
        try {
            uAU.edit(userData);
        } catch (Exception ex) {
            Logger.getLogger(UserMaintanance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeactivateUser(String email) {
        UserDataDAO uAU = new UserDataDAO();
        UserData userData = uAU.findUserData(email);
        
        userData.setUserStatus(false);
        
        try {
            uAU.edit(userData);
        } catch (Exception ex) {
            Logger.getLogger(UserMaintanance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
