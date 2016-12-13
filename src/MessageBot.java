import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


public class MessageBot extends Bot{

	private BotManager manager;
	private boolean annoying;
	
	public MessageBot(String token, BotManager bm) throws DiscordException {
		super(token);
		manager = bm;
		annoying = false;
	}
	
	public void registerListener(Handler h) {
		this.getClient().getDispatcher().registerListener(h);
		System.out.println("[bot status] handler recieved");
	}
	public void unregisterListener(Handler h) {
		this.getClient().getDispatcher().unregisterListener(h);
		System.out.println("[bot status] handler removed");
	}

	public void parseCommand(MessageReceivedEvent event) throws RateLimitException, MissingPermissionsException, DiscordException {
		String content = event.getMessage().getContent();
		String[] splitContent = content.split(" ");
		String command = splitContent[0];
		IChannel channel = event.getMessage().getChannel();
		IUser author = event.getMessage().getAuthor();
		int i = command.length();
		if (splitContent.length > 1)
			manager.parseCommand(command.substring(1), content.substring(i+1), channel, author);
		else
			manager.parseCommand(command.substring(1), "", channel, author);		
	}
	
	public BotManager getManager() {
		return manager;
	}
	
	public boolean annoying() {
		return annoying;
	}
	public void setAnnoying(boolean b) {
		annoying = b;
	}
	
}
