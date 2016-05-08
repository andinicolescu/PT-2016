package assig5;

import java.util.Map;
import java.util.Set;

public abstract class SerTemplate {
	
	protected String filename="Dictionary";
	
	public abstract void serialize(Map<String, Set<String>> dic);
	public abstract Map<String, Set<String>> readDictionary();
}
