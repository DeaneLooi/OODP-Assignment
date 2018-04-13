package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class reads and writes to the data file of the Entity object.
 * 
 * @author Deane
 * @version 1.0
 * @since 2018-04-01
 */
public class Serialization {

	/**
	 * 
	 * Reads the data file that is being specified in fileName and returns the data
	 * 
	 * @param fileName File name of data file, a constant
	 * @return Returns a list of Entity objects
	 */
	public static List<?> readSerializedObject(String fileName) {

		List<?> pDetails = null;

		try {

			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			pDetails = (ArrayList<?>) in.readObject();
			in.close();

		} catch (IOException | ClassNotFoundException e) {
			// e.printStackTrace();
		}
		return pDetails;

	}

	/**
	 * 
	 * This method writes the data of the list of Entity objects into the data file that is being specified
	 * 
	 * @param fileName File name of data file, a constant
	 * @param list List of Entity object, retrieved from readSerializedObject(fileName)
	 * @return
	 */
	public static boolean writeSerializedObject(String fileName, List<?> list) {

		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException e) {
			return false;
		}

		return true;

	}

}
