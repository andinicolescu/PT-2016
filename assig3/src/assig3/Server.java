package assig3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

	private BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(10);
	private AtomicInteger waitingTime = new AtomicInteger(0);
	private Logger logger=Logger.getInstance();
	private int nr;
	private GUI graphic = GUI.getInstance();
	public Server(int nr){
		this.nr=nr;
	}
	
	public void run() {
		
		while (true) {
			try {
				Task t1 = queue.take();
				long taskTime = t1.getOveralTime();
				t1.setServerNr(nr);
				logger.logTask(t1);
				Thread.sleep(taskTime);
				graphic.pop(nr);
				waitingTime.set(waitingTime.get() - (int) taskTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeLogger(){
		logger.close();
	}

	public BlockingQueue<Task> getQueue() {
		return queue;
	}

	public AtomicInteger getWaitingTime() {
		return waitingTime;
	}

}
