/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.Jugadores;
import persistencia.JugadoreshasPartida;
import persistencia.JugadoreshasPartidaPK;
import persistencia.Partida;

/**
 *
 * @author marianacro
 */
public class JugadoreshasPartidaJpaController implements Serializable {

    public JugadoreshasPartidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JugadoreshasPartida jugadoreshasPartida) throws PreexistingEntityException, Exception {
        if (jugadoreshasPartida.getJugadoreshasPartidaPK() == null) {
            jugadoreshasPartida.setJugadoreshasPartidaPK(new JugadoreshasPartidaPK());
        }
        jugadoreshasPartida.getJugadoreshasPartidaPK().setJugadoresusuario(jugadoreshasPartida.getJugadores().getUsuario());
        jugadoreshasPartida.getJugadoreshasPartidaPK().setPartidaidPartida(jugadoreshasPartida.getPartida().getIdPartida());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugadores jugadores = jugadoreshasPartida.getJugadores();
            if (jugadores != null) {
                jugadores = em.getReference(jugadores.getClass(), jugadores.getUsuario());
                jugadoreshasPartida.setJugadores(jugadores);
            }
            Partida partida = jugadoreshasPartida.getPartida();
            if (partida != null) {
                partida = em.getReference(partida.getClass(), partida.getIdPartida());
                jugadoreshasPartida.setPartida(partida);
            }
            em.persist(jugadoreshasPartida);
            if (jugadores != null) {
                jugadores.getJugadoreshasPartidaCollection().add(jugadoreshasPartida);
                jugadores = em.merge(jugadores);
            }
            if (partida != null) {
                partida.getJugadoreshasPartidaCollection().add(jugadoreshasPartida);
                partida = em.merge(partida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJugadoreshasPartida(jugadoreshasPartida.getJugadoreshasPartidaPK()) != null) {
                throw new PreexistingEntityException("JugadoreshasPartida " + jugadoreshasPartida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JugadoreshasPartida jugadoreshasPartida) throws NonexistentEntityException, Exception {
        jugadoreshasPartida.getJugadoreshasPartidaPK().setJugadoresusuario(jugadoreshasPartida.getJugadores().getUsuario());
        jugadoreshasPartida.getJugadoreshasPartidaPK().setPartidaidPartida(jugadoreshasPartida.getPartida().getIdPartida());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            JugadoreshasPartida persistentJugadoreshasPartida = em.find(JugadoreshasPartida.class, jugadoreshasPartida.getJugadoreshasPartidaPK());
            Jugadores jugadoresOld = persistentJugadoreshasPartida.getJugadores();
            Jugadores jugadoresNew = jugadoreshasPartida.getJugadores();
            Partida partidaOld = persistentJugadoreshasPartida.getPartida();
            Partida partidaNew = jugadoreshasPartida.getPartida();
            if (jugadoresNew != null) {
                jugadoresNew = em.getReference(jugadoresNew.getClass(), jugadoresNew.getUsuario());
                jugadoreshasPartida.setJugadores(jugadoresNew);
            }
            if (partidaNew != null) {
                partidaNew = em.getReference(partidaNew.getClass(), partidaNew.getIdPartida());
                jugadoreshasPartida.setPartida(partidaNew);
            }
            jugadoreshasPartida = em.merge(jugadoreshasPartida);
            if (jugadoresOld != null && !jugadoresOld.equals(jugadoresNew)) {
                jugadoresOld.getJugadoreshasPartidaCollection().remove(jugadoreshasPartida);
                jugadoresOld = em.merge(jugadoresOld);
            }
            if (jugadoresNew != null && !jugadoresNew.equals(jugadoresOld)) {
                jugadoresNew.getJugadoreshasPartidaCollection().add(jugadoreshasPartida);
                jugadoresNew = em.merge(jugadoresNew);
            }
            if (partidaOld != null && !partidaOld.equals(partidaNew)) {
                partidaOld.getJugadoreshasPartidaCollection().remove(jugadoreshasPartida);
                partidaOld = em.merge(partidaOld);
            }
            if (partidaNew != null && !partidaNew.equals(partidaOld)) {
                partidaNew.getJugadoreshasPartidaCollection().add(jugadoreshasPartida);
                partidaNew = em.merge(partidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                JugadoreshasPartidaPK id = jugadoreshasPartida.getJugadoreshasPartidaPK();
                if (findJugadoreshasPartida(id) == null) {
                    throw new NonexistentEntityException("The jugadoreshasPartida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(JugadoreshasPartidaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            JugadoreshasPartida jugadoreshasPartida;
            try {
                jugadoreshasPartida = em.getReference(JugadoreshasPartida.class, id);
                jugadoreshasPartida.getJugadoreshasPartidaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugadoreshasPartida with id " + id + " no longer exists.", enfe);
            }
            Jugadores jugadores = jugadoreshasPartida.getJugadores();
            if (jugadores != null) {
                jugadores.getJugadoreshasPartidaCollection().remove(jugadoreshasPartida);
                jugadores = em.merge(jugadores);
            }
            Partida partida = jugadoreshasPartida.getPartida();
            if (partida != null) {
                partida.getJugadoreshasPartidaCollection().remove(jugadoreshasPartida);
                partida = em.merge(partida);
            }
            em.remove(jugadoreshasPartida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<JugadoreshasPartida> findJugadoreshasPartidaEntities() {
        return findJugadoreshasPartidaEntities(true, -1, -1);
    }

    public List<JugadoreshasPartida> findJugadoreshasPartidaEntities(int maxResults, int firstResult) {
        return findJugadoreshasPartidaEntities(false, maxResults, firstResult);
    }

    private List<JugadoreshasPartida> findJugadoreshasPartidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JugadoreshasPartida.class));
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

    public JugadoreshasPartida findJugadoreshasPartida(JugadoreshasPartidaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JugadoreshasPartida.class, id);
        } finally {
            em.close();
        }
    }

    public int getJugadoreshasPartidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JugadoreshasPartida> rt = cq.from(JugadoreshasPartida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
