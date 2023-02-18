package repository;

import java.util.ArrayList;

public interface Crud<Entity> {
	
	/**
	 * 
	 * method to add entity
	 * 
	 */
	public void addEntityDataBase(Entity entity);

	/**
	 * 
	 * method to remove entity
	 * 
	 */
	public void removeEntityDataBase(Entity entity, Long id);
	/**
	 * 
	 * method to remove entity by attribute
	 * Warning(remove all entity which has the same attribute) 
	 * 
	 * */
	public void removeEntityByAttribute(Entity entity,String nameAttribute,Object valueAttribute);

	/**
	 * 
	 * method to update entity
	 *	
	 * 
	 */
	public void updateEntityDataBase(Entity entity);

	/**
	 * 
	 * method to list all entity
	 * 
	 */
	public ArrayList<Entity> listAllEntityDataBase(Entity entity);
	
	/**
	 * 
	 * method to list all entity by attribute
	 * 
	 */
	public ArrayList<Entity> listAllEntityDataBaseByAttribute(Entity entity,String nameAttribute, Object valueAttribute);

	/**
	 * 
	 * method to search entity by id
	 * 
	 */
	public Object searchEntityById(Entity entity,Long id);


}
