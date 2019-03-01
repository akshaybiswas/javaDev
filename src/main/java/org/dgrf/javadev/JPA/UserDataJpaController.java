/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.javadev.JPA;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.dgrf.javadev.JPA.exceptions.NonexistentEntityException;
import org.dgrf.javadev.JPA.exceptions.PreexistingEntityException;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
public class UserDataJpaController implements Serializable {

    public UserDataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserData userData) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUserData(userData.getUserEmail()) != null) {
                throw new PreexistingEntityException("UserData " + userData + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserData userData) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userData = em.merge(userData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = userData.getUserEmail();
                if (findUserData(id) == null) {
                    throw new NonexistentEntityException("The userData with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserData userData;
            try {
                userData = em.getReference(UserData.class, id);
                userData.getUserEmail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userData with id " + id + " no longer exists.", enfe);
            }
            em.remove(userData);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserData> findUserDataEntities() {
        return findUserDataEntities(true, -1, -1);
    }

    public List<UserData> findUserDataEntities(int maxResults, int firstResult) {
        return findUserDataEntities(false, maxResults, firstResult);
    }

    private List<UserData> findUserDataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserData.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserData findUserData(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserData.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserDataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserData> rt = cq.from(UserData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
