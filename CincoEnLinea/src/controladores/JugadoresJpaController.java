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

    public JugadoresJpaController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jugadores jugadores) throws PreexistingEntityException, Exception {
        if (jugadores.getJugadoreshasPartidaCollection() == null) {
            jugadores.setJugadoreshasPartidaCollection(new ArrayList<JugadoreshasPartida>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<JugadoreshasPartida> attachedJugadoreshasPartidaCollection = new ArrayList<JugadoreshasPartida>();
            for (JugadoreshasPartida jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach : jugadores.getJugadoreshasPartidaCollection()) {
                jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach = em.getReference(jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach.getClass(), jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach.getJugadoreshasPartidaPK());
                attachedJugadoreshasPartidaCollection.add(jugadoreshasPartidaCollectionJugadoreshasPartidaToAttach);
            }
            jugadores.setJugadoreshasPartidaCollection(attachedJugadoreshasPartidaCollection);
            em.persist(jugadores);
            for (JugadoreshasPartida jugadoreshasPartidaCollectionJugadoreshasPartida : jugadores.getJugadoreshasPartidaCollection()) {
                Jugadores oldJugadoresOfJugadoreshasPartidaCollectionJugadoreshasPartida = jugadoreshasPartidaCollectionJugadoreshasPartida.getJugadores();
                jugadoreshasPartidaCollectionJugadoreshasPartida.setJugadores(jugadores);
                jugadoreshasPartidaCollectionJugadoreshasPartida = em.merge(jugadoreshasPartidaCollectionJugadoreshasPartida);
                if (oldJugadoresOfJugadoreshasPartidaCollectionJugadoreshasPartida != null) {
                    oldJugadoresOfJugadoreshasPartidaCollectionJugadoreshasPartida.getJugadoreshasPartidaCollection().remove(jugadoreshasPartidaCollectionJugadoreshasPartida);
                    oldJugadoresOfJugadoreshasPartidaCollectionJugadoreshasPartida = em.merge(oldJugadoresOfJugadoreshasPartidaCollectionJugadoreshasPartida);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJugadores(jugadores.getUsuario()) != null) {
                throw new PreexistingEntityException("Jugadores " + jugadores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jugadores jugadores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugadores persistentJugadores = em.find(Jugadores.class, jugadores.getUsuario());
            Collection<JugadoreshasPartida> jugadoreshasPartidaCollectionOld = persistentJugadores.getJugadoreshasPartidaCollection();
            Collection<JugadoreshasPartida> jugadoreshasPartidaCollectionNew = jugadores.getJugadoreshasPartidaCollection();
            List<String> illegalOrphanMessages = null;
            for (JugadoreshasPartida jugadoreshasPartidaCollectionOldJugadoreshasPartida : jugadoreshasPartidaCollectionOld) {
                if (!jugadoreshasPartidaCollectionNew.contains(jugadoreshasPartidaCollectionOldJugadoreshasPartida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JugadoreshasPartida " + jugadoreshasPartidaCollectionOldJugadoreshasPartida + " since its jugadores field is not nullable.");
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
            jugadores.setJugadoreshasPartidaCollection(jugadoreshasPartidaCollectionNew);
            jugadores = em.merge(jugadores);
            for (JugadoreshasPartida jugadoreshasPartidaCollectionNewJugadoreshasPartida : jugadoreshasPartidaCollectionNew) {
                if (!jugadoreshasPartidaCollectionOld.contains(jugadoreshasPartidaCollectionNewJugadoreshasPartida)) {
                    Jugadores oldJugadoresOfJugadoreshasPartidaCollectionNewJugadoreshasPartida = jugadoreshasPartidaCollectionNewJugadoreshasPartida.getJugadores();
                    jugadoreshasPartidaCollectionNewJugadoreshasPartida.setJugadores(jugadores);
                    jugadoreshasPartidaCollectionNewJugadoreshasPartida = em.merge(jugadoreshasPartidaCollectionNewJugadoreshasPartida);
                    if (oldJugadoresOfJugadoreshasPartidaCollectionNewJugadoreshasPartida != null && !oldJugadoresOfJugadoreshasPartidaCollectionNewJugadoreshasPartida.equals(jugadores)) {
                        oldJugadoresOfJugadoreshasPartidaCollectionNewJugadoreshasPartida.getJugadoreshasPartidaCollection().remove(jugadoreshasPartidaCollectionNewJugadoreshasPartida);
                        oldJugadoresOfJugadoreshasPartidaCollectionNewJugadoreshasPartida = em.merge(oldJugadoresOfJugadoreshasPartidaCollectionNewJugadoreshasPartida);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = jugadores.getUsuario();
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugadores jugadores;
            try {
                jugadores = em.getReference(Jugadores.class, id);
                jugadores.getUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugadores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<JugadoreshasPartida> jugadoreshasPartidaCollectionOrphanCheck = jugadores.getJugadoreshasPartidaCollection();
            for (JugadoreshasPartida jugadoreshasPartidaCollectionOrphanCheckJugadoreshasPartida : jugadoreshasPartidaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jugadores (" + jugadores + ") cannot be destroyed since the JugadoreshasPartida " + jugadoreshasPartidaCollectionOrphanCheckJugadoreshasPartida + " in its jugadoreshasPartidaCollection field has a non-nullable jugadores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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

    public Jugadores findJugadores(String id) {
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
