/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev;

import java.util.Date;
import java.util.List;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
public class DgrfUser {

    public static void main(String[] args) {
        //UserDataDAO ud = new UserDataDAO();
        //List<UserData> uList = ud.findUserDataEntities();
        //List<UserData> al = new ArrayList<>();
        //Date tD = new Date();

//        for (int i = 0; i < uList.size(); i++) {
//            UserData userData = uList.get(i);
//            Date uT = userData.getUserLastloginTs();
//            if (uT.before(tD)) {
//                long diff = tD.getTime() - uT.getTime();
//                //System.out.println(diff);
//
//                int diffD = (int) (diff / (24 * 60 * 60 * 1000));
//                //int diffH = (int) (diff / (60 * 60 * 1000));
//                //int diffM = (int) (diff / (60 * 1000));
//                //System.out.println(diffH); 
//                
//                if (diffD > 30) {
//                    userData.setUserStatus(false);
//                    ud.UserDataEdit(userData);
//                }
//            }
//        }

        String email = args[0];
        String pass = args[1];

        UserAuth ua = new UserAuth();
        int uAuth = ua.AuthenticateUser(email, pass);

        switch (uAuth) {
            case 0:
                System.out.println("Invalid User.");
                break;
            case 1:
                System.out.println("User logged in successfully. :)");
                break;
            case 2:
                System.out.println("Enterd wrong credentials. :(");
                break;
            case 3:
                System.out.println("Inactive User Account!");
                break;
            case 4:
                System.out.println("You have exceeded your attempts.");
                break;
            default:
                System.out.println("Oh Snap! Something went wrong.");
                break;
        }
          
        UserMaintanance uMaintain = new UserMaintanance();
   
        List<UserData> uActive = uMaintain.getActiveUsers();
        List<UserData> uInactive = uMaintain.getInactiveUsers();
        
        System.out.println("Active Users("+uActive.size()+"): ");
        for(int i = 0; i < uActive.size(); i++) {
            System.out.println(uActive.get(i).getUserEmail());
        }
        
        
        System.out.println("Inactive Users("+uInactive.size()+"): ");
        for(int j = 0; j < uInactive.size(); j++) {
            System.out.println(uInactive.get(j).getUserEmail());
        }
        
        //uMaintain.ActivateUser("demo@gmail.com");
        //uMaintain.DeactivateUser("demo@gmail.com");
    }
}
