/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.JugadoreshasPartida;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.Partida;

/**
 *
 * @author marianacro
 */
public class PartidaJpaController implements Serializable {

    public PartidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Partida partida) throws PreexistingEntityException, Exception {
        if (partida.getJugadoreshasPartidaCollection() == null) {
            partida.setJugadoreshasPartidaCollection(new ArrayList<JugadoreshasPartida>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<JugadoreshasPartida> attachedJugadoreshasPartidaCollection = new ArrayList<JugadoreshasPartida>();
            for (JugadoreshasPartida jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach : partida.getJugadoreshasPartidaCollection()) {
                jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach = em.getReference(jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach.getClass(), jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach.getJugadoreshasPartidaPK());
                attachedJugadoreshasPartidaCollection.add(jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach);
            }
            partida.setJugadoreshasPartidaCollection(attachedJugadoreshasPartidaCollection);
            em.persist(partida);
            for (JugadoreshasPartida jugadoreshasPartidaCollectionJugadoreshasPartida : partida.getJugadoreshasPartidaCollection()) {
                Partida oldPartidaOfJugadoreshasPartidaCollectionJugadoreshasPartida = jugadoreshasPartidaCollectionJugadoreshasPartida.getPartida();
                jugadoreshasPartidaCollectionJugadoreshasPartida.setPartida(partida);
                jugadoreshasPartidaCollectionJugadoreshasPartida = em.merge(jugadoreshasPartidaCollectionJugadoreshasPartida);
                if (oldPartidaOfJugadoreshasPartidaCollectionJugadoreshasPartida != null) {
                    oldPartidaOfJugadoreshasPartidaCollectionJugadoreshasPartida.getJugadoreshasPartidaCollection().remove(jugadoreshasPartidaCollectionJugadoreshasPartida);
                    oldPartidaOfJugadoreshasPartidaCollectionJugadoreshasPartida = em.merge(oldPartidaOfJugadoreshasPartidaCollectionJugadoreshasPartida);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPartida(partida.getIdPartida()) != null) {
                throw new PreexistingEntityException("Partida " + partida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Partida partida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partida persistentPartida = em.find(Partida.class, partida.getIdPartida());
            Collection<JugadoreshasPartida> jugadoreshasPartidaCollectionOld = persistentPartida.getJugadoreshasPartidaCollection();
            Collection<JugadoreshasPartida> jugadoreshasPartidaCollectionNew = partida.getJugadoreshasPartidaCollection();
            List<String> illegalOrphanMessages = null;
            for (JugadoreshasPartida jugadoreshasPartidaCollectionOldJugadoreshasPartida : jugadoreshasPartidaCollectionOld) {
                if (!jugadoreshasPartidaCollectionNew.contains(jugadoreshasPartidaCollectionOldJugadoreshasPartida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JugadoreshasPartida " + jugadoreshasPartidaCollectionOldJugadoreshasPartida + " since its partida field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<JugadoreshasPartida> attachedJugadoreshasPartidaCollectionNew = new ArrayList<JugadoreshasPartida>();
            for (JugadoreshasPartida jugadoreshasPartidaCollectionNewJugadoreshasPartidaToAttach : jugadoreshasPartidaCollectionNew) {
                jugadoreshasPartidaCollectionNewJugadoreshasPartidaToAttach = em.getReference(jugadoreshasPartidaCollectionNewJugadoreshasPartidaToAttach.getClass(), jugadoreshasPartidaCollectionNewJugadoreshasPartidaToAttach.getJugadoreshasPartidaPK());
                attachedJugadoreshasPartidaCollectionNew.add(jugadoreshasPartidaCollectionNewJugadoreshasPartidaToAttach);
            }
            jugadoreshasPartidaCollectionNew = attachedJugadoreshasPartidaCollectionNew;
            partida.setJugadoreshasPartidaCollection(jugadoreshasPartidaCollectionNew);
            partida = em.merge(partida);
            for (JugadoreshasPartida jugadoreshasPartidaCollectionNewJugadoreshasPartida : jugadoreshasPartidaCollectionNew) {
                if (!jugadoreshasPartidaCollectionOld.contains(jugadoreshasPartidaCollectionNewJugadoreshasPartida)) {
                    Partida oldPartidaOfJugadoreshasPartidaCollectionNewJugadoreshasPartida = jugadoreshasPartidaCollectionNewJugadoreshasPartida.getPartida();
                    jugadoreshasPartidaCollectionNewJugadoreshasPartida.setPartida(partida);
                    jugadoreshasPartidaCollectionNewJugadoreshasPartida = em.merge(jugadoreshasPartidaCollectionNewJugadoreshasPartida);
                    if (oldPartidaOfJugadoreshasPartidaCollectionNewJugadoreshasPartida != null && !oldPartidaOfJugadoreshasPartidaCollectionNewJugadoreshasPartida.equals(partida)) {
                        oldPartidaOfJugadoreshasPartidaCollectionNewJugadoreshasPartida.getJugadoreshasPartidaCollection().remove(jugadoreshasPartidaCollectionNewJugadoreshasPartida);
                        oldPartidaOfJugadoreshasPartidaCollectionNewJugadoreshasPartida = em.merge(oldPartidaOfJugadoreshasPartidaCollectionNewJugadoreshasPartida);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = partida.getIdPartida();
                if (findPartida(id) == null) {
                    throw new NonexistentEntityException("The partida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partida partida;
            try {
                partida = em.getReference(Partida.class, id);
                partida.getIdPartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<JugadoreshasPartida> jugadoreshasPartidaCollectionOrphanCheck = partida.getJugadoreshasPartidaCollection();
            for (JugadoreshasPartida jugadoreshasPartidaCollectionOrphanCheckJugadoreshasPartida : jugadoreshasPartidaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Partida (" + partida + ") cannot be destroyed since the JugadoreshasPartida " + jugadoreshasPartidaCollectionOrphanCheckJugadoreshasPartida + " in its jugadoreshasPartidaCollection field has a non-nullable partida field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(partida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Partida> findPartidaEntities() {
        return findPartidaEntities(true, -1, -1);
    }

    public List<Partida> findPartidaEntities(int maxResults, int firstResult) {
        return findPartidaEntities(false, maxResults, firstResult);
    }

    private List<Partida> findPartidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Partida.class));
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

    public Partida findPartida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Partida.class, id);
        } finally {
            em.close();
        }
    }

    public int getPartidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Partida> rt = cq.from(Partida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
