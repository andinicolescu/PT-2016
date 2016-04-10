package assig3;

import java.util.Random;

public class Generator {

	private static final long MIN = 4000;
	private static final long MAX = 8000;

	public Task generate() {
		Task t = new Task();
		Random r = new Random();
		long randomValue = MIN + (long) (r.nextDouble() * (MAX - MIN));
		t.setOveralTime(randomValue);
		return t;
	}
}
