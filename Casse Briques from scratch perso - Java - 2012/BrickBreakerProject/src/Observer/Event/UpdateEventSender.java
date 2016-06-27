package Observer.Event;


public interface UpdateEventSender {
	public void update();
	public void add(UpdateEventListener l);
}
