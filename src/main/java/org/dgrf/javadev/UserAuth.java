/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.entities.UserData;

public class UserAuth {

    public int AuthenticateUser(String email, String pass) {

        UserDataDAO ud = new UserDataDAO();
        UserData userData = ud.findUserData(email);
        int returnCode;
        
        if (userData != null) {
            if (userData.getUserStatus()) {
                if (userData.getUserPassword().equals(pass)) {
                    Date lastLoggedIn = new Date();
                    
                    userData.setUserInvalidAttms(0);
                    userData.setUserLastloginTs(lastLoggedIn);
                    try {
                        ud.edit(userData);
                    } catch (Exception ex) {
                        Logger.getLogger(UserAuth.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    returnCode = 1;
                    return returnCode;

                } else {
                    int userAttm = userData.getUserInvalidAttms();
                    userAttm++;

                    if (userAttm > 2) {
                        userData.setUserStatus(false);
                        returnCode = 4;
                        return returnCode;
                    }
                    userData.setUserInvalidAttms(userAttm);
                    try {
                        ud.edit(userData);
                    } catch (Exception ex) {
                        Logger.getLogger(UserAuth.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    returnCode = 2;
                    return returnCode;
                }
            } else {
                returnCode = 3;
                return returnCode;
            }

        } else {
            returnCode = 0;
            return returnCode;
        }

    }
}
