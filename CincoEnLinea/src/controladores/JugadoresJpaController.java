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
import persistencia.Partida;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.Jugadores;

/**
 *
 * @author marianacro
 */
public class JugadoresJpaController implements Serializable {

    public JugadoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jugadores jugadores) throws PreexistingEntityException, Exception {
        if (jugadores.getPartidaCollection() == null) {
            jugadores.setPartidaCollection(new ArrayList<Partida>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Partida> attachedPartidaCollection = new ArrayList<Partida>();
            for (Partida partidaCollectionPartidaToAttach : jugadores.getPartidaCollection()) {
                partidaCollectionPartidaToAttach = em.getReference(partidaCollectionPartidaToAttach.getClass(), partidaCollectionPartidaToAttach.getIdPartida());
                attachedPartidaCollection.add(partidaCollectionPartidaToAttach);
            }
            jugadores.setPartidaCollection(attachedPartidaCollection);
            em.persist(jugadores);
            for (Partida partidaCollectionPartida : jugadores.getPartidaCollection()) {
                partidaCollectionPartida.getJugadoresCollection().add(jugadores);
                partidaCollectionPartida = em.merge(partidaCollectionPartida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJugadores(jugadores.getIdJugadores()) != null) {
                throw new PreexistingEntityException("Jugadores " + jugadores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jugadores jugadores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugadores persistentJugadores = em.find(Jugadores.class, jugadores.getIdJugadores());
            Collection<Partida> partidaCollectionOld = persistentJugadores.getPartidaCollection();
            Collection<Partida> partidaCollectionNew = jugadores.getPartidaCollection();
            Collection<Partida> attachedPartidaCollectionNew = new ArrayList<Partida>();
            for (Partida partidaCollectionNewPartidaToAttach : partidaCollectionNew) {
                partidaCollectionNewPartidaToAttach = em.getReference(partidaCollectionNewPartidaToAttach.getClass(), partidaCollectionNewPartidaToAttach.getIdPartida());
                attachedPartidaCollectionNew.add(partidaCollectionNewPartidaToAttach);
            }
            partidaCollectionNew = attachedPartidaCollectionNew;
            jugadores.setPartidaCollection(partidaCollectionNew);
            jugadores = em.merge(jugadores);
            for (Partida partidaCollectionOldPartida : partidaCollectionOld) {
                if (!partidaCollectionNew.contains(partidaCollectionOldPartida)) {
                    partidaCollectionOldPartida.getJugadoresCollection().remove(jugadores);
                    partidaCollectionOldPartida = em.merge(partidaCollectionOldPartida);
                }
            }
            for (Partida partidaCollectionNewPartida : partidaCollectionNew) {
                if (!partidaCollectionOld.contains(partidaCollectionNewPartida)) {
                    partidaCollectionNewPartida.getJugadoresCollection().add(jugadores);
                    partidaCollectionNewPartida = em.merge(partidaCollectionNewPartida);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = jugadores.getIdJugadores();
                if (findJugadores(id) == null) {
                    throw new NonexistentEntityException("The jugadores with id " + id + " no longer exists.");
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
            Jugadores jugadores;
            try {
                jugadores = em.getReference(Jugadores.class, id);
                jugadores.getIdJugadores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugadores with id " + id + " no longer exists.", enfe);
            }
            Collection<Partida> partidaCollection = jugadores.getPartidaCollection();
            for (Partida partidaCollectionPartida : partidaCollection) {
                partidaCollectionPartida.getJugadoresCollection().remove(jugadores);
                partidaCollectionPartida = em.merge(partidaCollectionPartida);
            }
            em.remove(jugadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jugadores> findJugadoresEntities() {
        return findJugadoresEntities(true, -1, -1);
    }

    public List<Jugadores> findJugadoresEntities(int maxResults, int firstResult) {
        return findJugadoresEntities(false, maxResults, firstResult);
    }

    private List<Jugadores> findJugadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jugadores.class));
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

    public Jugadores findJugadores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getJugadoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jugadores> rt = cq.from(Jugadores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
