import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


public class MessageListener extends Handler{
	
	private MessageBot bot;
	
	public MessageListener(MessageBot mb) {
		super();
		bot = mb;
	}
	
	@EventSubscriber
	public void onReadyEvent(MessageReceivedEvent event) throws RateLimitException, MissingPermissionsException, DiscordException {
		System.out.println("[message] " + event.getMessage().getAuthor().getName() + ": " + event.getMessage().getContent());
		String content = event.getMessage().getContent();
		if(content.split(" ")[0].charAt(0) == '!') {
			bot.parseCommand(event);
		}
		if(bot.annoying()) {
			bot.getManager().sendMessage("yoo wassupp");
		}
	}
	
	@Override
	public void unregister() {
		bot.unregisterListener(this);
		bot = null;
	}
	
	public MessageBot getBot() {
		return bot;
	}

}
