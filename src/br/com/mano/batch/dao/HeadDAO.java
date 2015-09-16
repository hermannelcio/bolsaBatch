package br.com.mano.batch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mano.batch.entities.Head;

public class HeadDAO {
	
    protected static EntityManager entityManager;
    
    public HeadDAO() {
        entityManager = getEntityManager();
    }
 
    private EntityManager getEntityManager() {
    	EntityManagerFactory factory = null;
        if (entityManager == null) {
        	factory = Persistence.createEntityManagerFactory("dbbolsaPU");
            entityManager = factory.createEntityManager();
        }
 
        return entityManager;
    }
 
    public Head getById(final int id) {
        return entityManager.find(Head.class, id);
    }
    
    public void persist(Head head) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(head);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    public void merge(Head head) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(head);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Head> findAll() {
    	List<Head> list = entityManager.createQuery("FROM " + Head.class.getName()).getResultList();
    	return list;
    }
    
    
    public void remove(Head head) {
        try {
            entityManager.getTransaction().begin();
            head = entityManager.find(Head.class, head.getId());
            entityManager.remove(head);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
 
    public void removeById(final int id) {
        try {
        	Head head = getById(id);
            remove(head);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int getLastId(){
    	int ret = (int) entityManager.createNativeQuery("SELECT Max(h.idhead) FROM head h").getSingleResult();
    	return ret;
    }

    public boolean verigicaRegistro(Head head){
    	return !pesquisaHead(head).isEmpty();
    }

	private List<Head> pesquisaHead(Head head) {
		@SuppressWarnings("unchecked")
		List<Head> lista = entityManager.createNamedQuery("head.findBynomeArquivoh").setParameter("nomeArquivoh", head.getNomeArquivoh()).getResultList();
		return lista;
	}
    
    public Head retornaRegistro(Head head){
    	return (Head) pesquisaHead(head).get(0);
    }


}
