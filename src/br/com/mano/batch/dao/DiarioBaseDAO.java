package br.com.mano.batch.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.mano.batch.entities.DiarioBase;

public class DiarioBaseDAO {
	
    protected EntityManager entityManager;
    
    public DiarioBaseDAO() {
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
 
    public DiarioBase getById(final int id) {
        return entityManager.find(DiarioBase.class, id);
    }
    
    public void persist(DiarioBase diarioBase) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(diarioBase);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public void persist(Collection<DiarioBase> cotacoesDiarias) throws Exception {
        try {
            entityManager.getTransaction().begin();
            for (DiarioBase cot : cotacoesDiarias){
//            	System.out.println(cot.getCodigoNegociacaoPapel());
            	entityManager.persist(cot);
            }
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public void merge(DiarioBase diarioBase) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(diarioBase);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<DiarioBase> findAll() {
    	List<DiarioBase> list = entityManager.createQuery("FROM " + DiarioBase.class.getName()).getResultList();
    	return list;
    }
    
    
    public void remove(DiarioBase diarioBase) throws Exception {
        try {
            entityManager.getTransaction().begin();
            diarioBase = entityManager.find(DiarioBase.class, diarioBase.getId());
            entityManager.remove(diarioBase);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
 
    public void removeById(final int id) throws Exception {
        try {
        	DiarioBase diarioBase = getById(id);
            remove(diarioBase);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }
    }
    
    
    public int getLastId(){
    	int ret = (int) entityManager.createNativeQuery("SELECT Max(d.iddiariobase) FROM diariobase d").getSingleResult();
    	return ret;
    }

    public boolean verigicaRegistro(DiarioBase diariobase){
    	return pesquisaDiarioBase(diariobase).isEmpty();
    }

	private List<DiarioBase> pesquisaDiarioBase(DiarioBase diariobase) {
		@SuppressWarnings("unchecked")
		List<DiarioBase> lista = entityManager
			.createNamedQuery("diariobase.findBycodigoNegociacaoPapelEData")
			.setParameter("codigoNegociacaoPapel", diariobase.getCodigoNegociacaoPapel())
			.setParameter("dataPregao", diariobase.getDataPregao())
			.getResultList();
		return lista;
	}
    
    public DiarioBase retornaRegistro(DiarioBase diariobase){
    	return (DiarioBase) pesquisaDiarioBase(diariobase).get(0);
    }
    
    /**
     * 
     * @param idhead
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<String> buscaListaPapeis(int idhead){
    	@SuppressWarnings("rawtypes")
		List papeis = entityManager.createNativeQuery("SELECT d.codneg FROM DiarioBase d WHERE d.head_idhead =:head_idhead").setParameter("head_idhead", idhead).getResultList();
		return papeis;
    	
    }
    
	public Timestamp dataUltimoRegistro(){
		Timestamp time = (Timestamp) entityManager.createNativeQuery("SELECT max(d.datapreg) FROM DiarioBase d").getSingleResult();
		return time;
    }


}
