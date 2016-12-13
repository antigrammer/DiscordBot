import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;


public class Bot {
	
	//Fields
	private static IDiscordClient myClient;
	private ReadyEventListener readyListener;
	private boolean ready;
	
	//Constructors
	public Bot(String token) throws DiscordException {
		
		ready = false;
		myClient = new ClientBuilder().withToken(token).login();
		readyListener = new ReadyEventListener(this);

		myClient.getDispatcher().registerListener(readyListener);		
	}
	
	//Class methods
	public IDiscordClient getClient() {
		return myClient;
	}
	public void setClient(String token) throws DiscordException {
		myClient = new ClientBuilder().withToken(token).login();
	}
	public boolean isReady() {
		return ready;
	}
	public void ready() {
		ready = true;
	}
	public void notReady() {
		ready = false;
	}
		
}
