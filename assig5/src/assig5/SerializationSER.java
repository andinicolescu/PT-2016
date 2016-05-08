package assig5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

public class SerializationSER extends SerTemplate {

	@Override
	public void serialize(Map<String, Set<String>> dic) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(super.filename+".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dic);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Set<String>> readDictionary() {
		Map<String, Set<String>> dictionary;
		try {
			FileInputStream fis = new FileInputStream(super.filename+".ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			dictionary = (Map<String, Set<String>>) ois.readObject();
			ois.close();
			return dictionary;
		} catch (IOException i) {
			i.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
