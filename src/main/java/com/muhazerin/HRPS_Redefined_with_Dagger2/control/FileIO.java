package com.muhazerin.HRPS_Redefined_with_Dagger2.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.DataAccess;

/**
 * 
 * @author KohTongLiang
 * @author https://github.com/KohTongLiang
 *
 */

@Singleton
public class FileIO implements DataAccess{
	private String directory = "";

	@Inject
	public FileIO() {}
	
	@Override
	public Object[] readObject(Class<?> cls) {
		directory = cls + ".txt";
		ArrayList<Object> objs = new ArrayList<>();
		try {
			
			FileInputStream fi = new FileInputStream(new File(directory));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			// Read objects
			boolean read = true;
			while (read) {
				Object obj = oi.readObject();
				
				if (obj == null) {
					read = !read;
				}
				else {
					objs.add(obj);	
				}
			}
			
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			if(e.getMessage() != null) {
				System.out.println("Error initializing stream: " + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objs.toArray();
	} // end of readObject class

	@Override
	public void writeObject (Object[] objs, Class<?> cls) {
		directory = cls + ".txt";
		try {
			FileOutputStream f = new FileOutputStream(new File(directory));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			for (int i = 0; i < objs.length; i++) {
				o.writeObject(objs[i]);
			}

			o.close();
			f.close();
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	} // end of writeObject class
}