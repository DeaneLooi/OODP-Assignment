package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

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

	public static void writeSerializedObject(String fileName, List<?> list) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(list);
		out.close();
	}

}
