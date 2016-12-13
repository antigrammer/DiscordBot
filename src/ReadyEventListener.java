import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;


public class ReadyEventListener implements IListener<ReadyEvent>{

	private Bot sourceBot;
	
	public ReadyEventListener(Bot bot) {
		sourceBot = bot;
	}

	public void handle(ReadyEvent event) {
		System.out.println("[bot status] the bot is now ready");
		startAction(event);
	}
	
	public void startAction(ReadyEvent event) {
		sourceBot.ready();
	}
	
}
