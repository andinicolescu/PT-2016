package assig3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
	private int queueMax = 3;
	private int queueNr = 5;
	private int openTime = 800000;
	private int closeTime = 820000;
	private int minArr;
	private int maxArr;
	private Generator generator = new Generator();
	private GUI graphic = GUI.getInstance();
	private AtomicInteger currentTime = new AtomicInteger(0);
	private ArrayList<Server> servers = new ArrayList<Server>(10);
	private int[][] timeAnalysis = new int[4][10];
	private Scanner scanner;

	public Scheduler() {
		try {
			scanner = new Scanner(new File("input.txt"));
			queueMax = scanner.nextInt();
			queueNr = scanner.nextInt();
			openTime = scanner.nextInt();
			currentTime.set(openTime);
			closeTime = scanner.nextInt();
			minArr = scanner.nextInt();
			maxArr = scanner.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void schedule() {
		timePasses();
		initializePeak();
		while (currentTime.get() < closeTime) {
			while (!queuesFull()) {

				if (needNewServer() && servers.size() < queueNr) {
					servers.add(new Server(servers.size()));
					Thread thr = new Thread(servers.get(servers.size() - 1));
					thr.start();
				}
				Random rand = new Random();
				int randomNum = rand.nextInt((maxArr - minArr) + 1) + minArr;

				try {
					Thread.sleep(randomNum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (!queuesFull()) {
					Task t1 = generator.generate();
					t1.setProcessingStart(currentTime.get());
					int bestQueue = getBestQueue();
					t1.setProcessingEnd(
							currentTime.get() + t1.getOveralTime() + servers.get(bestQueue).getWaitingTime().get());
					t1.setServerNr(bestQueue);
					servers.get(bestQueue).getQueue().add(t1);
					servers.get(bestQueue).getWaitingTime().addAndGet((int) t1.getOveralTime());
					countWaitingTime(bestQueue, servers.get(bestQueue).getWaitingTime().get());
					countPeakTime(bestQueue,servers.get(bestQueue).getWaitingTime().get());
					graphic.addTask(t1);
				}

			}
		}

	}

	public void countPeakTime(int queue, int waitingTime) {
		if (timeAnalysis[2][queue]<waitingTime){
			timeAnalysis[2][queue]=waitingTime;
			timeAnalysis[3][queue]=currentTime.get();
		}
	}

	public void initializePeak() {
		for (int i = 0; i < queueNr; i++) {
			timeAnalysis[1][i] = 0;
		}
	}

	public void countWaitingTime(int queue, int time) {
		timeAnalysis[0][queue] = timeAnalysis[0][queue] + time;
		timeAnalysis[1][queue]++;
	}

	public void printStatistics() {
		for (int i = 0; i < queueNr; i++) {
			System.out.println(
					"Average Waiting Time for Server " + (i + 1) + " is " + timeAnalysis[0][i] / timeAnalysis[1][i]);
			System.out.println("Peak Waiting Time on server "+(i+1)+" was "+timeAnalysis[2][i]+" at "+timeAnalysis[3][i]);
		}
		System.out.println("Service Time: " + ((closeTime - openTime) * queueNr));
	}

	public void closeLogger() {
		boolean close = true;
		for (Server s : servers) {
			if (s.getQueue().size() > 0) {
				close = false;
			}
		}
		if (close) {
			servers.get(0).closeLogger();
		}
	}

	public void timePasses() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				boolean finished = false;
				while (true)
					try {
						Thread.sleep(100);
						graphic.setTime(currentTime.addAndGet(100));
						if (currentTime.get() > closeTime && !finished) {
							printStatistics();
							finished = true;
						}
						if (currentTime.get() > closeTime) {
							closeLogger();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
		t1.start();
	}

	public int getBestQueue() {
		int queue = -1;
		int min = closeTime;
		int i = -1;
		for (Server s : servers) {
			i++;
			if (s.getWaitingTime().get() < min && s.getQueue().size() < queueMax) {
				min = s.getWaitingTime().get();
				queue = i;
			}
		}
		return queue;
	}

	public boolean queuesFull() {
		if (servers.size() < queueNr)
			return false;
		else
			for (Server s : servers) {
				if (s.getQueue().size() < queueMax - 1)
					return false;
			}
		return true;
	}

	public boolean needNewServer() {
		for (Server s : servers) {
			if (s.getQueue().size() < queueMax)
				return false;
		}
		return true;
	}

	public void processTasks() {
		for (Server s : servers) {
			Thread t = new Thread(s);
			t.start();
		}
	}
}
