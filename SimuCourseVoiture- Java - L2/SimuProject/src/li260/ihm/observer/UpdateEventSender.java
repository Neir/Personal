package li260.ihm.observer;

public interface UpdateEventSender {
	public void update();
	public void add(UpdateEventListener l);
}
