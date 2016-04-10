package assig3;

public class Task {
	private long overalTime;
	private long processingStart;
	private long processingEnd;
	private int serverNr;
	
	public long getOveralTime() {
		return overalTime;
	}
	public void setOveralTime(long overalTime) {
		this.overalTime = overalTime;
	}
	public long getProcessingStart() {
		return processingStart;
	}
	public void setProcessingStart(long processingStart) {
		this.processingStart = processingStart;
	}
	public long getProcessingEnd() {
		return processingEnd;
	}
	public void setProcessingEnd(long processingEnd) {
		this.processingEnd = processingEnd;
	}
	public void seeTask(){
		System.out.println("Added to Queue: "+processingStart);
		System.out.println("Length: "+overalTime);
		System.out.println("End: "+processingEnd);
		System.out.println("On Server "+serverNr);
		System.out.println();
	}

	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("<html>Added: "+processingStart+"<br /><html>");
		sb.append("Length: "+overalTime+"\r\n ");
		sb.append("End: "+processingEnd+"\r\n ");
		return sb.toString();
	}
	
	public int getServerNr() {
		return serverNr;
	}
	public void setServerNr(int serverNr) {
		this.serverNr = serverNr;
	}
}
