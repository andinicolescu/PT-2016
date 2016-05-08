package assig5;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary implements DictionaryI {
	private Map<String, Set<String>> synonyms;
	private SerializationJSON serialJSON;
	private SerializationSER serialSER;

	public Dictionary() {
		//setSynonyms(new TreeMap<String, Set<String>>());
		serialJSON=new SerializationJSON();
		serialSER=new SerializationSER();
		setSynonyms(serialJSON.readDictionary());
	}

	public Map<String, Set<String>> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Map<String, Set<String>> synonyms) {
		this.synonyms = synonyms;
	}

	@Override
	public void addSynonym(String word, String syno) {
		assert word != null && !word.isEmpty() : "Word is not good";
		assert syno != null : "Synonym word is empty";
		int preSize = synonyms.size();
		if (synonyms.containsKey(word)) {
			preSize = synonyms.size();
			Set<String> newSynonyms = synonyms.get(word);
			for (String s : newSynonyms) {
				synonyms.get(s).add(syno);
			}
			newSynonyms.add(syno);
			if (synonyms.containsKey(syno)) {
				synonyms.get(syno).add(word);
			} else {
				Set<String> s1 = new HashSet<String>();
				s1.add(word);
				for (String s : synonyms.get(word)) {
					if (!s.equals(syno)) {
						s1.add(s);
					}
				}
				synonyms.put(syno, s1);
			}
		} else {
			Set<String> s1 = new HashSet<String>();
			s1.add(syno);
			synonyms.put(word, s1);
			if (synonyms.containsKey(syno)) {
				synonyms.get(syno).add(word);
			} else {
				Set<String> s2 = new HashSet<String>();
				s2.add(word);
				for (String s : synonyms.get(word)) {
					if (!s.equals(syno)) {
						s1.add(s);
					}
				}
				synonyms.put(syno, s2);
			}
		}
		int postSize = synonyms.size();
		assert preSize < postSize : "Synonyms not added";
		assert isConsistent() : "Dictionary is not consistent!";

		serialJSON.serialize(synonyms);
		serialSER.serialize(synonyms);
	}

	@Override
	public String toString() {
		return "Dictionary [synonyms=" + synonyms + "]";
	}

	@Override
	public void deleteSynonym(String word) {
		assert isConsistent() : "Dictionary is not consistent";
		assert word != null && !word.isEmpty() : "Word is empty";
		int preSize = synonyms.size();
		if (synonyms.containsKey(word)) {
			for (String syn : synonyms.get(word)) {
				synonyms.get(syn).remove(word);
			}
			synonyms.remove(word);
		}
		int postSize = synonyms.size();
		assert preSize > postSize : "Word was not deleted";
		assert isConsistent() : "Dictionary is not consistent";
		
		serialJSON.serialize(synonyms);
		serialSER.serialize(synonyms);
	}

	@Override
	public Map<String, Set<String>> viewAll() {
		return synonyms;
	}

	@Override
	public Map<String, Set<String>> searchWord(String word) {
		Map<String, Set<String>> result = new TreeMap<String, Set<String>>();
		if (word.contains("?") || word.contains("*")){
			for (String keys : synonyms.keySet()) {
				if (keys.matches(word.replace("?", ".?").replace("*", ".*?"))) {
					result.put(keys, synonyms.get(keys));
				}
			}
			return result;
		}
		else {
			result.put(word, synonyms.get(word));
			return result;
		}
	}

	@Override
	public boolean isConsistent() {
		for (String word : synonyms.keySet()) {
			for (String syno : synonyms.get(word)) {
				if (!synonyms.containsKey(syno)) {
					return false;
				}
			}
		}
		return true;
	}
}
