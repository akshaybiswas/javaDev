/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.dgrf.javadev.JPA.UserDataJpaController;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
public class UserDataDAO extends UserDataJpaController{

    public UserDataDAO() {
        
        super(Persistence.createEntityManagerFactory("org.dgrf_javaDev_jar_1.0-SNAPSHOTPU"));
    }
    public List<UserData> getUsersByStaus (boolean userStatus) {
        EntityManager em = getEntityManager();
        TypedQuery<UserData> query = em.createNamedQuery("UserData.getUserBySatus", UserData.class);
        query.setParameter("userStatus", userStatus);
        List<UserData> userDataByStatus  = query.getResultList();
        return userDataByStatus; 
    }
    
}
