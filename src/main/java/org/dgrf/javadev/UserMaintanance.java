/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.dto.ResponseCode;
import org.dgrf.javadev.JPA.exceptions.PreexistingEntityException;
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

    public int AddUser(String userEmail, String userFirstname, String userLastname) {
        int responseCode;

        try {
            UserDataDAO dataDAO = new UserDataDAO();
            UserData userData = new UserData();
            Date userLastloginTs = new Date();

            userData.setUserEmail(userEmail);
            userData.setUserPassword("1234");
            userData.setUserFirstname(userFirstname);
            userData.setUserLastname(userLastname);
            userData.setUserStatus(true);
            userData.setUserLastloginTs(userLastloginTs);

            dataDAO.create(userData);
            responseCode = ResponseCode.SUCCESS;

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(UserMaintanance.class.getName()).log(Level.SEVERE, null, ex);
            responseCode = ResponseCode.ALREADY_EXISISTS;

        } catch (Exception ex) {
            Logger.getLogger(UserMaintanance.class.getName()).log(Level.SEVERE, null, ex);
            responseCode = ResponseCode.CONTACT_ADMIN;
        }

        return responseCode;
    }

    public int UpdateUser(String userId, String userFirstname, String userLastname, boolean userStatus) {
        int responseCode;

        try {
            UserDataDAO dataDAO = new UserDataDAO();
            UserData userData = dataDAO.findUserData(userId);

            userData.setUserFirstname(userFirstname);
            userData.setUserLastname(userLastname);
            if(userStatus) {
                ActivateUser(userId);
            } else {
                DeactivateUser(userId);
            }

            dataDAO.edit(userData);
            responseCode = ResponseCode.SUCCESS;

        } catch (Exception ex) {
            Logger.getLogger(UserMaintanance.class.getName()).log(Level.SEVERE, null, ex);
            responseCode = ResponseCode.EXCEPTION_CAUGHT;
        }

        return responseCode;
    }

    public int UpdatePassword(String userId, String oldPass, String newPass) {
        int responseCode;
        String userOldPass;

        UserDataDAO dataDAO = new UserDataDAO();
        UserData userData = dataDAO.findUserData(userId);
        userOldPass = userData.getUserPassword();

        UserAuth userAuth = new UserAuth();
        responseCode = userAuth.AuthenticateUser(userId, oldPass);

        if (responseCode == ResponseCode.SUCCESS) {

            try {
                userData.setUserPassword(newPass);
                dataDAO.edit(userData);
                return responseCode;

            } catch (Exception ex) {
                Logger.getLogger(UserMaintanance.class.getName()).log(Level.SEVERE, null, ex);
                responseCode = ResponseCode.EXCEPTION_CAUGHT;
                return responseCode;
            }

        } else {
            return responseCode;
        }

    }
}
