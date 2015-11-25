// Use java threads to simulate the Dining Philosophers Problem; Credits given to Prof. Riley
// Bugra Cinbat & Sam Bruns*/

class DiningPhilosopher {
	private final Chopstick[] chopsticks = new Chopstick[5];
	private int count = 0;
	Thread philosopher;

	public DiningPhilosopher() {
		putChopsticksOnTheTable();
		for (int i = 0; i < miscsubs.NUMBER_PHILOSOPHERS; i++, count++) {
			if (i == 0) {
				i = miscsubs.NUMBER_PHILOSOPHERS - 1;
			}
			philosopher = new Thread(new PhilosopherThread((i + 1),
					this.chopsticks[i], this.chopsticks[count]));
			philosopher.start();
		}
	}

	private void putChopsticksOnTheTable() {
		for (int i = 0; i < chopsticks.length; i++)
			chopsticks[i] = new Chopstick();

	}

	public static void main(String args[]) throws InterruptedException {
		System.out.println("Starting the Dining Philosophers Simulation\n");
		miscsubs.InitializeChecking();
		new DiningPhilosopher();
		miscsubs.LogResults();
	}
};
