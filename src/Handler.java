
public class Handler {
	
	private Bot bot;
	
	protected Handler() {	}
	
	public Handler(Bot b) {
		bot = b;
	}
	
	public void unregister() {
		System.out.println("WRONG");
		bot = null;
		bot.getClient().getDispatcher().unregisterListener(this);
	}

}
