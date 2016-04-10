package assig3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Logger {
	
	private PrintWriter writer;
	private static Logger instance;
	
	public Logger(){
		try {
			writer = new PrintWriter("logger.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void logTask(Task t){
		writer.println("Added to Queue: "+t.getProcessingStart());
		writer.println("Length: "+t.getOveralTime());
		writer.println("End: "+t.getProcessingEnd());
		writer.println("On Server "+t.getServerNr());
		writer.println();
	}
	
	public void close(){
		writer.close();
	}
	
	public static Logger getInstance() {
		if (instance == null) {
			synchronized (GUI.class) {
				if (instance == null) {
					instance = new Logger();
				}
			}
		}
		return instance;
	}
	
}
