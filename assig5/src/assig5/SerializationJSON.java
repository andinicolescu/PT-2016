package assig5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SerializationJSON extends SerTemplate {

	@SuppressWarnings("unchecked")
	public void serialize(Map<String, Set<String>> dic) {

		JSONObject dicObj = new JSONObject();
		for (Entry<String, Set<String>> entry : dic.entrySet()) {
			JSONArray b = new JSONArray();
			for (String syn : entry.getValue()) {
				b.add(syn);
			}
			dicObj.put(entry.getKey(), b);
		}
		try {

			// Writing to a file
			File file = new File(super.filename+".json");
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);

			fileWriter.write(dicObj.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, Set<String>> readDictionary() {
		JSONParser parser = new JSONParser();
		Map<String, Set<String>> result = new TreeMap<String, Set<String>>();

		try {

			Object obj = parser.parse(new FileReader(super.filename+".json"));

			JSONObject jsonObject = (JSONObject) obj;

			Set<String> keys = (Set<String>) jsonObject.keySet();

			for (String key : keys) {
				Set<String> values = new TreeSet<String>();
				JSONArray arr = (JSONArray) jsonObject.get(key);
				Iterator<String> iterator = arr.iterator();
				while (iterator.hasNext()) {
					values.add(iterator.next());
				}
				result.put(key, values);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

}
