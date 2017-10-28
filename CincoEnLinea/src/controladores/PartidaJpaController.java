/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.Jugadores;
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
        if (partida.getJugadoresCollection() == null) {
            partida.setJugadoresCollection(new ArrayList<Jugadores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Jugadores> attachedJugadoresCollection = new ArrayList<Jugadores>();
            for (Jugadores jugadoresCollectionJugadoresToAttach : partida.getJugadoresCollection()) {
                jugadoresCollectionJugadoresToAttach = em.getReference(jugadoresCollectionJugadoresToAttach.getClass(), jugadoresCollectionJugadoresToAttach.getIdJugadores());
                attachedJugadoresCollection.add(jugadoresCollectionJugadoresToAttach);
            }
            partida.setJugadoresCollection(attachedJugadoresCollection);
            em.persist(partida);
            for (Jugadores jugadoresCollectionJugadores : partida.getJugadoresCollection()) {
                jugadoresCollectionJugadores.getPartidaCollection().add(partida);
                jugadoresCollectionJugadores = em.merge(jugadoresCollectionJugadores);
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

    public void edit(Partida partida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partida persistentPartida = em.find(Partida.class, partida.getIdPartida());
            Collection<Jugadores> jugadoresCollectionOld = persistentPartida.getJugadoresCollection();
            Collection<Jugadores> jugadoresCollectionNew = partida.getJugadoresCollection();
            Collection<Jugadores> attachedJugadoresCollectionNew = new ArrayList<Jugadores>();
            for (Jugadores jugadoresCollectionNewJugadoresToAttach : jugadoresCollectionNew) {
                jugadoresCollectionNewJugadoresToAttach = em.getReference(jugadoresCollectionNewJugadoresToAttach.getClass(), jugadoresCollectionNewJugadoresToAttach.getIdJugadores());
                attachedJugadoresCollectionNew.add(jugadoresCollectionNewJugadoresToAttach);
            }
            jugadoresCollectionNew = attachedJugadoresCollectionNew;
            partida.setJugadoresCollection(jugadoresCollectionNew);
            partida = em.merge(partida);
            for (Jugadores jugadoresCollectionOldJugadores : jugadoresCollectionOld) {
                if (!jugadoresCollectionNew.contains(jugadoresCollectionOldJugadores)) {
                    jugadoresCollectionOldJugadores.getPartidaCollection().remove(partida);
                    jugadoresCollectionOldJugadores = em.merge(jugadoresCollectionOldJugadores);
                }
            }
            for (Jugadores jugadoresCollectionNewJugadores : jugadoresCollectionNew) {
                if (!jugadoresCollectionOld.contains(jugadoresCollectionNewJugadores)) {
                    jugadoresCollectionNewJugadores.getPartidaCollection().add(partida);
                    jugadoresCollectionNewJugadores = em.merge(jugadoresCollectionNewJugadores);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            Collection<Jugadores> jugadoresCollection = partida.getJugadoresCollection();
            for (Jugadores jugadoresCollectionJugadores : jugadoresCollection) {
                jugadoresCollectionJugadores.getPartidaCollection().remove(partida);
                jugadoresCollectionJugadores = em.merge(jugadoresCollectionJugadores);
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
