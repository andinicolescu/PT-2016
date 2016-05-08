package assig5;

import java.util.Map;
import java.util.Set;

public interface DictionaryI {

	/**
	 * preCond syno!=null
	 * @param syno
	 * postCond Map presize<Map postsize
	 */
	public void addSynonym(String word, String syno);
	/**
	 * preCond word!=null
	 * preCond syno not empty
	 * @param word
	 * postCond Map presize>Map postsize
	 */
	public void deleteSynonym(String word);
	/**
	 * 
	 * @return
	 */
	public Map<String,Set<String>> viewAll();
	/**
	 * 
	 * @param word
	 * @return
	 */
	public Map<String,Set<String>> searchWord(String word);
	/**
	 * 
	 * @return
	 */
	public boolean isConsistent();
}
