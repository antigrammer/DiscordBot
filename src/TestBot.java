import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


public class TestBot extends Bot{

	public TestBot(String token) throws DiscordException {
		super(token);
		
		//getClient().getDispatcher().registerListener(new EventHandler());
		getClient().getDispatcher().registerListener(new ReadyEventListener(this));
	}
	
	public void registerListener(Object o) {
		getClient().getDispatcher().registerListener(o);
		System.out.println("got it");
	}
	
}
