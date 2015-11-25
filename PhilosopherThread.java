public class PhilosopherThread implements Runnable {

	private final Chopstick left;
	private final Chopstick right;
	private State _state;

	public enum State {
		EATING, THINKING, WAITING
	}

	public PhilosopherThread(int name, Chopstick left, Chopstick right) {

		this.right = right;
		this.left = left;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				eat();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void think() throws InterruptedException {
		this.setPhilosopherState(PhilosopherThread.State.THINKING);
		miscsubs.RandomDelay();
	}

	private void setPhilosopherState(State state) {
		this._state = state;
	}

	public State getPhilosopherState() {
		return _state;
	}

	public void eat() throws InterruptedException {
		synchronized (right) {
			while (right.isUsed() || left.isUsed())
				try {
					this.setPhilosopherState(PhilosopherThread.State.WAITING);
					right.wait();
				} catch (InterruptedException e) {
				}
			synchronized (left) {
				try {
					Thread.sleep(1);
					right.setUsed(true);
					right.setUsed(true);
					this.setPhilosopherState(PhilosopherThread.State.EATING);
					miscsubs.RandomDelay();
				} finally {
					left.setUsed(false);
					right.setUsed(false);
					left.notify();
					right.notify();
				}
			}
			think();
		}
	}
}
