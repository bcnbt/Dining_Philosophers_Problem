// Miscellaneous definitions for the Dining Philosophers            
// Credits given to Prof. Riley.

import java.lang.Thread;
import java.util.Random;

class miscsubs {
	static int NUMBER_PHILOSOPHERS = 5;
	static int NUMBER_CHOPSTICKS = 5;
	static int MAX_EATS = 500;
	static int TotalEats = 0;
	static int EatCount[] = new int[NUMBER_PHILOSOPHERS];
	static boolean EatingLog[] = new boolean[NUMBER_PHILOSOPHERS];
	static Random r;

	static void InitializeChecking() {
		int i;
		for (i = 0; i < NUMBER_PHILOSOPHERS; ++i) {
			EatingLog[i] = false;
		}
	}

	static synchronized void StartEating(int MyIndex) {
		// Un-comment below for debugging..
		// System.out.println("Philosopher " + MyIndex + " Eating");
		TotalEats++;
		EatCount[MyIndex]++;
		EatingLog[MyIndex] = true;
		int LeftNeighbor = (MyIndex == 0) ? NUMBER_PHILOSOPHERS - 1
				: MyIndex - 1;
		int RightNeighbor = (MyIndex + 1) % NUMBER_PHILOSOPHERS;
		// At no time can any be eating at the same time as neighbors */
		if (EatingLog[LeftNeighbor] || EatingLog[RightNeighbor]) {
			System.out.println("ERROR! Philosopher " + MyIndex
					+ " eating incorrectly");
		}
	}

	static synchronized void DoneEating(int MyIndex) {
		EatingLog[MyIndex] = false;
	}

	static void LogResults() {
		for (int i = 0; i < NUMBER_PHILOSOPHERS; i++) {
			System.out.println("EatCount " + i + " - " + EatCount[i]);
		}
	}

	static void RandomDelay() {
		if (r == null) {
			r = new Random();
		}
		int ycount = r.nextInt(900) + 100;
		for (int i = 0; i < ycount; ++i) {
			Thread.currentThread();
			Thread.yield();
		}
	}
}
