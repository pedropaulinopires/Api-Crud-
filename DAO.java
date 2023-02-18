package service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import domain.Person;
import javassist.compiler.ast.NewExpr;
import repository.Crud;

public class DAO<Entity> implements Crud<Entity> {
	/** create connection data base */
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("P");

	public DAO(String persistenceUnitName) {

	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getConnection() {
		return getEmf().createEntityManager();
	}

	/* methods data transfer object */

	public void addEntityDataBase(Entity entity) {
		// star connection
		EntityManager em = getConnection();
		try {
			// transactions
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			// if error, go back
			em.getTransaction().rollback();
			System.out.println("Error to add entity Data Base ==>> " + e.getMessage());
		} finally {
			// finish connection
			em.close();
		}

	}

	public void removeEntityDataBase(Entity entity, Long id) {
		// star connection
		EntityManager em = getConnection();
		try {
			// transactions
			em.getTransaction().begin();
			em.remove(em.getReference(entity.getClass(), id));
			em.getTransaction().commit();
		} catch (Exception e) {
			// if error, go back
			em.getTransaction().rollback();
			System.out.println("Erro to remove Entity Data Base ==>>  " + e.getMessage());
		} finally {
			// finish connection
			em.close();
		}

	}

	public void removeEntityByAttribute(Entity entity, String nameAttribute, Object valueAttribute) {
		// start connection
		EntityManager em = getConnection();
		try {
			// create query
			em.getTransaction().begin();
			em.createQuery("delete from " + entity.getClass().getSimpleName()+ " e where " + nameAttribute + " = :attribute")
					.setParameter("attribute", valueAttribute).executeUpdate();
			// if error, go back
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro to remove entity by attribute ==>> " + e);
		} finally {
			// finish connection
			em.close();
		}

	}


	public void updateEntityDataBase(Entity entity) {
		// star connection
		EntityManager em = getConnection();
		try {
			// start transitions
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			// if error, go back
			em.getTransaction().rollback();
			System.out.println("Error update entity data base ==>> " + e.getMessage());
		} finally {
			// close connection
			em.close();
		}

	}

	public ArrayList<Entity> listAllEntityDataBase(Entity entity) {
		ArrayList<Entity> list = new ArrayList<Entity>();
		EntityManager em = getConnection();
		try {
			list = (ArrayList<Entity>) em.createQuery("from "+entity.getClass().getSimpleName()).getResultList();
		} catch (Exception e) {
			System.out.println("Erro list all entity data base ==>> "+e.getMessage());
		} finally {
			//close connection
			em.close();
		}
		return list;
	}
	
	public ArrayList<Entity> listAllEntityDataBaseByAttribute(Entity entity, String nameAttribute,
			Object valueAttribute) {
		ArrayList<Entity> list = new ArrayList<Entity>();
		//start connection
		EntityManager em = getConnection();
		try {
			list =(ArrayList<Entity>) em.createQuery("select c from "+entity.getClass().getSimpleName()+" c where "+nameAttribute+" = :attribute").setParameter("attribute", valueAttribute).getResultList();
		} catch (Exception e) {
			System.out.println("Error list all entity data base by attribute ==>> "+e.getMessage());
		} finally {
			//close connection
			em.close();
		}
		return list;
	}

	public Object searchEntityById(Entity entity,Long id) {
		Object entityTransition =entity;
		//start connection
		EntityManager em = getConnection();
		try {
			//find element
			entityTransition =em.find(entity.getClass(), id);
		} catch (Exception e) {
			System.out.println("Erro search entity by id ==>> "+e.getMessage());
		} finally {
			//close connection
			em.close();
		}
		return entityTransition;
	}

	

	

}
