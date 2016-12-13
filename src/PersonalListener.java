import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


public class PersonalListener extends MessageListener {

	private IUser user;
	private IChannel channel;
	private Game game;
	
	public PersonalListener(MessageBot mb, IUser u, IChannel c, Game g) {
		super(mb);
		user = u;
		channel = c;
		game = g;
		System.out.println("[listener status] listening in on " + user.getName() + " on channel " + channel.getName() + ".");
	}
	
	@Override @EventSubscriber
	public void onReadyEvent(MessageReceivedEvent event) throws RateLimitException, MissingPermissionsException, DiscordException {
		if(event.getMessage().getAuthor().equals(user) && event.getMessage().getChannel().equals(channel)) {
			game.parse(event.getMessage());
		}
	}
	
	public IUser getUser() {
		return user;
	}
	public IChannel getChannel() {
		return channel;
	}

}
