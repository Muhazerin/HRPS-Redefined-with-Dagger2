package com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces;

/**
 * 
 * @author muhazerin
 *
 */
public interface DataAccess {
	public Object[] readObject(Class<?> cls);
	
	public void writeObject (Object[] objs, Class<?> cls);
}
